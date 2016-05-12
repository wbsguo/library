package cn.swu.pulltorefreshswipemenulistviewsample;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.swu.pulltorefresh.RefreshTime;
import cn.swu.pulltorefreshswipemenulistviewsample.PullToRefreshSwipeMenuListView.IXListViewListener;
import cn.swu.pulltorefreshswipemenulistviewsample.PullToRefreshSwipeMenuListView.OnMenuItemClickListener;
import cn.swu.pulltorefreshswipemenulistviewsample.PullToRefreshSwipeMenuListView.OnSwipeListener;
import cn.swu.swipemenulistview.SwipeMenu;
import cn.swu.swipemenulistview.SwipeMenuCreator;
import cn.swu.swipemenulistview.SwipeMenuItem;

public class MainActivity extends Activity implements IXListViewListener {
    private SwipeAdapter mAdapter;
    private PullToRefreshSwipeMenuListView mListView;
    private Handler mHandler;
    private List<MessageBean> messageBeans=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getDatas();
        mListView = (PullToRefreshSwipeMenuListView) findViewById(R.id.listView);
        mAdapter = new SwipeAdapter(this,messageBeans);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
        swipeTest();
        mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "item点击", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    private void getDatas(){
        for(int i=0;i<20;i++){
            MessageBean messageBean=new MessageBean();
            messageBean.setName("名字:"+i);
            messageBean.setContent("内容:" + i);
            messageBeans.add(messageBean);
        }
    }
    private void onLoad() {
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getApplicationContext()));
        mListView.stopRefresh();

        mListView.stopLoadMore();

    }

    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
                RefreshTime.setRefreshTime(getApplicationContext(), df.format(new Date()));
                onLoad();
            }
        }, 2000);
    }

    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoad();
            }
        }, 2000);
    }
    private void swipeTest(){
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(MainActivity.this);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
//				// set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        deleteNotice(position);
                        break;
                }
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
    private void deleteNotice(int position){
        messageBeans.remove(position);
        mAdapter.setDatas(messageBeans);
        mAdapter.notifyDataSetChanged();
    }
}
