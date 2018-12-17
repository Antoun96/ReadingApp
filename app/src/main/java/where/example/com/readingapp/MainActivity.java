package where.example.com.readingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import where.example.com.readingapp.Class.Read;
import where.example.com.readingapp.Class.User;
import where.example.com.readingapp.DB.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    public TextView result, id_order, list_title;
    public EditText input;
    public Button submit;
    public ArrayList<User> users = new ArrayList<>();
    public Read read;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Read> reads = new ArrayList<>();
        input = findViewById(R.id.input);
        submit = findViewById(R.id.submit);
        result = findViewById(R.id.percentage);
        id_order = findViewById(R.id.id_order);

        users = DataBaseHelper.getInstance(this).getAllUsers();

        // setting data if it is the first time then get it , we can also use shared preferences in this condition
        if (users.size() == 0) {
            DataBaseHelper.getInstance(this).addUsers();
            DataBaseHelper.getInstance(this).addReadPages();
            users = DataBaseHelper.getInstance(this).getAllUsers();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checking for unempty EditText
                if (input.getText().toString().equals("")) {
                    input.setError(Html.fromHtml("<font color=#ffffff>Please Enter valid ID</font>"));
                    return;
                } else {
                    // getting reads Percentage
                    ArrayList<Read> selected_reads;
                    int id = Integer.valueOf(input.getText().toString());
                    selected_reads = DataBaseHelper.getInstance(MainActivity.this).getReadsById(id);
                    read = new Read();
                    float percentage = read.percentage(selected_reads);
                    result.setText("Percentage = " + String.format("%.3f", percentage));

                    //TreeMap automatically sort in ascending order , so we will reverse it
                    // getting all users readings in order to sort them
                    TreeMap<Integer, Float> id_perc = new TreeMap<>(Collections.reverseOrder());
                    id_perc.put(Integer.valueOf(input.getText().toString()), percentage);
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).id != id) {
                            ArrayList<Read> reads = new ArrayList<>();
                            reads = DataBaseHelper.getInstance(MainActivity.this).getReadsById(users.get(i).id);
                            id_perc.put(users.get(i).id, read.percentage(reads));
                        }
                    }

                    //getting list sorted
                    ArrayList<User> ordered_users = new ArrayList<>();
                    int order = 0;
                    int index = 0;
                    for (Map.Entry<Integer, Float> entry : id_perc.entrySet()) {
                        User user = new User();
                        user.id = entry.getKey();
                        user.name = DataBaseHelper.getInstance(MainActivity.this).getUserById(user.id);
                        ordered_users.add(user);

                        // getting its order
                        if (user.id == id) {
                            order = index + 1;
                        }
                        index++;
                    }
                    list_title = findViewById(R.id.txt_list_title);
                    list_title.setText("Users List :");
                    listView = findViewById(R.id.users_list);
                    ListAdapter listAdapter = new ListAdapter(MainActivity.this, ordered_users);
                    listView.setAdapter(listAdapter);
                    id_order.setText("User Order = " + Integer.toString(order));
                }
            }
        });
    }
}
