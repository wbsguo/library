package cn.swu.pulltorefreshswipemenulistviewsample;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/14.
 */
public class MessageBean implements Serializable{
    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
