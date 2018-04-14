package com.isidoreofseville.swine;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Dave on 3/23/2018.
 */

public class SoundPlayer {

    private static SoundPool soundPool;
    private int clickSound;
    private int applause;
    private int beep, time;
    private int correct, wrong;
    private int piglose, pigwin;
    private int bgSound;

    public SoundPlayer(Context context) {

        if (soundPool == null){
            soundPool = new SoundPool(9, AudioManager.STREAM_MUSIC, 0);
            System.out.println("CREATED NEW SOUNDPOOL OBJECT");
        }

        bgSound = soundPool.load(context, R.raw.bg, 10);
        clickSound = soundPool.load(context, R.raw.click, 1);
        applause = soundPool.load(context, R.raw.applause, 1);
        beep = soundPool.load(context, R.raw.beep, 1);
        time = soundPool.load(context, R.raw.time, 1);
        correct = soundPool.load(context, R.raw.correct, 1);
        wrong = soundPool.load(context, R.raw.wrong, 1);
        piglose = soundPool.load(context, R.raw.piglose, 1);
        pigwin = soundPool.load(context, R.raw.pigwin, 1);
    }

    public void playClickSound(){
        soundPool.play(clickSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playBg(){
        soundPool.play(bgSound, 0.3f, 0.3f, 1, -1, 1.0f);
    }

    public void playApplause(){
        soundPool.play(applause, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playBeep(){
        soundPool.play(beep, 1.0f, 1.0f, 1, 0, 1.0f);

    }

    public void playTime(){
        soundPool.play(time, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playCorrect(){
        soundPool.play(correct, 1.0f, 1.0f, 1, 0, 1.0f);

    }

    public void playWrong(){
        soundPool.play(wrong, 1.0f, 1.0f, 1, 0, 1.0f);

    }
    public void playPigLost(){
        soundPool.play(piglose, 1.0f, 1.0f, 1, 0, 1.0f);

    }

    public void playPigWin(){
        soundPool.play(pigwin, 1.0f, 1.0f, 1, 0, 1.0f);

    }



    public void stopBg(){
        soundPool.stop(bgSound);
    }
}
