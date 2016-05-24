package com.example;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.android.bitmapfun.util.ImageFetcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.maxwin.view.XListView;


/**
 * Created by wangbs on 16/5/24.
 */
public class PinterestActivity extends FragmentActivity implements XListView.IXListViewListener {
    private XListView mAdapterView;
    private ImageFetcher mImageFetcher;
    private LinkedList<DuitangInfo> mInfos=new LinkedList<DuitangInfo>();
    private StaggeredAdapter mAdapter;
    private int currentPage = 0;
    private ContentTask task = new ContentTask(this, 2);
    private String urlPath="http://www.duitang.com/album/1733789/masn/p/%s/24/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinterest);
        mAdapterView=(XListView)findViewById(R.id.list);
        mAdapterView.setPullLoadEnable(true);
        mAdapterView.setXListViewListener(this);

        mImageFetcher = new ImageFetcher(this, 240);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.setExitTasksEarly(false);
        initView();
        AddItemToContainer(currentPage, 2);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    private void initView(){
        mAdapter=new StaggeredAdapter(this,mInfos,mImageFetcher);
        mAdapterView.setAdapter(mAdapter);
    }
    @Override
    public void onRefresh() {
        AddItemToContainer(++currentPage, 1);
    }

    @Override
    public void onLoadMore() {
        AddItemToContainer(++currentPage, 2);
    }
    private void AddItemToContainer(int pageindex, int type) {
        if (task.getStatus() != AsyncTask.Status.RUNNING) {
            String url = String.format(urlPath,pageindex);
            Log.d("MainActivity", "current url:" + url);
            ContentTask task = new ContentTask(this, type);
            task.execute(url);

        }
    }
    private class ContentTask extends AsyncTask<String, Integer, List<DuitangInfo>> {
        private Context mContext;
        private int mType = 1;
        public ContentTask(Context context, int type) {
            super();
            mContext = context;
            mType = type;
        }
        @Override
        protected List<DuitangInfo> doInBackground(String... params) {
            try {
                return parseNewsJSON(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<DuitangInfo> result) {
            if (mType == 1) {
                mAdapterView.stopRefresh();
                mAdapter.setmInfos(result);
                mAdapter.notifyDataSetChanged();
            } else if (mType == 2) {
                mAdapterView.stopLoadMore();
                mAdapter.addMore(result);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        protected void onPreExecute() {
        }

        public List<DuitangInfo> parseNewsJSON(String url) throws IOException {
            List<DuitangInfo> duitangs = new ArrayList<DuitangInfo>();
            String json = "";
            if (Helper.checkConnection(mContext)) {
                try {
                    json = Helper.getStringFromUrl(url);
                } catch (Exception e) {
                    Log.e("IOException is : ", e.toString());
                    e.printStackTrace();
                    return duitangs;
                }
            }
            Log.d("MainActiivty", "json:" + json);
            try {
                if (null != json) {
                    JSONObject newsObject = new JSONObject(json);
                    JSONObject jsonObject = newsObject.getJSONObject("data");
                    JSONArray blogsJson = jsonObject.getJSONArray("blogs");

                    for (int i = 0; i < blogsJson.length(); i++) {
                        JSONObject newsInfoLeftObject = blogsJson.getJSONObject(i);
                        DuitangInfo newsInfo1 = new DuitangInfo();
                        newsInfo1.setAlbid(newsInfoLeftObject.isNull("albid") ? "" : newsInfoLeftObject.getString("albid"));
                        newsInfo1.setIsrc(newsInfoLeftObject.isNull("isrc") ? "" : newsInfoLeftObject.getString("isrc"));
                        newsInfo1.setMsg(newsInfoLeftObject.isNull("msg") ? "" : newsInfoLeftObject.getString("msg"));
                        newsInfo1.setHeight(newsInfoLeftObject.getInt("iht"));
                        duitangs.add(newsInfo1);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return duitangs;
        }
    }
}
