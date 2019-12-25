package com.BlankSPace.blankrain;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AudioAttributes aA;
    private Dictionary<String, int[]> profiles = new Hashtable<String, int[]>();
    List<MediaPlayer> mPs = new ArrayList<MediaPlayer>();
    ArrayList<String> profileNames = new ArrayList<String>();
    private Spinner profileMenu;
    private Graph graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aA = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
        SetupProfiles();
        graph = (Graph) findViewById(R.id.graph);

        Spinner profileMenu = findViewById(R.id.profile_menu);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, profileNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profileMenu.setAdapter(arrayAdapter);
        profileMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ResetMediaPlayer();
                playProfile(parent.getItemAtPosition(position).toString());
                graph.mPs = mPs;
            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {}
        });


    }

    private void playProfile(String profile){
        for(int i = 0; i < profiles.get(profile).length; i++){
            MediaPlayer tmp_mP = MediaPlayer.create(getApplicationContext(), profiles.get(profile)[i], aA, i);
            tmp_mP.setLooping(true);
            tmp_mP.start();

            if(i % 2 == 0)
                tmp_mP.setVolume(0.25f, 0);
            else
                tmp_mP.setVolume(0, 0.25f);

            mPs.add(tmp_mP);
        }
    }

    private void ResetMediaPlayer(){
        try {
            for(MediaPlayer mp : mPs){
                mp.stop();
                mp.release();
                mp = null;
            }
            mPs.clear();
            graph.ResetNodes();
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void SetupProfiles(){
        final int[] thunderIDs = {  R.raw.thunder_0a, R.raw.thunder_0b,
                                    R.raw.thunder_1a, R.raw.thunder_1b,
                                    R.raw.thunder_2a, R.raw.thunder_2b,
                                    R.raw.thunder_3a, R.raw.thunder_3b,
                                    R.raw.thunder_4a, R.raw.thunder_4b,
                                    R.raw.thunder_5a, R.raw.thunder_5b,
                                    R.raw.thunder_6a, R.raw.thunder_6b,
                                    R.raw.thunder_7a, R.raw.thunder_7b,
                                    R.raw.thunder_8a, R.raw.thunder_8b,
                                    R.raw.thunder_9a, R.raw.thunder_9b  };
        profiles.put("Thunder", thunderIDs);
        profileNames.add("Thunder");

        final int[] rainIDs = {  R.raw.rain_0a, R.raw.rain_0b,
                                    R.raw.rain_1a, R.raw.rain_1b,
                                    R.raw.rain_2a, R.raw.rain_2b,
                                    R.raw.rain_3a, R.raw.rain_3b,
                                    R.raw.rain_4a, R.raw.rain_4b,
                                    R.raw.rain_5a, R.raw.rain_5b,
                                    R.raw.rain_6a, R.raw.rain_6b,
                                    R.raw.rain_7a, R.raw.rain_7b,
                                    R.raw.rain_8a, R.raw.rain_8b,
                                    R.raw.rain_9a, R.raw.rain_9b  };

        profiles.put("Rain", rainIDs);
        profileNames.add("Rain");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(graph.initialized == false) {
            graph.InitNodes();
        }
        //Toast.makeText(getApplicationContext(), "w: " + graph.getWidth() + " h: " + graph.getHeight(), Toast.LENGTH_SHORT).show();
    }
}
