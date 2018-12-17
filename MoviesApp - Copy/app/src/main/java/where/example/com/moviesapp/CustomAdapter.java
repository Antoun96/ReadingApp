package where.example.com.moviesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Antoun on 10/6/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<Movies> movies;
    private Activity activity;
    public  final static String SER_KEY = "where.example.com.moviesapp.ser";

    public CustomAdapter(Activity activity,ArrayList<Movies> tmp) {
        this.movies=tmp;
        this.activity = activity;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_image, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder viewHolder, final int position) {

        Picasso.with(activity).load(movies.get(position).moviePoster).resize(250,400).into(viewHolder.img_android);
        Log.v("Picasso", "Loaded");
        viewHolder.img_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
              Movies movie = movies.get(position);

                Intent intent = new Intent(activity, Details.class);
                intent.putExtra(SER_KEY, movie);
                Log.v("movieeee", movie.title);
                activity.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            img_android = (ImageView) view.findViewById(R.id.image_view);
        }
    }

}

