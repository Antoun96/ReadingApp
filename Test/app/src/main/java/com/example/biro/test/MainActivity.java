package com.example.biro.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Contact c = new Contact(null,null,null);
        setContentView(R.layout.activity_main);
        DataBaseHelper dbh = new DataBaseHelper(this);
       Toast.makeText(this, dbh.addContact(),Toast.LENGTH_LONG).show();
    }
}
