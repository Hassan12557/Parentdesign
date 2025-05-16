package com.example.parentdesigndash;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = findViewById(R.id.webview);

        // Keep navigation inside the app
        myWebView.setWebViewClient(new WebViewClient());

        // Load your website
        myWebView.loadUrl("https://dashboard-h2ae.vercel.app/login");

        // WebView settings
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Needed for modern websites

        // SECURITY IMPROVEMENTS
        myWebView.getSettings().setAllowFileAccess(false); // Avoid local file access
        myWebView.getSettings().setAllowContentAccess(false); // Avoid content URL access
        myWebView.getSettings().setDomStorageEnabled(true); // Required for some websites

        // Handle back press properly (modern way)
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (myWebView.canGoBack()) {
                    myWebView.goBack();
                } else {
                    finish();
                }
            }
        });
    }
}
