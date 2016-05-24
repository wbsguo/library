package com.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.bitmapfun.util.ImageFetcher;

import java.util.List;

import me.maxwin.view.ScaleImageView;

/**
 * Created by wangbs on 16/5/24.
 */
public class StaggeredAdapter extends BaseAdapter {
    private Context mContext;
    private List<DuitangInfo> mInfos;
    private ImageFetcher mImageFetcher;
    public StaggeredAdapter(Context mContext, List<DuitangInfo> mInfos, ImageFetcher mImageFetcher) {
        this.mContext = mContext;
        this.mInfos = mInfos;
        this.mImageFetcher=mImageFetcher;
    }

    public void setmInfos(List<DuitangInfo> mInfos) {
        this.mInfos = mInfos;
    }
    public void addMore(List<DuitangInfo> datas) {
        mInfos.addAll(datas);
    }
    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        DuitangInfo duitangInfo = mInfos.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
            convertView = layoutInflator.inflate(R.layout.infos_list, null);
            holder = new ViewHolder();
            holder.imageView = (ScaleImageView) convertView.findViewById(R.id.news_pic);
            holder.contentView = (TextView) convertView.findViewById(R.id.news_title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageWidth(duitangInfo.getWidth());
        holder.imageView.setImageHeight(duitangInfo.getHeight());
        holder.contentView.setText(duitangInfo.getMsg());
        mImageFetcher.loadImage(duitangInfo.getIsrc(), holder.imageView);
        return convertView;
    }
    class ViewHolder {
        ScaleImageView imageView;
        TextView contentView;
        TextView timeView;
    }
}
