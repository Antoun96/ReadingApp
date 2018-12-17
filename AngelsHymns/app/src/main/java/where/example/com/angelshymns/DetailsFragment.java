package where.example.com.angelshymns;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.ArrayList;

import static android.R.attr.numColumns;
import static android.R.attr.verticalDivider;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }
    RecyclerView recyclerView;
    GridCustomAdapter adapter ;
    Hymn hymn = new Hymn();
    ArrayList<String>hymns = new ArrayList<>();
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable ;
    int flag ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        hymn = (Hymn) getArguments().getSerializable("k");

        Log.v("hymn details fragement", hymn.content);


        hymns.add(hymn.content);
        hymns.add(hymn.content_coptic);

        handler = new Handler();
        seekBar = (SeekBar) rootView.findViewById(R.id.seek_bar);

        final Button button = (Button) rootView.findViewById(R.id.audio);
        button.setText("تشغيل");
        flag = 0;

        recyclerView = (RecyclerView) rootView.findViewById(R.id.hymns_grid);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GridCustomAdapter(getActivity(), hymns);
        recyclerView.setAdapter(adapter);

        switch (hymn.id) {
            case 11:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kg11);
                break;
            case 12:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kg12);
                break;
            case 13:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kg13);
                break;
            case 14:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kg14);
                break;
            case 15:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kg15);
                break;
            case 16:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kg16);
                break;
            case 111:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.o111);
                break;
            case 112:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.o112);
                break;
            case 113:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.o113);
                break;
            case 114:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.o114);
                break;
            case 115:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.o115);
                break;
            case 116:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.o116);
                break;
            case 201:
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.m201);
                break;
            case 202:
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.m202);
                break;
            case 203:
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.m203);
                break;
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(true);
        seekBar.setMax(mediaPlayer.getDuration());
//        playCycle();
//        mediaPlayer.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if (input) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag ==0) {
                    flag = 1;
                    button.setText("ايقاف");
                    mediaPlayer.start();
                    playCycle();
                }
                else if (flag ==1)
                {
                    flag = 0;
                    button.setText("تشغيل");
                    mediaPlayer.pause();
                }
            }
        });

        return rootView;
    }


    public void playCycle()
    {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying())
        {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable , 1000);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mediaPlayer.start();
//        playCycle();
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        mediaPlayer.pause();
//
//    }
}
