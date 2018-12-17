package com.example.antoun.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Antoun on 4/1/2016.
 */
public class insertion extends ActionBarActivity {
    DataBase db;
//    EditText editname , editemail,editpassword,editphone;
    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        db = new DataBase(this);

//        editname = (EditText)findViewById(R.id.editname);
//        editpassword = (EditText)findViewById(R.id.editpassword);
//        editemail = (EditText)findViewById(R.id.editemail);
//        editphone = (EditText)findViewById(R.id.editphone);
        btn = (Button)findViewById(R.id.build_btn_id);

//        AddData ();
        view();
    }
//    public void AddData ()
//    {
//        btn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean inserted = db.insertData(editname.getText().toString(), editemail.getText().toString(), editpassword.getText().toString(), editphone.getText().toString());
//                        if (inserted == true) {
//                            Toast.makeText(insertion.this, "Data Inserted", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(insertion.this, "Data not Inserted", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }
//        );
//    }
    public void view ()
    {
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor row = db.getData();
                        if (row.getCount() == 0) {
                            showMessages("Wrong", "Name or Password");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (row.moveToNext()) {
                                buffer.append("ID"+row.getString(0)+"\n");
                                buffer.append("name"+row.getString(1)+"\n");
                                buffer.append("email"+row.getString(2)+"\n");
                                buffer.append("password"+row.getString(3)+"\n");
                                buffer.append("phone"+row.getString(4)+"\n");

                            }
                            showMessages("Login", buffer.toString());
                        }
                    }
                }
        );
    }
    public void showMessages (String title , String Messages)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messages);
    }

}
