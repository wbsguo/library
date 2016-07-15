package com.example.treeview;

import android.app.Activity;
import android.os.Bundle;

import com.example.treeview.tree.TreeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private TreeView treeView;
    private Map<String,List<FoodBean>> hashMap=new HashMap<>();
    private List<String> titles=new ArrayList<>();
    private TreeViewAdapter2 adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initView2();
        getData();
    }
    private void findView(){
        treeView = (TreeView) findViewById(R.id.tree_view);
        treeView.setHeaderView(getLayoutInflater().inflate(R.layout.list_group_view, treeView,
                false));
    }
    private void initView() {
        treeView.setAdapter(new TreeViewAdapter(this, treeView));
    }
    private void initView2() {
        adapter2=new TreeViewAdapter2(this, treeView,titles,hashMap);
        treeView.setAdapter(adapter2);
    }
    private void getData(){
        String title1="我的好友";
        titles.add(title1);
        List<FoodBean> friends1=new ArrayList<>();
        FoodBean friendBean1=new FoodBean();
        friendBean1.setFoodName("亲美食慕军");
        friendBean1.setFriendDescript("全国各地美食,欢迎推荐");
        friends1.add(friendBean1);
        FoodBean friendBean2=new FoodBean();
        friendBean2.setFoodName("育儿导师琦炀");
        friendBean2.setFriendDescript("更新了说说");
        friends1.add(friendBean2);
        FoodBean friendBean3=new FoodBean();
        friendBean3.setFoodName("风驰");
        friendBean3.setFriendDescript("更新了说说");
        friends1.add(friendBean3);
        hashMap.put(title1,friends1);
        String title2="故乡";
        titles.add(title2);
        List<FoodBean> friends2=new ArrayList<>();
        FoodBean friendBean21=new FoodBean();
        friendBean21.setFoodName("小沈");
        friendBean21.setFriendDescript("更新了说说");
        friends2.add(friendBean21);
        FoodBean friendBean22=new FoodBean();
        friendBean22.setFoodName("小陈");
        friendBean22.setFriendDescript("更新了说说");
        friends2.add(friendBean22);
        FoodBean friendBean23=new FoodBean();
        friendBean23.setFoodName("小春");
        friendBean23.setFriendDescript("更新了说说");
        friends2.add(friendBean23);
        hashMap.put(title2,friends2);
        String title3="成都";
        titles.add(title3);
        List<FoodBean> friends3=new ArrayList<>();
        FoodBean friendBean31=new FoodBean();
        friendBean31.setFoodName("小建");
        friendBean31.setFriendDescript("更新了说说");
        friends3.add(friendBean31);
        FoodBean friendBean32=new FoodBean();
        friendBean32.setFoodName("小东");
        friendBean32.setFriendDescript("更新了说说");
        friends3.add(friendBean32);
        hashMap.put(title3,friends3);
        flashView();
    }
    private void flashView(){
        adapter2.setTitles(titles);
        adapter2.setHashMap(hashMap);
        adapter2.notifyDataSetChanged();
    }
}

