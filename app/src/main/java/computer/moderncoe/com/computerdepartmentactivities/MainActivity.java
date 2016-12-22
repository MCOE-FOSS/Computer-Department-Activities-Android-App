package computer.moderncoe.com.computerdepartmentactivities;

import android.annotation.SuppressLint; //Indicates that Lint should ignore the specified warnings for the annotated element.
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class MainActivity extends Activity {

    WebView myWebView;

    @SuppressLint("SetJavaScriptEnabled") //Supress warning for enabling JavaScript in WebView
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.webview);
        final ProgressDialog dialog = new ProgressDialog(this);
        myWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                dialog.setMessage("Loading. Please Wait");
                dialog.show();
                if(newProgress == 100 && dialog.isShowing())
                    dialog.dismiss();
            }
        });

        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true); //enabled java script as wordpress site for WebView uses JS
        myWebView.loadUrl("https://mcoecomp.wordpress.com");
    }
    //code to handle back keypress
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient {
        private static final String TAG = "MyWebViewClient";;

        // Give application a chance to catch additional URL loading requests
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "About to load:" + url);
            view.loadUrl(url);
            return true;
        }
    }

}





