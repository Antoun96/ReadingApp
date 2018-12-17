package where.example.com.angelshymns;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class HymnsOptionsFragment extends Fragment {
    public CustomAdapter adapter ;
    RecyclerView recyclerView;
    public HymnsOptionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hymns_options, container, false);

        ImageView sign = (ImageView)rootView.findViewById(R.id.sign);
        sign.setImageResource(R.drawable.antoun);

        int choice ;
        choice = (Integer)getArguments().getSerializable("key");
        Log.v("choice", String.valueOf(choice));
        ArrayList<Hymn> hymns = new ArrayList<>();
//        DataBaseHelper.getInstance(getActivity()).deleteHymns();
        hymns= DataBaseHelper.getInstance(getActivity()).getAllHymnView();

        if (hymns.isEmpty())
        {
            DataBaseHelper.getInstance(getActivity()).addHymns();
            hymns= DataBaseHelper.getInstance(getActivity()).getAllHymnView();
        }
//        onSavedData(tmp);
        Log.v("DB2in", "in");
        Log.v("3add 2",String.valueOf(hymns.size()));
        ArrayList<Hymn> kg = new ArrayList<>();
        ArrayList<Hymn> oula = new ArrayList<>();
        ArrayList<Hymn> mawhob = new ArrayList<>();
        if (choice == 1)
        {
            for (int i = 0 ; i< hymns.size() ; i++ )
            {
                if (hymns.get(i).id <20)
                {
                    Hymn hymn = new Hymn();
                    hymn=hymns.get(i);
                    kg.add(hymn);
                }
            }
            recyclerView = (RecyclerView)rootView.findViewById(R.id.hymns_list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new CustomAdapter(getActivity(),kg);
            recyclerView.setAdapter(adapter);

        }
        else if (choice ==2)
        {
            for (int i = 0 ; i< hymns.size() ; i++ )
            {
                if (hymns.get(i).id >100 && hymns.get (i).id <200)
                {
                    oula.add(hymns.get(i));
                }
            }
            recyclerView = (RecyclerView)rootView.findViewById(R.id.hymns_list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new CustomAdapter(getActivity(),oula);
            recyclerView.setAdapter(adapter);
        }

        else if (choice ==3)
        {
            for (int i = 0 ; i< hymns.size() ; i++ )
            {
                if (hymns.get(i).id >200)
                {
                    mawhob.add(hymns.get(i));
                }
            }
            recyclerView = (RecyclerView)rootView.findViewById(R.id.hymns_list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new CustomAdapter(getActivity(), mawhob);
            recyclerView.setAdapter(adapter);
        }

             return rootView;
    }
}
