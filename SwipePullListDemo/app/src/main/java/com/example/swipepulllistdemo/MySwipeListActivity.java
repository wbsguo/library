package com.example.swipepulllistdemo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshSwipeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbs on 16/6/17.
 */
public class MySwipeListActivity extends Activity{
    private static final String TAG="MySwipeListActivity";
    private PullToRefreshSwipeListView listView;
    private SwipeMenuListView menuListView;
    private UserAdapter adapter;
    private List<String> datas=new ArrayList<String>();
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            listView.onRefreshComplete();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewUI();
        getData();
        initView();
    }
    private void findViewUI(){
        listView = (PullToRefreshSwipeListView) findViewById(R.id.lv);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        menuListView =listView.getRefreshableView();
        menuListView.setMenuCreator(new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                setSwipeMenu(menu);
            }
        });
        menuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                setOnMenuItemClick(position,index);
                return false;
            }
        });
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                Toast.makeText(MySwipeListActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                getFlashData();
                handler.sendEmptyMessageDelayed(0, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                Toast.makeText(MySwipeListActivity.this, "上拉加载更多", Toast.LENGTH_SHORT).show();
                getMoreData();
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        });
    }
    private void getData(){
        for(int i=0;i<10;i++){
            datas.add("我们"+i);
        }
    }
    private void getFlashData(){
        datas.clear();
        for(int i=0;i<10;i++){
            datas.add("我们"+i);
        }
        flashList();
    }
    private void getMoreData(){
        for(int i=10;i<20;i++){
            datas.add("他们"+i);
        }
        flashList();
    }
    private void flashList(){
        adapter.setDatas(datas);
        menuListView.closeMenu();
        adapter.notifyDataSetChanged();
    }
    private void initView(){
        adapter=new UserAdapter(this,datas);
        menuListView.setAdapter(adapter);
    }
    private void setSwipeMenu(SwipeMenu menu){
        SwipeMenuItem callItem = new SwipeMenuItem(getApplicationContext());
        // set item background
        callItem.setBackground(new ColorDrawable(Color.GRAY));
        // set item width
        callItem.setWidth(130);
        // set a icon
        // deleteItem.setIcon(R.drawable.ic_launcher);
        callItem.setTitle("Call");
        callItem.setTitleColor(Color.WHITE);
        callItem.setTitleSize(18);

        SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
        // set item background
        deleteItem.setBackground(new ColorDrawable(Color.RED));
        // set item width
        deleteItem.setWidth(130);
        // set a icon
        // deleteItem.setIcon(R.drawable.ic_launcher);
        deleteItem.setTitle("Delete");
        deleteItem.setTitleColor(Color.WHITE);
        deleteItem.setTitleSize(18);
        // add to menu
        menu.addMenuItem(callItem);
        menu.addMenuItem(deleteItem);
    }
    private void setOnMenuItemClick(int position,int index){
        Toast.makeText(MySwipeListActivity.this, "position="+position+";index="+index, Toast.LENGTH_SHORT).show();
        datas.remove(position);
        flashList();
    }
}
