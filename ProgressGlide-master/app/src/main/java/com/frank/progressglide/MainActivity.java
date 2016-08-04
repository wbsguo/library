package com.frank.progressglide;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frank.progressglide.progress.ProgressModelLoader;

import java.lang.ref.WeakReference;

/**
 * 借助OkHttp的拦截器进行进度监听
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ImageView iv_0;
    private ProgressBar progressBar;
    private TextView progressTextView;

    private final MainActivityHandler mainActivityHandler = new MainActivityHandler(this);
    String imageUrl="http://api.v2.pokemonmap.xyz/vtest/data/timages/6693/2656693_0.jpg?1470224503";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_0 = (ImageView) findViewById(R.id.iv_0);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressTextView = (TextView) findViewById(R.id.progress_text_view);
        //960 × 533 pixels,25059 bytes
        Glide.with(this)
                .using(new ProgressModelLoader(mainActivityHandler))
                .load(imageUrl)
                .into(iv_0);
    }

    private static class MainActivityHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MainActivityHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final MainActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        //int bytesRead = msg.arg1;
                        //int contentLength = msg.arg2;
                        int percent = msg.arg1*100/msg.arg2;
                        Log.e(TAG,"msg.arg1:"+msg.arg1+"msg.arg2:"+msg.arg2+"percent:"+percent);
                        activity.progressBar.setProgress(percent);
                        activity.progressTextView.setText("loading..." + percent + "%");
                        if (msg.arg1 == msg.arg2) {
                            activity.progressTextView.setText(msg.arg2 + " bytes");
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

}

