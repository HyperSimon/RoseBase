package com.roselism.rosebase.webkit;

import android.webkit.WebViewClient;

/**
 * Created by simon on 16-6-15.
 */
public class WebViewClientWrapper extends WebViewClient {
    WebViewClient webViewClient;

    public WebViewClientWrapper(WebViewClient webViewClient) {
        this.webViewClient = webViewClient;
    }
}
