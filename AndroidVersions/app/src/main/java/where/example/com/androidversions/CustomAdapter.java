package where.example.com.androidversions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Antoun on 10/6/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<Android> android;
    private Context context;

    public CustomAdapter(Context context,ArrayList<Android> tmp) {
        android=tmp;
        this.context = context;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder viewHolder, int position) {

        viewHolder.android_name.setText("Name: "+android.get(position).name);
        viewHolder.android_api.setText("API: "+String.valueOf(android.get(position).api_level));
        viewHolder.android_version.setText("Version"+android.get(position).version.toString());
        Picasso.with(context).load(android.get(position).url).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView android_name;
        private TextView android_version;
        private TextView android_api;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            android_name = (TextView)view.findViewById(R.id.text_label);
            android_api = (TextView)view.findViewById(R.id.text_api);
            android_version = (TextView)view.findViewById(R.id.text_vesion);
            img_android = (ImageView) view.findViewById(R.id.image_item);
        }
    }

}
