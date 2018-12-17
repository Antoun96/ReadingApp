package where.example.com.deaconsschool;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class HymnOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymn_options);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        HymnOptionsFragment frag;
        String lvl_name = (String) getIntent().getSerializableExtra(InnerLevelFragment.SR_KEY);
        Log.v("hymn details class", lvl_name);
        if (savedInstanceState == null) {
            Bundle bund = new Bundle();
            bund.putSerializable("kk", lvl_name);
            frag = new HymnOptionsFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.add(R.id.containerr, frag);
            tran.commit();
        } else {
            Bundle bund = new Bundle();
            bund.putSerializable("kk", lvl_name);
            frag = new HymnOptionsFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.replace(R.id.containerr, frag);
            tran.commit();
        }

    }

}

