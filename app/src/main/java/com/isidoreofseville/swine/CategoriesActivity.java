package com.isidoreofseville.swine;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CategoriesActivity extends Activity {

    private ArrayList<Button> btnArray;
    SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        soundPlayer = new SoundPlayer(this);

        CATEGORIES.initialize();


        btnArray = new ArrayList<>();

        Button btn = findViewById(R.id.category_breeds);
        btnArray.add(btn);

        btn = findViewById(R.id.category_swine);
        btnArray.add(btn);

        btn = findViewById(R.id.category_pregnancy);
        btnArray.add(btn);

        btn = findViewById(R.id.category_lactation);
        btnArray.add(btn);

        btn = findViewById(R.id.category_feeding);
        btnArray.add(btn);

        btn = findViewById(R.id.category_health);
        btnArray.add(btn);

        btn = findViewById(R.id.category_baby);
        btnArray.add(btn);

        btn = findViewById(R.id.category_disease);
        btnArray.add(btn);

        setBtnFunctions();

    }

    private void setBtnFunctions() {
        for (int i = 0; i < btnArray.size(); i++){

            final int finalI = i;
            btnArray.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    soundPlayer.playClickSound();
                    Intent intent = new Intent(CategoriesActivity.this, Game.class);
                    intent.putExtra("category", CATEGORIES.categoriesArray.get(finalI));
                    startActivity(intent);
                }
            });
        }

    }


}
