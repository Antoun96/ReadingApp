package where.example.com.moviesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrailersFragment extends Fragment implements DataParsedListener {

    Movies movie = new Movies();
    ArrayList<Movies>movies = new ArrayList<>();
    ListView lv;
    ArrayList<String> movie_string = new ArrayList<>();
    ArrayList<String> movie_titles = new ArrayList<>();
    ArrayAdapter<String> in;
    String id;

    public TrailersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id= (String)getArguments().getSerializable("id2");
        Log.v(id, "id2");
        String idtrail = id+"11";
        Log.v(id, "id2");
        int idd = Integer.parseInt(idtrail);
        FetchMovies moviesTask = new FetchMovies();
        moviesTask.setOnDataParsed(this);
        moviesTask.execute(idd);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_trailers, container, false);
        Log.v("1" , "where");

        in = new ArrayAdapter<String>(
                getActivity(), R.layout.listitem_trailers, R.id.listitem_trail, movie_titles);

        lv = (ListView) rootView.findViewById(R.id.list_trail);
        lv.setAdapter(in);
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Log.v("clicked ? " , "yes");
               startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(movie.trailers.get(i))));
           }
       });

            return rootView;
        }
        @Override
    public void onDataParsed(ArrayList<Movies> m) {
        this.movies = m;
            for (int i = 0 ; i<movies.size() ; i++) {
                if (movies.get(i).id.equals(id)) {
                    movie = movies.get(i);
                    for (int j = 0; j < movie.trailers.size(); j++) {
                        movie_titles.add("Trailer "+(j+1));
                    }
                    for (int j = 0; j < movie.trailers.size(); j++) {
                        movie_string.add(movie.trailers.get(j));
                    }
                }
            }

        lv.setAdapter(in);

    }

}
