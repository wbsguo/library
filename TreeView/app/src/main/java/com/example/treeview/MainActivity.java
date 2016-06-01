package com.example.treeview;

import android.app.Activity;
import android.os.Bundle;

import com.example.treeview.tree.TreeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {
    private TreeView treeView;
    private HashMap<String,List<FriendBean>> hashMap=new HashMap<>();
    private List<String> titles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        getData();
        initView2();
    }
    private void findView(){
        treeView = (TreeView) findViewById(R.id.tree_view);
        treeView.setHeaderView(getLayoutInflater().inflate(R.layout.list_head_view, treeView,
                false));
    }
    private void initView() {
        treeView.setAdapter(new TreeViewAdapter(this, treeView));
    }
    private void initView2() {
        treeView.setAdapter(new TreeViewAdapter2(this, treeView,titles,hashMap));
    }
    private void getData(){
        String title1="我的好友";
        titles.add(title1);
        List<FriendBean> friends1=new ArrayList<>();
        FriendBean friendBean1=new FriendBean();
        friendBean1.setFriendName("亲美食慕军");
        friendBean1.setFriendDescript("全国各地美食,欢迎推荐");
        friends1.add(friendBean1);
        FriendBean friendBean2=new FriendBean();
        friendBean2.setFriendName("育儿导师琦炀");
        friendBean2.setFriendDescript("更新了说说");
        friends1.add(friendBean2);
        FriendBean friendBean3=new FriendBean();
        friendBean3.setFriendName("风驰");
        friendBean3.setFriendDescript("更新了说说");
        friends1.add(friendBean3);
        hashMap.put(title1,friends1);
        String title2="故乡";
        titles.add(title2);
        List<FriendBean> friends2=new ArrayList<>();
        FriendBean friendBean21=new FriendBean();
        friendBean21.setFriendName("小沈");
        friendBean21.setFriendDescript("更新了说说");
        friends2.add(friendBean21);
        FriendBean friendBean22=new FriendBean();
        friendBean22.setFriendName("小陈");
        friendBean22.setFriendDescript("更新了说说");
        friends2.add(friendBean22);
        FriendBean friendBean23=new FriendBean();
        friendBean23.setFriendName("小春");
        friendBean23.setFriendDescript("更新了说说");
        friends2.add(friendBean23);
        hashMap.put(title2,friends2);
        String title3="成都";
        titles.add(title3);
        List<FriendBean> friends3=new ArrayList<>();
        FriendBean friendBean31=new FriendBean();
        friendBean31.setFriendName("小建");
        friendBean31.setFriendDescript("更新了说说");
        friends3.add(friendBean31);
        FriendBean friendBean32=new FriendBean();
        friendBean32.setFriendName("小东");
        friendBean32.setFriendDescript("更新了说说");
        friends3.add(friendBean32);
        hashMap.put(title3,friends3);
    }
}
