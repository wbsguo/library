package com.example.treeview;

import java.io.Serializable;

/**
 * Created by wangbs on 16/6/1.
 */
public class FoodBean implements Serializable{
    private String friendName;
    private String foodName = "";//食物名
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
