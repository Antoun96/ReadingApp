package where.example.com.deaconsschool;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class InnerLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innerlevel);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        InnerLevelFragment frag ;
        String lvl_name = (String) getIntent().getSerializableExtra(MainActivityFragment.SER_KEY);
        Log.v("hymn details class" , lvl_name);
        if (savedInstanceState == null) {
            Bundle bund = new Bundle();
            bund.putSerializable("k", lvl_name);
            frag = new InnerLevelFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.add(R.id.container, frag);
            tran.commit();
        }
        else
        {
            Bundle bund = new Bundle();
            bund.putSerializable("k", lvl_name);
            frag = new InnerLevelFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.replace(R.id.container, frag);
            tran.commit();
        }

    }

}
