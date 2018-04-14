package com.isidoreofseville.swine;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lose extends Activity {

    Button btnNext;
    TextView txtGameOver, txtCorrectAnswer;
    String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SoundPlayer soundPlayer = new SoundPlayer(this);
        soundPlayer.playPigLost();

        Intent intent = getIntent();
        correctAnswer = intent.getStringExtra("correctAnswer");

        btnNext = findViewById(R.id.btnNext);
        txtGameOver = findViewById(R.id.txtGameWin);
        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer);

        txtCorrectAnswer.setText(correctAnswer);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lose.this, Game.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                overridePendingTransition(0,0);
                finish();
                overridePendingTransition(0,0);
            }
        });
        setFonts();
        System.out.println("LOSE!");
    }


    private void setFonts(){
        TextView[] tv2 = {btnNext, txtGameOver, txtCorrectAnswer};

        FontCustomizer fontCustomizer = new FontCustomizer(this);
        fontCustomizer.setToNexaBlack(tv2);
    }

    @Override
    public void onBackPressed() {

    }
}
