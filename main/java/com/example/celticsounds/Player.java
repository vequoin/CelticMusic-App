package com.example.celticsounds;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Player extends AppCompatActivity {

    public ImageView play_btn;
    public ImageView stop_btn;
    public ImageView pause_btn;
    public ImageView back_btn;
    ImageView mainImage;
    TextView songName;
    int value = (int)MainActivity.getValue();
    int[] songs = {R.raw.jig,R.raw.bagpipe,R.raw.harp,R.raw.band};
    String[] song_names = {"Jig.mp3","Bagpipes.mp3","Harp.mp3","Band.mp3"};
    MediaPlayer media_Player;
    private double startTime;
    private double finalTime;
    private SeekBar seekBar;
    private Handler myHandler = new Handler();
    private TextView tx1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        play_btn = (ImageView) findViewById(R.id.play_button);
        stop_btn = (ImageView) findViewById(R.id.stop_button);
        mainImage = (ImageView) findViewById(R.id.main_image);
        pause_btn = (ImageView) findViewById(R.id.pause_button);
        songName = findViewById(R.id.song_name);
        seekBar = findViewById(R.id.seek_bar_main);
        back_btn = (ImageView)findViewById(R.id.backkkbtn);
        tx1 = findViewById(R.id.tx_1);
        onStarting();
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_this();
            }
        });
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop_play();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                media_Player.pause();
            }
        });

    }

    @Override
    public void onBackPressed() {
        media_Player.stop();
        seekBar.setProgress(0);
        play_btn.setEnabled(true);
        super.onBackPressed();
    }

    private void stop_play() {
        media_Player.stop();
        seekBar.setProgress(0);
        play_btn.setEnabled(true);
    }

    public void onStarting(){
        switch (value) {
            case 2:
                media_Player = MediaPlayer.create(this,songs[value]);
                songName.setText(song_names[value]);
                mainImage.setImageResource(R.drawable.irishharp);
                play_btn.setEnabled(false);
                stop_btn.setEnabled(true);
                media_Player.start();
                finalTime = media_Player.getDuration();
                startTime = media_Player.getCurrentPosition();
                seekBar.setMax((int)finalTime);
                seekBar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);
                break;
            case 1:
                media_Player = MediaPlayer.create(this,songs[value]);
                songName.setText(song_names[value]);
                mainImage.setImageResource(R.drawable.irishbag);
                play_btn.setEnabled(false);
                stop_btn.setEnabled(true);
                media_Player.start();
                finalTime = media_Player.getDuration();
                startTime = media_Player.getCurrentPosition();
                seekBar.setMax((int)finalTime);
                seekBar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);
                break;
            case 0:
                media_Player = MediaPlayer.create(this,songs[value]);
                songName.setText(song_names[value]);
                mainImage.setImageResource(R.drawable.irishjigg);
                play_btn.setEnabled(false);
                stop_btn.setEnabled(true);
                media_Player.start();
                finalTime = media_Player.getDuration();
                startTime = media_Player.getCurrentPosition();
                seekBar.setProgress((int) startTime);
                seekBar.setMax((int)finalTime);
                myHandler.postDelayed(UpdateSongTime, 100);
                break;
            case 3:
                media_Player = MediaPlayer.create(this,songs[value]);
                songName.setText(song_names[3]);
                mainImage.setImageResource(R.drawable.irishband);
                play_btn.setEnabled(false);
                stop_btn.setEnabled(true);
                media_Player.start();
                finalTime = media_Player.getDuration();
                startTime = media_Player.getCurrentPosition();
                seekBar.setMax((int)finalTime);
                seekBar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);
                break;
            default:
                break;
        }
    }

    public void play_this() {
        Toast.makeText(this, "I am clicked", Toast.LENGTH_SHORT).show();
        media_Player = MediaPlayer.create(this,songs[value]);
        finalTime = media_Player.getDuration();
        startTime = media_Player.getCurrentPosition();
        media_Player.start();
        seekBar.setMax((int)finalTime);
        seekBar.setProgress((int) startTime);
        myHandler.postDelayed(UpdateSongTime, 100);
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = media_Player.getCurrentPosition();
            tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}