package where.example.com.deaconsschool;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public final static String SER_KEY = "where.example.com.deaconsschool.ser";
    public CustomAdapter adapter;
    RecyclerView recyclerView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    ArrayList<String> levels = new ArrayList<>();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView image = (ImageView) rootView.findViewById(R.id.crossimage);
        image.setImageResource(R.drawable.cross);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.hymns_levels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference();
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        databaseReference = database.getReference();
        databaseReference.child("levels").child("all_levels").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    String name = child.getValue(String.class);
                    if (!levels.contains(name)) {
                        levels.add(name);
                    }
                }
                adapter = new CustomAdapter(getActivity(), levels, new CustomOnClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        final String lvl_name = levels.get(position);
                        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("levels");
                        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (snapshot.hasChild(lvl_name)) {
                                    Intent intent = new Intent(getActivity(), InnerLevel.class);
                                    intent.putExtra(SER_KEY, lvl_name);
                                    Log.v("hymn adapter12", lvl_name);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(getActivity(), InfoCourse.class);
                                    intent.putExtra(SER_KEY, lvl_name);
                                    Log.v("hymn adapter11", lvl_name);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return rootView;
    }
}
