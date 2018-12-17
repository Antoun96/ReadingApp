package where.example.com.moviesapp;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antoun on 10/11/2016.
 */
public class Movies implements Serializable{
    public String moviePoster;
    public String overview;
    public String title;
    public String date;
    public String vote_avrg;
    public String id;
    public ArrayList<Review> review = new ArrayList<>();
    public ArrayList<String> trailers= new ArrayList<>();
}
