package com.example.sticklistlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ListViewTitleActivity extends Activity implements StickyListHeadersListView.OnHeaderClickListener,AdapterView.OnItemClickListener{
    private static final String TAG="ListViewTitleActivity";
    private StickyListHeadersListView lv_data;
    FoodAdapter adapter;
    HashMap<String, FicationBean> hashMap = new HashMap<String, FicationBean>();
    List<FoodBean> datas = new ArrayList<FoodBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_title);
        findUI();
        viewList();
        getFoods();
    }
    private void findUI(){
        lv_data = (StickyListHeadersListView) findViewById(R.id.lv_data);
//        lv_data.setEmptyView(findViewById(R.id.tv_no_results));
        lv_data.setDrawingListUnderStickyHeader(true);
        lv_data.setAreHeadersSticky(true);
    }
    private void viewList() {
        adapter = new FoodAdapter(
                hashMap, datas,ListViewTitleActivity.this);
        lv_data.setOnItemClickListener(this);
        lv_data.setOnHeaderClickListener(this);
        lv_data.setAdapter(adapter);
    }
    public void getFoods(){
        List<FoodBean> datas = new ArrayList<FoodBean>();
        for(int i=0;i<5;i++){
            FoodBean foodBean=new FoodBean();
            foodBean.setFoodName("食物名"+i);
            foodBean.setFicationId("茄子");
            datas.add(foodBean);
        }
        for(int i=0;i<5;i++){
            FoodBean foodBean2=new FoodBean();
            foodBean2.setFoodName("食物名"+i);
            foodBean2.setFicationId("土豆");
            datas.add(foodBean2);
        }
        for(int i=0;i<5;i++){
            FoodBean foodBean=new FoodBean();
            foodBean.setFoodName("食物名"+i);
            foodBean.setFicationId("西红柿");
            datas.add(foodBean);
        }
        for(FoodBean foodBean:datas){
            Log.e(TAG,"食物分类:"+foodBean.getFicationId()+":食物名:"+foodBean.getFoodName());
        }
        List<FicationBean> fications=new ArrayList<FicationBean>();
        FicationBean ficationBean1 = new FicationBean();
        ficationBean1.setFication_id("茄子");
        ficationBean1.setFication_name("茄子名");
        fications.add(ficationBean1);
        FicationBean ficationBean2 = new FicationBean();
        ficationBean2.setFication_id("土豆");
        ficationBean2.setFication_name("土豆名");
        fications.add(ficationBean2);
        FicationBean ficationBean3 = new FicationBean();
        ficationBean3.setFication_id("西红柿");
        ficationBean3.setFication_name("西红柿名");
        fications.add(ficationBean3);
        datas(fications,datas);
    }
    //注:食物数据ficationid相同，要在一块
    private void datas(List<FicationBean> fications, List<FoodBean> foods){
        hashMap.clear();
        for (int i = 0; i < fications.size(); i++) {
            for (FoodBean foodBean : foods) {
                if (fications.get(i).getFication_id().equals(foodBean.getFicationId())) {
                    if (!hashMap.containsKey(fications.get(i).getFication_id())) {
                        hashMap.put(fications.get(i).getFication_id(), fications.get(i));
                    }
                    foodBean.setHeard_id(i);
                    foodBean.setFicationName(fications.get(i).getFication_name());
                }
            }
        }
        datas.clear();
        datas.addAll(foods);
        adapter.setDatas(datas);
        adapter.setHashMap(hashMap);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onHeaderClick(StickyListHeadersListView l, View header,
                              int itemPosition, long headerId, boolean currentlySticky) {
        Log.v(TAG, "itemPosition:" + itemPosition + "headerId:" + headerId);
//        String ficationId="";
//        boolean flag=false;
//        for(FoodBean foodBean:datas){
//            if(!flag){
//                if(foodBean.getHeard_id()==headerId){
//                    ficationId=foodBean.getFicationId();
//                }
//            }
//        }
//        FicationBean ficationBean=(FicationBean)hashMap.get(ficationId);
//        MyLog.v(TAG, "分类名:"+ficationBean.getFication_name());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        FoodBean foodBean=(FoodBean)parent.getItemAtPosition(position);
        Log.v(TAG, "食物名:"+foodBean.getFoodName());
    }
}
