package where.example.com.angelshymns;

import android.content.Intent;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public final static String SER_KEY = "where.example.com.angelshymns.ser";
    int choice =0;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView image = (ImageView)rootView.findViewById(R.id.crossimage);
        image.setImageResource(R.drawable.cross);
        Button btn = (Button) rootView.findViewById(R.id.kg);
        Button btn2 = (Button) rootView.findViewById(R.id.oula);
        Button btn3 = (Button)rootView.findViewById(R.id.mawhob);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_kg(v);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_oula(v);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_mawhob(v);
            }
        });
        ImageView sign = (ImageView)rootView.findViewById(R.id.sign);
        sign.setImageResource(R.drawable.antoun);
        return rootView;
    }
    public void go_kg(View v)
    {
        choice = 1;
        Intent intent = new Intent(getActivity(), HymnsOptions.class);
        intent.putExtra(SER_KEY , choice);
        startActivity(intent);
    }
    public void go_oula(View v)
    {
        choice = 2;
        Intent intent = new Intent(getActivity(), HymnsOptions.class);
        intent.putExtra(SER_KEY , choice);
//        Log.v("movieeee", movie.title);
        startActivity(intent);
    }

    public void go_mawhob(View v)
    {
        choice = 3;
        Intent intent = new Intent(getActivity(), HymnsOptions.class);
        intent.putExtra(SER_KEY , choice);
//        Log.v("movieeee", movie.title);
        startActivity(intent);
    }
}
