package com.app.jpushtest;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wangbs on 16/8/3.
 */
public class JpushApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        jpushInit();
    }
    private void jpushInit() {
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(getApplicationContext());            // 初始化 JPush
    }
}
