package where.example.com.readingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.ArrayList;

import where.example.com.readingapp.Class.User;


public class ListAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<User> users;

    public ListAdapter(Context context, ArrayList<User> users) {
        this.mContext = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        final TextView idTextView = convertView.findViewById(R.id.txt_id);
        final TextView nameTextView = convertView.findViewById(R.id.txt_name);

        idTextView.setText(String.valueOf(users.get(position).id));
        nameTextView.setText((users.get(position).name));

        return convertView;
    }

}

