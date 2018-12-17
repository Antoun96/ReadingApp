package where.example.com.cashier;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    ArrayList<List> lists = new ArrayList<>();
    public CustomAdapter adapter ;
    RecyclerView recyclerView;
    List list = new List();

    public MainActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        // list of options
        list.name = "Buy";
        list.image= R.drawable.buy;
        lists.add(list);
        Log.v(lists.get(0).name,"name 0");
        list = new List();
        list.name="Sell";
        list.image = R.drawable.sell;
        lists.add(list);
        Log.v(lists.get(0).name,"name 0");
        Log.v(lists.get(1).name,"name 1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        recyclerView = (RecyclerView)rootView.findViewById(R.id.versions_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter(getActivity(),lists);
        recyclerView.setAdapter(adapter);

        return rootView;

    }
}
