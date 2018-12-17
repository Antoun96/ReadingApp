package where.example.com.elmalaklibrary;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] mobileArray = {"بيع منتج" , "شراء منتج"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this.getActivity() ,R.layout.listview_item ,R.id.list_textview , mobileArray);
        ListView listview = (ListView)rootView.findViewById(R.id.listview);
        listview.setAdapter(adapter);
        return rootView;
    }

}
