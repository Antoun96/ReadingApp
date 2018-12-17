package where.example.com.moviesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements DataParsedListener {
    static ArrayList<Movies> movies = new ArrayList<>();
    public CustomAdapter adapter;
    RecyclerView recyclerView;


    GridView grid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        int url = openPreferredOrder();
        if (url == 5)
        {
            ArrayList<Movies>tmp = new ArrayList<>();
            tmp= DataBaseHelper.getInstance(getActivity()).getAllMoviesView();
            onSavedData(tmp);
            Log.v("DB2in", "in");
        }
        else {
            FetchMovies moviesTask = new FetchMovies();
            moviesTask.setOnDataParsed(this);
            moviesTask.execute(url);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.movie_grid);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter(getActivity(), movies);
        recyclerView.setAdapter(adapter);



        return rootView;

    }


    @Override
    public void onDataParsed(ArrayList<Movies> m) {
        this.movies = m;
        adapter = new CustomAdapter(getActivity(), movies);
        recyclerView.setAdapter(adapter);
    }

    public void onSavedData (ArrayList<Movies>m)
    {
        this.movies = m;
        adapter = new CustomAdapter(getActivity(), movies);
        recyclerView.setAdapter(adapter);
    }

    private Integer openPreferredOrder() {
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(getContext());

        String order = sharedPrefs.getString(getString(R.string.pref_order_key),getString(R.string.pref_top));

        Log.v("shared",order);
        int url;
        if (order.equals("popular"))
        {
            url = 1;
            return url;
        }
        else if (order.equals("top rated"))
        {
            url = 2;
            return url;
        }
        else {
            url = 5;
            return url;
        }

    }
}
