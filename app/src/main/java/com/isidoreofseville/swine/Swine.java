package com.isidoreofseville.swine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class Swine extends Activity {

    //This is where the swine information has been put
    private WebView webView;
    private RelativeLayout mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swine);

        webView = findViewById(R.id.webView);
        mainView = findViewById(R.id.mainView);

        webView.setBackgroundColor(Color.TRANSPARENT);

        //We used a web site or html to display swine information
        webView.loadUrl("file:///android_asset/html/index.html");


        webView.setWebViewClient(new WebViewClient()
        {
            // Override URL
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                if(url.equalsIgnoreCase("file:///android_asset/html/activity_video://video")){
                    Intent intent=new Intent(Swine.this,Video.class);
                    startActivity(intent);
                    System.out.println("OVERRIDEN");
                } else {
                    return false;
                }

                Log.e("URL","URL "+url);
                return true;
            }


        });
    }

    //Added this code to go back to webpage instead of going back to the main screen of the app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        return super.onKeyDown(keyCode, event);
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        mainView.removeView(webView);
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy();
    }
}
