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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class InnerLevelFragment extends Fragment {
    public final static String SR_KEY = "where.example.com.deaconsschol.ser";
    public InnerLevelFragment() {
    }
    public CustomAdapter adapter ;
    RecyclerView recyclerView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    String lvlname = new String();
    static String stat_lvl_name;
    ArrayList<String>lvls = new ArrayList<>();

    int flag ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_innerlevel, container, false);

        lvlname = (String) getArguments().getSerializable("k");

//        Log.v("hymn details fragement", hymn.content);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.inner_lvls);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DatabaseReference databaseReference = database.getReference();
        stat_lvl_name = lvlname;
        databaseReference.child("levels").child(stat_lvl_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children ) {
                    String name = child.getValue(String.class);
                    if (!lvls.contains(name))
                     {
                      lvls.add(name);
                     }
                    //Log.i("el name", name);
                    //if (!lvls.isEmpty()) {
                        adapter = new CustomAdapter(getActivity(), lvls, new CustomOnClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                String lvl_name = lvls.get(position);
                                Intent intent = new Intent(getActivity(), HymnOptions.class);
                                intent.putExtra(SR_KEY, lvl_name);
                                Log.v("hymn adapter1", lvl_name);
                                startActivity(intent);
                            }
                        });
                    //}
//                    else if (lvls.isEmpty()){
//                        Intent intent = new Intent(getActivity(), InfoCourse.class);
//                        intent.putExtra(SR_KEY, stat_lvl_name);
//                        Log.v("hymn adapter2", stat_lvl_name);
//                        startActivity(intent);
//                    }
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return rootView;
    }




}
