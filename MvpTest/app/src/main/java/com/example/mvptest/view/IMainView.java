package com.example.mvptest.view;

import java.util.List;

/**
 * Created by wangbs on 16/7/11.
 */
public interface IMainView {
    public void onGetDataList(List<String> datas);
    public void toast(String msg);
}
