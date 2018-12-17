package where.example.com.deaconsschool;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class InfoCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_course);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        InfoCourseFragment frag;
        String lvl_name = (String) getIntent().getSerializableExtra(MainActivityFragment.SER_KEY);
        Log.v("hymn details class 1", lvl_name);
        if (savedInstanceState == null) {
            Bundle bund = new Bundle();
            bund.putSerializable("kkey", lvl_name);
            frag = new InfoCourseFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.add(R.id.containerr, frag);
            tran.commit();
        } else {
            Bundle bund = new Bundle();
            bund.putSerializable("kkey", lvl_name);
            frag = new InfoCourseFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.replace(R.id.containerr, frag);
            tran.commit();
        }

    }

}
