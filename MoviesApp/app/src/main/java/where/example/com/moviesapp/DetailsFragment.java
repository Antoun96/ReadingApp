package where.example.com.moviesapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsFragment extends Fragment {

    Movies movie;
    ArrayList<Movies> movies = new ArrayList<>();
    Boolean isFavorite;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(savedInstanceState==null)
        {
            isFavorite=false;
        }
        else
        {
            isFavorite=savedInstanceState.getBoolean("is favorite");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is favorite",isFavorite);
    }
//    ExpandableListView expandableListView;
//    ExpandableListAdapter expandableListAdapter;
//    List <String> expandableListTitle ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        if(MainActivity.isTwoPane()) {
            movie = (Movies) getArguments().getSerializable("key");
        }
        else
        {
            movie = (Movies) getArguments().getSerializable("k");
        }
        Log.v("movieeee2", movie.overview);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.image_detail);
        Picasso.with(getActivity()).load(movie.moviePoster).into(imageView);
        TextView textlabel = (TextView) rootView.findViewById(R.id.text_label_details);
        textlabel.setText(movie.title);
        TextView textover = (TextView) rootView.findViewById(R.id.textover);
        textover.setText(movie.overview);
        TextView date = (TextView) rootView.findViewById(R.id.date);
        date.setText(movie.date);
        TextView vote = (TextView) rootView.findViewById(R.id.vote);
        vote.setText(movie.vote_avrg + " / 10");

        Button button = (Button) rootView.findViewById(R.id.review_but);
        button.setOnClickListener(new View.OnClickListener() {
            ReviewFragment frag;

            public void onClick(View v) {
                if (MainActivity.isTwoPane()) {
                    Bundle bund = new Bundle();
                    bund.putSerializable("id", movie.id);
                    frag = new ReviewFragment();
                    frag.setArguments(bund);
                    FragmentManager man = getFragmentManager();
                    FragmentTransaction tran = man.beginTransaction();
                    tran.replace(R.id.detailcontent, frag);
                    tran.commit();

                } else {
                    Bundle bund = new Bundle();
                    bund.putSerializable("id", movie.id);
                    frag = new ReviewFragment();
                    frag.setArguments(bund);
                    FragmentManager man = getFragmentManager();
                    FragmentTransaction tran = man.beginTransaction();
                    tran.replace(R.id.container, frag);
                    tran.commit();
                }
            }
        });
        Button button2 = (Button) rootView.findViewById(R.id.trailers_but);
        button2.setOnClickListener(new View.OnClickListener() {
            TrailersFragment frag2;

            public void onClick(View v) {
                if(MainActivity.isTwoPane())
                {
                    Bundle bund = new Bundle();
                    bund.putSerializable("id2", movie.id);
                    frag2 = new TrailersFragment();
                    frag2.setArguments(bund);
                    FragmentManager man = getFragmentManager();
                    FragmentTransaction tran = man.beginTransaction();
                    tran.replace(R.id.detailcontent, frag2);
                    tran.addToBackStack(null);
                    tran.commit();
                }
                else {
                    Bundle bund = new Bundle();
                    bund.putSerializable("id2", movie.id);
                    frag2 = new TrailersFragment();
                    frag2.setArguments(bund);
                    FragmentManager man = getFragmentManager();
                    FragmentTransaction tran = man.beginTransaction();
                    tran.replace(R.id.container, frag2);
                    tran.addToBackStack(null);
                    tran.commit();
                }

            }
        });

        final Button favorite = (Button)rootView.findViewById(R.id.favorite_btn);
        if (DataBaseHelper.getInstance(getActivity()).found(movie)) {
            favorite.setText("Remove From Favourites");
            isFavorite = true;
        }
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            DataBaseHelper.getInstance(getActivity()).deleteMovies(movie);
                            isFavorite = false;
                        }
                    };
                    Thread t = new Thread(r);
                    t.start();
                    Toast.makeText(getActivity(), movie.title + " removed from your favourites", Toast.LENGTH_LONG).show();
                    favorite.setText("Add To Favorites");


                } else {
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            DataBaseHelper.getInstance(getActivity()).addMovies(movie);
                            isFavorite = true;
                        }
                    };
                    Thread t = new Thread(r);
                    Toast.makeText(getActivity(), movie.title + " added to your favourites", Toast.LENGTH_LONG).show();
                    t.start();
                    favorite.setText("Remove From Favourite");

                }

            }
        });



        return rootView;

    }
}




