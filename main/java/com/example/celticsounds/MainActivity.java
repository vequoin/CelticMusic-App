package com.example.celticsounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int value;


    public void setValue(int value) {
        MainActivity.value = value;
    }

    public static int getValue() {
        return value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button irishJig = findViewById(R.id.play_jig);
        Button irishBag = findViewById(R.id.play_bagpipe);
        Button irishHarp = findViewById(R.id.play_harp);
        Button irishBand = findViewById(R.id.play_band);

        irishHarp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irishHOnClick();
            }
        });
        irishBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bagpipeOnClick();
            }
        });

        irishJig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jigOnClick();
            }
        });

        irishBand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bandOnClick();
            }
        });
    }

    private void bandOnClick() {
        Intent myActivity  = new Intent(MainActivity.this,Player.class);
        setValue(3);
        startActivity(myActivity);
    }

    private void jigOnClick() {
        Intent myActivity  = new Intent(MainActivity.this,Player.class);
        setValue(0);
        startActivity(myActivity);
    }

    private void bagpipeOnClick() {
        Intent myActivity  = new Intent(MainActivity.this,Player.class);
        setValue(1);
        startActivity(myActivity);
    }

    public void irishHOnClick() {
        Intent myActivity  = new Intent(MainActivity.this,Player.class);
        setValue(2);
        startActivity(myActivity);
    }




}