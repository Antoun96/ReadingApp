package where.example.com.moviesapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DetailsFragment frag ;
        Movies mov = (Movies)getIntent().getSerializableExtra(CustomAdapter.SER_KEY);
        if (savedInstanceState == null)
        {
            Bundle bund = new Bundle();
            bund.putSerializable("k",mov);
            frag = new DetailsFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.add(R.id.container , frag );
            tran.commit();
        }
        else
        {
            Bundle bund = new Bundle();
            bund.putSerializable("k",mov);
            frag = new DetailsFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.replace(R.id.container , frag);
            tran.commit();
        }


//        Log.v("movieeee2", mov.overview);

//        view(mov);
    }

//   public void view(Movies movie)
//   {
//        ImageView imageView = (ImageView) findViewById(R.id.image_detail);
//       Picasso.with(getApplicationContext()).load(movie.moviePoster).into(imageView);
//        TextView textlabel = (TextView)findViewById(R.id.text_label_details);
//       textlabel.setText(movie.title);
//        TextView textover=(TextView)findViewById(R.id.textover);
//       textover.setText(movie.overview);
//       TextView date = (TextView)findViewById(R.id.date);
//       date.setText(movie.date);
//
//
//
//   }
}
