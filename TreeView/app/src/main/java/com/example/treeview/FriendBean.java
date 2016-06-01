package com.example.treeview;

import java.io.Serializable;

/**
 * Created by wangbs on 16/6/1.
 */
public class FriendBean implements Serializable{
    private String friendName;
    private String friendDescript;

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendDescript() {
        return friendDescript;
    }

    public void setFriendDescript(String friendDescript) {
        this.friendDescript = friendDescript;
    }
}
