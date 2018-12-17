package where.example.com.moviesapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Antoun on 10/18/2016.
 */
public class FetchMovies extends AsyncTask<Integer, Void, ArrayList<Movies>> {
    static String idd;
    static String Url;
    DataParsedListener listener;
    int type = 0;
    static ArrayList<Movies> movies = new ArrayList<Movies>();
    ArrayList<Movies> moviess = new ArrayList<Movies>();
    public void setOnDataParsed(DataParsedListener listen)

    {
        this.listener = listen;
    }

    private ArrayList<Movies> getReviews(String moviesJsonStr) throws JSONException {
        final String Results = "results";
        JSONObject moviesJson = new JSONObject(moviesJsonStr);
        JSONArray moviesArray = moviesJson.getJSONArray(Results);

        Movies movie = new Movies();
        for (int i = 0; i < moviesArray.length(); i++) {
            Review review = new Review();

            review.author = moviesArray.getJSONObject(i).getString("author");
            Log.v(review.author, "author");
            review.review = moviesArray.getJSONObject(i).getString("content");
            Log.v(review.review, "rev");
            movie.review.add(review);
        }
        moviess.add(movie);

        return moviess;
    }

    private ArrayList<Movies> getTrailers(String moviesJsonStr) throws JSONException {
        final String Youtube = "youtube";
        JSONObject moviesJson = new JSONObject(moviesJsonStr);
        JSONArray moviesArray = moviesJson.getJSONArray(Youtube);
//        ArrayList<Movies> moviess = new ArrayList<Movies>();
        Movies movie = new Movies();
        for (int i = 0; i < moviesArray.length(); i++) {
            String tmp = new String();
            tmp = "https://www.youtube.com/watch?v=" + moviesArray.getJSONObject(i).getString("source");
            Log.v(tmp, "trailers");
            movie.trailers.add(tmp);
        }
        moviess.add(movie);

        return moviess;
    }

    private ArrayList<Movies> getMoviesDataFromJson(String moviesJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String Results = "results";
        final String Poster = "poster_path";
        final String Overview = "overview";
        final String Date = "release_date";
        final String Id = "id";
        final String Title = "title";

        final String Vote_Avg = "vote_average";


        JSONObject moviesJson = new JSONObject(moviesJsonStr);
        JSONArray moviesArray = moviesJson.getJSONArray(Results);
        ArrayList<Movies> moviess = new ArrayList<Movies>();

        for (int i = 0; i < moviesArray.length(); i++) {
            Movies movie = new Movies();
            movie.moviePoster = "http://image.tmdb.org/t/p/w185" + moviesArray.getJSONObject(i).getString(Poster);
            movie.overview = moviesArray.getJSONObject(i).getString(Overview);
            movie.title = moviesArray.getJSONObject(i).getString(Title);
            String date = moviesArray.getJSONObject(i).getString(Date);
            movie.date = date.substring(0, 4);
            movie.vote_avrg = moviesArray.getJSONObject(i).getString(Vote_Avg);
            movie.id = moviesArray.getJSONObject(i).getString(Id);
            moviess.add(movie);

        }
        return moviess;
    }


    @Override
    protected ArrayList<Movies> doInBackground(Integer... ints) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String moviesJsonStr = null;

        try {

            if (ints[0] == 1)//popular
            {
                type = ints[0];
                Url = "http://api.themoviedb.org/3/movie/popular?api_key=558264949c51d81dcdf48bfe76ccc954";

            } else if (ints[0] == 2)//top rated
            {
                type = ints[0];
                Url = "http://api.themoviedb.org/3/movie/top_rated?api_key=558264949c51d81dcdf48bfe76ccc954";
            } else {
                String id = ints[0].toString();
                if (id.substring(id.length() - 2, id.length()).equals("00")) {// review
                    Log.v(id, "id inner");
                    id = id.substring(0, id.length() - 2);
                    Log.v(id, "id inner");
                    idd = id;
                    type = 3;
                    Url = "http://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=558264949c51d81dcdf48bfe76ccc954";

                } else if (id.substring(id.length() - 2, id.length()).equals("11")) {// trailers
                    Log.v(id, "id inner");
                    id = id.substring(0, id.length() - 2);
                    Log.v(id, "id inner");
                    idd = id;
                    type = 4;
                    Url = "http://api.themoviedb.org/3/movie/" + id + "/trailers?api_key=558264949c51d81dcdf48bfe76ccc954";

                }

            }
            URL url = new URL(Url);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }

            moviesJsonStr = buffer.toString();


        } catch (IOException e) {
            Log.e("MainFragment", "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainFragment", "Error closing stream", e);
                }
            }
        }
        if (ints[0] == 1 || ints[0] == 2) {
            try {
                return getMoviesDataFromJson(moviesJsonStr);
            } catch (JSONException e) {

            }
        } else if (type == 3) {
            try {
                return getReviews(moviesJsonStr);
            } catch (JSONException e) {

            }
        } else if (type == 4) {
            try {
                return getTrailers(moviesJsonStr);
            } catch (JSONException e) {

            }
        }


        return null;
    }


    @Override
    protected void onPostExecute(ArrayList<Movies> moviess) {

        if (moviess != null) {
            if (type == 1 || type == 2) {
                movies.clear();
                for (Movies movie : moviess) {
                    movies.add(movie);
                    Log.v(movie.id ,"movvv");
                }
            }
            else if (type == 3) {
                Log.v("Entered", "reviews");
                for (int i = 0; i < movies.size(); i++) {
                    if (movies.get(i).id.equals(idd)) {
                        for (int j = 0; j < moviess.get(0).review.size(); j++) {
                            movies.get(i).review.add(moviess.get(0).review.get(j));
                            Log.v(movies.get(i).review.get(j).author, "reviews");
                        }

                    }
                }
            }
            else if (type == 4) {
                Log.v("Entered", "trailers");
                for (int i = 0; i < movies.size(); i++) {
                    if (movies.get(i).id.equals(idd)) {
                        for (int j = 0; j < moviess.get(0).trailers.size(); j++) {
                            movies.get(i).trailers.add(moviess.get(0).trailers.get(j));

                        }
                    }
                }
            }

        }

        if (listener != null) {
            listener.onDataParsed(movies);
        }


    }
}


