package com.example.butterknifelibrary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wangbs on 16/5/11.
 */
public class ListTestActivity extends Activity {
    @Bind(R.id.list_layout)
    ListView listLayout;

    private MyListAdapter adapter;
    private List<String> datas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        ButterKnife.bind(this);
        getDatas();
        initView();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    private void getDatas(){
        for(int i=0;i<20;i++){
            datas.add("我们的好"+i);
        }
    }
    private void initView(){
        adapter=new MyListAdapter(this,datas);
        listLayout.setAdapter(adapter);
    }
}
