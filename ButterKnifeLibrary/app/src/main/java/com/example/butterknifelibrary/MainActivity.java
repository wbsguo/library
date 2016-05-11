package com.example.butterknifelibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    @OnClick({R.id.button1, R.id.button2})
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.button1:
                break;
            case R.id.button2:
                intent=new Intent(MainActivity.this,ListTestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
