package com.example.mvptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.mvptest.presenter.MainPresenter;
import com.example.mvptest.presenter.MainPresenterCompl;
import com.example.mvptest.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView {
    private ListView mvpList;
    private BaseAdapter adapter;
    private List<String> datas=new ArrayList<>();
    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mvpList=(ListView)findViewById(R.id.mvp_list);
        init();
        initView();
    }
    private void init(){
        presenter=new MainPresenterCompl(this,this);
    }
    private void initView(){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        mvpList.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadDatas();
    }
    @Override
    public void onGetDataList(List<String> datas) {
        if(datas!=null){
            this.datas.clear();
            this.datas.addAll(datas);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void toast(String msg) {

    }
}
