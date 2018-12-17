package where.example.com.deaconsschool;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DetailsFragment frag;
        Hymn h = (Hymn) getIntent().getSerializableExtra(HymnOptionsFragment.SER_HYMN);
        Log.v("hymn details class", h.name);
        if (savedInstanceState == null) {
            Bundle bund = new Bundle();
            bund.putSerializable("kk", h);
            frag = new DetailsFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.add(R.id.containerr, frag);
            tran.commit();
        } else {
            Bundle bund = new Bundle();
            bund.putSerializable("kk", h);
            frag = new DetailsFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.replace(R.id.containerr, frag);
            tran.commit();
        }

    }

}
