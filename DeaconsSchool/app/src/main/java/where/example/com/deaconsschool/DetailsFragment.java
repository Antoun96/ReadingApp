package where.example.com.deaconsschool;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsFragment extends Fragment {
    MediaPlayer mediaPlayer = new MediaPlayer();
    int flag;
    Handler handler = new Handler();
    Runnable runnable;
    SeekBar seekBar;
    static String url;
    Hymn h;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        h = (Hymn) getArguments().getSerializable("kk");
        seekBar = (SeekBar) rootView.findViewById(R.id.seek_bar);

        final Button button = (Button) rootView.findViewById(R.id.audio);
        button.setText("تشغيل");
        flag = 0;
        TextView textarab = (TextView) rootView.findViewById(R.id.arabic);
        textarab.setText(h.arabic);
        TextView textcopt = (TextView) rootView.findViewById(R.id.coptic);
        textcopt.setText(h.coptic);

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
                if (flag == 0) {
                    button.setText("ايقاف");
                    flag = 1;
                    if (checkPermission() == true) {
                        startdownload(h.track);
                    }

                } else if (flag == 1) {
                    flag = 2;
                    button.setText("تشغيل");
                    mediaPlayer.pause();
                } else if (flag == 2) {
                    flag = 1;
                    button.setText("ايقاف");
                    mediaPlayer.start();
                    playCycle();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }


    /* Checks if external storage is available for read and write */
//    public boolean isExternalStorageWritable() {
//        String state = Environment.getExternalStorageState();
//        if (Environment.MEDIA_MOUNTED.equals(state)) {
//            return true;
//        }
//        return false;
//    }

    public Boolean checkPermission() {
        if (Build.VERSION.SDK_INT > LOLLIPOP) {
            if (checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }
            }
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startdownload(h.track);
        }
    }

    private void startdownload(final String track) {

        final FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference down = storageRef.child("Audio").child(track);

        //  File localFile = null;
        try {
            //localFile = File.createTempFile("Audio", "mp3");
            final File localFile = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), track);
            final File internal_storage_file = new File(getActivity().getFilesDir(), track);
            if (track.length() > 3) {
                if (localFile.exists()) {
                    if (localFile.length() > 0) {
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setLooping(true);

                        try {
                            Uri myUri = Uri.parse(localFile.getAbsolutePath());
                            mediaPlayer.setDataSource(getActivity().getApplicationContext(), myUri);
                            mediaPlayer.prepare();
                            seekBar.setMax(mediaPlayer.getDuration());
                            mediaPlayer.start();
                            playCycle();
                        } catch (IOException e) {
                        }
                    } else {
                        localFile.delete();
                    }

                }
                if (!localFile.exists()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Downloading....", Toast.LENGTH_SHORT).show();
                    localFile.createNewFile();
                    down.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getActivity().getApplicationContext(), "Downloaded", Toast.LENGTH_SHORT).show();
                            Uri myUri = Uri.parse(localFile.getAbsolutePath()); // initialize Uri here
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mediaPlayer.setLooping(true);
                            try {
                                mediaPlayer.setDataSource(getActivity().getApplicationContext(), myUri);
                                mediaPlayer.prepare();
                                seekBar.setMax(mediaPlayer.getDuration());
                                mediaPlayer.start();
                                playCycle();
                            } catch (IOException e) {
                            }


//                            Log.i("ana hna 2", "startdownload: ");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
//                            Log.i("ana hna 3", "startdownload: ");
                            localFile.delete();
                        }
                    });
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "File Not Found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playCycle() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable, 1000);
        }

    }
}

// da ay kalaaaam


//        Log.i("ana hna 1", "startdownload: ");
//        down.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(getActivity().getApplicationContext(),"Downloded",Toast.LENGTH_SHORT).show();
//                Log.i("ana hna 2", "startdownload: ");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Log.i("ana hna 3", "startdownload: ");
//
//            }
//        });


//    private void fetchAudioUrlFromFirebase(String link , String name) {
//        final FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();
//        StorageReference pathReference = storageRef.child(name+".mp3");
//        StorageReference httpsReference = storage.getReferenceFromUrl(link);
//        StorageReference islandRef = storageRef.child(name+".mp3");
//        try {
//            final File localFile = File.createTempFile("audio", "mp3");
//            islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    // Local temp file has been created
//                    Uri myUri = Uri.fromFile(localFile); // initialize Uri here
//                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    try {
//                        mediaPlayer.setDataSource(getActivity().getApplicationContext(),myUri);
//                        mediaPlayer.prepare();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }}).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle any errors
//                }
//            });
//        }
//        catch (IOException e)
//        {
//
//        }
//
//    }
//    @Override
//    public void onPrepared(MediaPlayer mp) {
//        mp.start();
//    }





