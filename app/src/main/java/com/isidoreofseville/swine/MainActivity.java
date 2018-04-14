package com.isidoreofseville.swine;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {


    ImageView btnPlay;
    ImageView btnSwine;
    ImageView btnExit;

    TextView txtHighScore;

    ImageView logo;
    SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Foreground.init(getApplication());

        soundPlayer = new SoundPlayer(this);
        soundPlayer.playBg();

        btnPlay = findViewById(R.id.btnPlay);
        btnSwine = findViewById(R.id.btnSwine);
        btnExit = findViewById(R.id.btnExit);
        txtHighScore = findViewById(R.id.txtHighScore);
        btnPlay.setOnClickListener(play_clicked);
        btnSwine.setOnClickListener(swine_clicked);
        btnExit.setOnClickListener(exit_clicked);

        logo = findViewById(R.id.logo);

        ObjectAnimator posY = ObjectAnimator.ofFloat(logo, "translationY", 9.0f).setDuration(2000);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(btnPlay, "scaleX", 1.05f).setDuration(700);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(btnPlay, "scaleY", 1.05f).setDuration(700);
        posY.setRepeatCount(ObjectAnimator.INFINITE);
        scaleX.setRepeatCount(ObjectAnimator.INFINITE);
        scaleY.setRepeatCount(ObjectAnimator.INFINITE);

        posY.setRepeatMode(ObjectAnimator.REVERSE);
        scaleX.setRepeatMode(ObjectAnimator.REVERSE);
        scaleY.setRepeatMode(ObjectAnimator.REVERSE);

        AnimatorSet posAnim = new AnimatorSet();
        AnimatorSet scaleAnim = new AnimatorSet();
        posAnim.play(posY);
        posAnim.start();
        scaleAnim.play(scaleX).with(scaleY);
        scaleAnim.start();

        setFonts();

    }

    private View.OnClickListener play_clicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            soundPlayer.playClickSound();
            startActivity(new Intent(MainActivity.this, CategoriesActivity.class));
        }
    };

    private View.OnClickListener swine_clicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            soundPlayer.playClickSound();
            startActivity(new Intent(MainActivity.this, Swine.class));
            MainActivity.this.overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
        }
    };

    private View.OnClickListener exit_clicked= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            soundPlayer.playClickSound();
            finish();
            System.exit(0);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        int hiscore = Data.readString(this, "hiscore");
        if (hiscore != 0)
            txtHighScore.setText("High Score: " + String.valueOf(hiscore));
        else txtHighScore.setText("");
    }

    private void setFonts(){
        TextView[] tv2 = {txtHighScore};

        FontCustomizer fontCustomizer = new FontCustomizer(this);
        fontCustomizer.setToNexaBlack(tv2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
