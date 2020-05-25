package com.example.coolpiece;

import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class DaumLocationActivity extends AppCompatActivity {
    WebView daum_webview;
    Button locate_ok;
    Button locate_cancel;
    TextView address_result;
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daumlocate);

        address_result=(TextView)findViewById(R.id.address_result);
        daum_webview=(WebView)findViewById(R.id.daum_webview);
        locate_ok=(Button)findViewById(R.id.locate_ok);
        locate_cancel=(Button)findViewById(R.id.locate_cancel);

        init_webView();

        handler=new Handler();

        locate_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void init_webView(){


        //daum_webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        daum_webview.getSettings().setJavaScriptEnabled(true);

        daum_webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        daum_webview.addJavascriptInterface(new AndroidBridge(), "MyAddress");
        //daum_webview.setWebViewClient(new SslWebViewConnect());
        daum_webview.setWebViewClient(new WebViewClient());
        daum_webview.setWebChromeClient(new WebChromeClient());
        daum_webview.loadUrl("http://192.168.43.133/address.php");
        //daum_webview.getSettings().setDomStorageEnabled(true);

    }
    public class AndroidBridge{
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    address_result.setText(String.format("현재 주소: (%s) %s %s", arg1, arg2, arg3));
                    init_webView();
                }
            });
        }
    }
    public class SslWebViewConnect extends WebViewClient{
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }
}
