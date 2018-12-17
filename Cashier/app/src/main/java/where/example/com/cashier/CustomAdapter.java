package where.example.com.cashier;

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
    private ArrayList<List> lists;
    private Context context;

    public CustomAdapter(Context context,ArrayList<List> tmp) {
        lists=tmp;
        this.context = context;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.button_image, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder viewHolder, int position) {

        viewHolder.android_name.setText("Name: "+lists.get(position).name);
//        viewHolder.android_api.setText("API: "+String.valueOf(android.get(position).api_level));
//        viewHolder.android_version.setText("Version"+android.get(position).version.toString());
//        Picasso.with(context).load(lists.get(position).image).into(viewHolder.img_android);
        viewHolder.img_android.setImageResource(lists.get(position).image);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView android_name;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            android_name = (TextView)view.findViewById(R.id.text_label);
            img_android = (ImageView) view.findViewById(R.id.image_item);
        }
    }

}
