package com.isidoreofseville.swine;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Dave on 2/2/2018.
 */

public class FontCustomizer {
    private Typeface nexablack;
    private Typeface nexareg;

    public FontCustomizer(Context context) {
        nexablack = Typeface.createFromAsset(context.getAssets(), "html/nexabold.otf");
        nexareg = Typeface.createFromAsset(context.getAssets(), "html/nexareg.otf");
    }

    public void setToNexa(TextView text){
        text.setTypeface(nexareg);
    }
    public void setToNexa(TextView[] textCollection){
        for (TextView text : textCollection){
            text.setTypeface(nexareg);
        }
    }

    public void setToNexaBlack(TextView text){
        text.setTypeface(nexablack);
    }

    public void setToNexaBlack(TextView[] textCollection){
        for (TextView text : textCollection){
            text.setTypeface(nexablack);
        }
    }
}
