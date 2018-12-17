package where.example.com.androidversions;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

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
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    static ArrayList<Android> andr = new ArrayList<>();
    public CustomAdapter adapter ;
    ListView list;
    RecyclerView recyclerView;
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onStart() {
        super.onStart();
        FetchVersions Task = new FetchVersions();
        Task.execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.versions_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter(getActivity(),andr);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public class FetchVersions extends AsyncTask<Void, Void, ArrayList<Android>> {

        private ArrayList<Android> getDataFromJson(String JsonStr)
                throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String Versions = "versions";
            final String Poster = "image";
            final String Name = "name";
            final String Andro = "android";
            final String Api = "api_level";
            final String version = "version";






            JSONObject appsJson = new JSONObject(JsonStr);
            JSONArray AndroidArray = appsJson.getJSONArray(Andro);

            JSONObject versionObject =AndroidArray.getJSONObject(1);
            JSONArray versionArray = versionObject.getJSONArray(Versions);
            ArrayList<Android>and = new ArrayList<Android>();

            for (int i = 0; i < 11; i++) {
                Android obj = new Android();
                obj.url = versionArray.getJSONObject(i).getString(Poster);
                obj.name = versionArray.getJSONObject(i).getString(Name);
                obj.api_level=versionArray.getJSONObject(i).getInt(Api);
                obj.version=versionArray.getJSONObject(i).getDouble(version);
                and.add(obj);

            }
            return and;
        }


        @Override
        protected ArrayList<Android> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String JsonStr = null;

            try {
                URL url = new URL("https://gist.githubusercontent.com/anonymous/0adcc7e908f7bf2dd9380a89f13c9b28/raw/33c9520e0b9c38d4a3f56e6c8fb7a74d27dd87c4/blob.json");

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

                JsonStr = buffer.toString();


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
            try {
                return getDataFromJson(JsonStr);
            } catch (JSONException e) {

            }
            return null;
        }
        @Override
        protected void onPostExecute(ArrayList<Android> and) {

            if (and!=null) {

                andr.clear();

                for (Android x : and) {

                    andr.add(x);
                }
                adapter = new CustomAdapter(getActivity(), andr);

            }
            recyclerView.setAdapter(adapter);
        }
    }

}
