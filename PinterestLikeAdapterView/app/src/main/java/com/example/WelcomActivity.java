package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by wangbs on 16/5/30.
 */
public class WelcomActivity extends Activity{
    private Button test1,test2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        test1=(Button)findViewById(R.id.test1);
        test2=(Button)findViewById(R.id.test2);
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomActivity.this,PinterestActivity.class);
                startActivity(intent);
            }
        });
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
