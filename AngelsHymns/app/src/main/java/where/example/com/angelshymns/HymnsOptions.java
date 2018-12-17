package where.example.com.angelshymns;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;


public class HymnsOptions extends AppCompatActivity {
    int choice;
    HymnsOptionsFragment frag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymns_options);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        choice = (Integer)getIntent().getSerializableExtra(MainActivityFragment.SER_KEY);

        Log.v("keeey",String.valueOf(choice));
        Bundle bund = new Bundle();
        bund.putSerializable("key",choice);
        frag = new HymnsOptionsFragment();
        frag.setArguments(bund);
        FragmentManager man = getFragmentManager();
        FragmentTransaction tran = man.beginTransaction();
        tran.replace( R.id.container , frag );
        tran.commit();

    }



}

