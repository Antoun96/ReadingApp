package where.example.com.moviesapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.ArrayList;

/**
 * Created by Antoun on 11/19/2016.
 */
public class CustomListAdapter extends RecyclerView.Adapter <CustomListAdapter.ViewHolder> {
private Movies movies;
private Activity context;

public CustomListAdapter(Activity context,Movies tmp) {
        movies=tmp;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_rev, viewGroup, false);
        return new ViewHolder(view);
        }



    @Override
public void onBindViewHolder(CustomListAdapter.ViewHolder viewHolder , int position) {

    viewHolder.author.setText("Author: " + movies.review.get(position).author);
    viewHolder.details.setText("Review: " + movies.review.get(position).review);

        }

@Override
public int getItemCount() {
        return movies.review.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView author;
    private TextView details;
    public ViewHolder(View view) {
        super(view);

        author = (TextView)view.findViewById(R.id.listtitle);
//        details = (TextView)view.findViewById(R.id.listdetail);

    }
}

}