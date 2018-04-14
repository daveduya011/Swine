package com.isidoreofseville.swine;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Gameover extends Activity {

    Button btnMainMenu;
    TextView txtScore, txtGameOver, lblScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SoundPlayer soundPlayer = new SoundPlayer(this);
        soundPlayer.playPigLost();
        Intent intent = getIntent();
        int score = intent.getExtras().getInt("Score");

        int lastHiScore = Data.readString(this, "hiscore");

        if (lastHiScore < score){
            Data.writeString(this, "hiscore", score);
        }

        btnMainMenu = findViewById(R.id.btnNext);
        txtScore = findViewById(R.id.txtScore);
        txtGameOver = findViewById(R.id.txtGameWin);
        lblScore = findViewById(R.id.lblScore);

        txtScore.setText(String.valueOf(score));
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setFonts();


    }


    private void setFonts(){
        TextView[] tv2 = {btnMainMenu, txtScore, txtGameOver, lblScore};

        FontCustomizer fontCustomizer = new FontCustomizer(this);
        fontCustomizer.setToNexaBlack(tv2);
    }
}
