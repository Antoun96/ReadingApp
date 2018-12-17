package where.example.com.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static boolean isTwoPane() {
        return twoPane;
    }

    private static boolean twoPane;
    private MainActivityFragment mainActivityFragment;
    android.support.v4.app.FragmentManager man;
    android.support.v4.app.FragmentTransaction tran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            mainActivityFragment = new MainActivityFragment();
            man = getSupportFragmentManager();
            tran = man.beginTransaction();
            tran.add(R.id.contentmain,mainActivityFragment,"MainFragment").commit();

        }
        else
        {
            mainActivityFragment = new MainActivityFragment();
            man = getSupportFragmentManager();
            tran = man.beginTransaction();
            tran.replace(R.id.contentmain, mainActivityFragment, "MainFragment").commit();
        }

        if(findViewById(R.id.detailcontent)== null)
        {
            twoPane = false;
        }
        else
        {
            twoPane = true;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}
