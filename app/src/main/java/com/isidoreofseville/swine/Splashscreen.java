package com.isidoreofseville.swine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import pl.droidsonroids.gif.GifImageView;

public class Splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splashscreen.this, MainActivity.class));

                finish();
            }
        }, 6000L);

        GifImageView swineText = findViewById(R.id.swineTextAnim);
        swineText.animate().alpha(1.0f).setDuration(2000).setStartDelay(1000);
    }
}
