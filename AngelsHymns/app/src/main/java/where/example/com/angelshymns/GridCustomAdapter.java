package where.example.com.angelshymns;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Antoun on 4/10/2017.
 */

public class GridCustomAdapter extends RecyclerView.Adapter<GridCustomAdapter.ViewHolder> {

    private ArrayList<String> hymns;
    private Activity activity;

    public GridCustomAdapter(Activity activity, ArrayList<String> tmp) {
        this.hymns = tmp;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.android_content.setText(hymns.get(position));
        viewHolder.android_content.setText(hymns.get(position));


    }

    @Override
    public int getItemCount() {
        return hymns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView android_content;

        public ViewHolder(View view) {
            super(view);

            android_content = (TextView) view.findViewById(R.id.text_list);
        }
    }

}




