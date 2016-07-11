package com.example.mvptest.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.mvptest.view.IMainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbs on 16/7/11.
 */
public class MainPresenterCompl implements MainPresenter{
    private IMainView mainView;
    private Context context;
    public MainPresenterCompl(Context context, IMainView mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    @Override
    public void loadDatas() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainView.onGetDataList(getDatas());
            }
        },2000);
    }
    private List<String> getDatas(){
        List<String> datas=new ArrayList<>();
        for(int i=0;i<20;i++){
            datas.add("我们测试:"+i);
        }
        return datas;
    }
}
