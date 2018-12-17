package where.example.com.deaconsschool;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfoCourseFragment extends Fragment {
    static String ads;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    static String info = new String();
    public InfoCourseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_info_course, container, false);
        ads = (String) getArguments().getSerializable("kkey");
        final TextView textcopt = (TextView) rootView.findViewById(R.id.coptic);
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("articles").child(ads)
                .addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                               Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                                               for (DataSnapshot child : children) {
                                                   info = child.getValue(String.class);
                                                       textcopt.setText(info);
                                                   }
                                               }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                                       });



        return rootView;
    }
}
