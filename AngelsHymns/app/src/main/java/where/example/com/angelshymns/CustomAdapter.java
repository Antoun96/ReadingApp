package where.example.com.angelshymns;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Antoun on 10/6/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Hymn> android;
    private Activity activity;
    public final static String SER_KEY = "where.example.com.angelshymns.ser";

    public CustomAdapter(Activity activity, ArrayList<Hymn> tmp) {
        android=tmp;
        this.activity = activity;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.android_name.setText(android.get(position).name);
        Log.v("Name", android.get(position).name);

        viewHolder.android_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hymn h = android.get(position);

                    Intent intent = new Intent(activity, Details.class);
                    intent.putExtra(SER_KEY, h);
                    Log.v("hymn adapter", h.name);
                    activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView android_name;

        public ViewHolder(View view) {
            super(view);

            android_name = (Button) view.findViewById(R.id.button_label);

        }
    }


}
