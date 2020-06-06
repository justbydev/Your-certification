package com.example.coolpiece;

import android.content.Intent;
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
/***activity that appear when click 내주소 찾기 button in MainActivity***/
public class DaumLocationActivity extends AppCompatActivity {
    WebView daum_webview;
    TextView address_result;
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daumlocate);

        address_result=(TextView)findViewById(R.id.address_result);


        init_webView();

        handler=new Handler();

    }

    public void init_webView(){

        daum_webview=(WebView)findViewById(R.id.daum_webview);
        //daum_webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        /***for link between android webview and javascript function***/
        daum_webview.getSettings().setJavaScriptEnabled(true);

        daum_webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        /***decide linking method between android and php, AndroidBridge***/
        /***MyAddress is using in php file for indicating android***/
        daum_webview.addJavascriptInterface(new AndroidBridge(), "MyAddress");
        //daum_webview.setWebViewClient(new SslWebViewConnect());
        /***for do not appear another window, finding address webview appear in the same window***/
        daum_webview.setWebViewClient(new WebViewClient());
        //daum_webview.setWebChromeClient(new WebChromeClient());
        //daum_webview.loadUrl("http://www.naver.com");
        //daum_webview.loadUrl("http://192.168.43.133/address.php");
        /***php file is in app-assets folder named with address.html***/
        daum_webview.loadUrl("file:///android_asset/address.html");

    }
    /***method that get address from php file and address string is in arg1, arg2, arg3***/
    private class AndroidBridge{
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //address_result.setText(String.format("현재 주소: (%s) %s %s", arg1, arg2, arg3));
                    /*Intent intent=new Intent(DaumLocationActivity.this, HomeActivity.class);
                    intent.putExtra("arg1", arg1);
                    intent.putExtra("arg2", arg2);
                    intent.putExtra("arg3", arg3);
                    finish();
                    startActivity(intent);*/
                    ((HomeActivity)HomeActivity.homecontext).arg1=arg1;
                    ((HomeActivity)HomeActivity.homecontext).arg2=arg2;
                    ((HomeActivity)HomeActivity.homecontext).arg3=arg3;
                    //finish();
                    init_webView();
                }
            });
        }
    }

}
