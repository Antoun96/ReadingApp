package where.example.com.deaconsschool;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);

        MainActivityFragment frag;
            if (savedInstanceState == null) {
            Bundle bund = new Bundle();
            frag = new MainActivityFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.add(R.id.container, frag);
            tran.commit();
        } else {
            Bundle bund = new Bundle();
            frag = new MainActivityFragment();
            frag.setArguments(bund);
            FragmentManager man = getFragmentManager();
            FragmentTransaction tran = man.beginTransaction();
            tran.replace(R.id.container, frag);
            tran.commit();
        }

    }

}
