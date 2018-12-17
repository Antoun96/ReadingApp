package where.example.com.deaconsschool;

import android.app.Activity;
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

    private ArrayList<String> android;
    private Activity activity;
    CustomOnClickListener listener;
    public final static String SER_KEY = "where.example.com.deaconsschool.ser";

    public CustomAdapter(Activity activity, ArrayList<String> tmp , CustomOnClickListener listener) {
        android=tmp;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.android_name.setText(android.get(position));
        Log.v("Name", android.get(position));

        viewHolder.android_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(v , viewHolder.getAdapterPosition());


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
//    String lvl_name = android.get(position);
//
//    Intent intent = new Intent(activity,InnerLevel.class);
//intent.putExtra(SER_KEY,lvl_name);
//        Log.v("hymn adapter",lvl_name);
//        activity.startActivity(intent);