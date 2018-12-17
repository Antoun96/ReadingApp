package where.example.com.angelshymns;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DetailsFragment frag ;
        Hymn h = (Hymn)getIntent().getSerializableExtra(CustomAdapter.SER_KEY);
        Log.v("hymn details class" , h.name);
        if (savedInstanceState == null) {
            Bundle bund = new Bundle();
            bund.putSerializable("k", h);
            frag = new DetailsFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.add(R.id.container, frag);
            tran.commit();
        }
        else
        {
            Bundle bund = new Bundle();
            bund.putSerializable("k", h);
            frag = new DetailsFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.replace(R.id.container, frag);
            tran.commit();
        }

    }

}
