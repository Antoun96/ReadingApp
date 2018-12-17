package where.example.com.moviesapp;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment implements DataParsedListener {

//    RecyclerView recyclerView ;
//    CustomListAdapter adapter;
    Movies movie = new Movies();
    ArrayList<Movies>movies = new ArrayList<>();

    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = (String)getArguments().getSerializable("id");
        Log.v(id, "id2");
        id = id+"00";
        Log.v(id, "id2");
        int idd = Integer.parseInt(id);

        FetchMovies moviesTask = new FetchMovies();
        moviesTask.setOnDataParsed(this);
        moviesTask.execute(idd);

    }

    ListView lv;
    ArrayList<String> movie_string = new ArrayList<>();
    ArrayAdapter<String> in;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);


//    recyclerView = (RecyclerView) rootView.findViewById(R.id.list_rev);
//    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//    recyclerView.setLayoutManager(layoutManager);
//
//    adapter = new CustomListAdapter(getActivity(), movie);
//    recyclerView.setAdapter(adapter);

        in = new ArrayAdapter<String>(getActivity(), R.layout.listitem_rev, R.id.listtitle, movie_string);
        lv = (ListView) rootView.findViewById(R.id.list_rev);
        lv.setAdapter(in);
        return rootView;
    }

    @Override
    public void onDataParsed(ArrayList<Movies> m) {
        this.movies = m;
        movie = movies.get(0);

        for (int i = 0 ; i<movie.review.size() ; i++) {
            movie_string.add(movie.review.get(i).author+" :\n"+movie.review.get(i).review);
        }

        lv.setAdapter(in);

//         adapter = new CustomListAdapter(getActivity(), movie);
//         recyclerView.setAdapter(adapter);


    }
}
