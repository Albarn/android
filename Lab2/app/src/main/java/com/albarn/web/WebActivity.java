package com.albarn.web;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.albarn.equation.R;

import java.util.ArrayList;
import java.util.Iterator;

public class WebActivity extends AppCompatActivity {
    //iterator for searchers urls list
    class Searchers implements Iterator<String>{

        String[] urls={"https://google.com","https://yahoo.com","https://duckduckgo.com", "https://www.bing.com/"};

        @Override
        public boolean hasNext() {
            return false;
        }

        int index=0;
        @Override
        public String next() {
            index++;
            index%=urls.length;
            return urls[index];
        }

        @Override
        public void remove() { }
    }

    private WebView webView;
    private Button changeUrlButton;
    private Searchers searchers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //get views
        webView=(WebView)findViewById(R.id.searcherWebView);
        changeUrlButton=(Button)findViewById(R.id.changeUrlButton);

        //prevent browser opening
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //initialize iterator,
        // webView url and button text - next searcher
        searchers=new Searchers();
        webView.loadUrl(searchers.next());
        changeUrlButton.setText(searchers.next());
    }

    //
    public void changeURL(View view){
        webView.loadUrl(changeUrlButton.getText().toString());
        changeUrlButton.setText(searchers.next());
    }
}
