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
public class HymnOptionsFragment extends Fragment {

    static String inner_lvlname;
    RecyclerView recyclerView;
    public final static String SER_HYMN = "where.example.com.deaconsschool.ser";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    Hymn hymn;
    ArrayList<Hymn>hymns = new ArrayList<>();
    CustomAdapter adapter;
    ArrayList<String> lvls = new ArrayList<String>();
    public HymnOptionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_hymn_options, container, false);
        inner_lvlname = (String) getArguments().getSerializable("kk");

//        Log.v("hymn details fragement", hymn.content);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.hymns_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DatabaseReference databaseReference = database.getReference();

        databaseReference.child("hymns").child(InnerLevelFragment.stat_lvl_name).child(inner_lvlname)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children ) {
                    hymn = child.getValue(Hymn.class);
                    if (!hymns.contains(hymn)) {
                        hymns.add(hymn);
                        Log.i("el2name", hymn.arabic);
                        lvls.add(hymn.name);
                    }
                    adapter = new CustomAdapter(getActivity(),lvls , new CustomOnClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            int p = lvls.indexOf(lvls.get(position));
                            Hymn h = hymns.get(p);
                            Intent intent = new Intent(getActivity(),Details.class);
                            intent.putExtra(SER_HYMN , h);
                            //Log.v("hymn adapter",lvl_name);
                            startActivity(intent);
                        }
                    });
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
