package com.example.flurrydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flurry.android.FlurryAgent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        FlurryAgent.init(getApplication(), Constant.FLURRY_KEY);
        FlurryAgent.onStartSession(this);
    }
    @Override
    protected void onDestroy() {
        if (FlurryAgent.isSessionActive()) {
            FlurryAgent.onEndSession(this);
        }
        super.onDestroy();
    }
}
