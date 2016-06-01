package com.example.treeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.treeview.tree.BaseTreeViewAdapter;
import com.example.treeview.tree.TreeView;

import java.util.HashMap;
import java.util.List;

/**
 * TreeView demo adapter
 *
 * @author markmjw
 * @date 2014-01-04
 */
public class TreeViewAdapter2 extends BaseTreeViewAdapter {
    private LayoutInflater mInflater;
    private List<String> titles;
    private HashMap<String,List<FriendBean>> hashMap;
    public TreeViewAdapter2(Context context, TreeView treeView,List<String> titles,HashMap<String,List<FriendBean>> hashMap) {
        super(treeView);
        mInflater = LayoutInflater.from(context);
        this.titles=titles;
        this.hashMap=hashMap;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hashMap.get(titles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hashMap.get(titles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return titles.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return titles.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_view, null);
        }

        ChildHolder holder = getChildHolder(convertView);
        FriendBean friendBean=(FriendBean) getChild(groupPosition, childPosition);
        holder.name.setText(friendBean.getFriendName());
        holder.state.setText(friendBean.getFriendDescript());
        return convertView;
    }

    private ChildHolder getChildHolder(final View view) {
        ChildHolder holder = (ChildHolder) view.getTag();
        if (null == holder) {
            holder = new ChildHolder(view);
            view.setTag(holder);
        }
        return holder;
    }

    private class ChildHolder {
        TextView name;
        TextView state;

        public ChildHolder(View view) {
            name = (TextView) view.findViewById(R.id.contact_list_item_name);
            state = (TextView) view.findViewById(R.id.cpntact_list_item_state);
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_group_view, null);
        }

        GroupHolder holder = getGroupHolder(convertView);

        holder.name.setText(titles.get(groupPosition));
        holder.onlineNum.setText(getChildrenCount(groupPosition) + "/" + getChildrenCount(groupPosition));
        if (isExpanded) {
            holder.indicator.setImageResource(R.drawable.indicator_expanded);
        } else {
            holder.indicator.setImageResource(R.drawable.indicator_unexpanded);
        }

        return convertView;
    }

    private GroupHolder getGroupHolder(final View view) {
        GroupHolder holder = (GroupHolder) view.getTag();
        if (null == holder) {
            holder = new GroupHolder(view);
            view.setTag(holder);
        }
        return holder;
    }

    private class GroupHolder {
        TextView name;
        ImageView indicator;
        TextView onlineNum;

        public GroupHolder(View view) {
            name = (TextView) view.findViewById(R.id.group_name);
            indicator = (ImageView) view.findViewById(R.id.group_indicator);
            onlineNum = (TextView) view.findViewById(R.id.online_count);
        }
    }

    @Override
    public void updateHeader(View header, int groupPosition, int childPosition, int alpha) {
        ((TextView) header.findViewById(R.id.group_name)).setText(titles.get(groupPosition));
        ((TextView) header.findViewById(R.id.online_count)).setText(getChildrenCount
                (groupPosition) + "/" + getChildrenCount(groupPosition));
        header.setAlpha(alpha);
    }
}
