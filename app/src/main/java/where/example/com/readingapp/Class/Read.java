package where.example.com.readingapp.Class;

import java.util.ArrayList;

/**
 * Created by Antoun on 12/16/2018.
 */

public class Read {
    public int id;
    public int from;
    public int to;
    public int user_id;

    public float percentage(ArrayList<Read> selected_read) {
        int start = 1;
        int end = 70;
        int num_of_readed_pages = 0;
        for (int i = 0; i < selected_read.size(); i++) {
            if (selected_read.get(i).from >= start) {
                start = selected_read.get(i).from;
                end = selected_read.get(i).to;
                num_of_readed_pages = end - start + num_of_readed_pages;
            } else if (selected_read.get(i).from < start) {
                end = selected_read.get(i).to;
                num_of_readed_pages = end - start + num_of_readed_pages;
            }
            start = end;
        }
        float percentage = (num_of_readed_pages + 1) / 70.0f;
        return percentage;
    }

}
