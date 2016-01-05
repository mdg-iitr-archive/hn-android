package com.mdg.hackernews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by abhishek on 28/5/15.
 */
public class webView extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        WebView wView= (WebView)findViewById(R.id.wView);
        TextView tv = (TextView)findViewById(R.id.tv);
        WebSettings webSettings;
        try{Intent intent = getIntent();
        final String message = intent.getStringExtra("webURL");
            if(message!=null) {tv.setText(message);
                wView.setWebViewClient(new WebViewClient() {

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {

                        view.loadUrl(url);
                        return true;
                    }
                });
                wView.loadUrl(message);}
            else tv.setText("message is null");

            Log.d("DataAbhi3", "Data received from main");}
        catch(Exception e){
            Toast.makeText(this,"Error"+e.toString(),Toast.LENGTH_LONG);
        }
    }
}
