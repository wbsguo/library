package com.example;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bitmapfun.util.ImageFetcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.maxwin.view.XListView;

public class MainActivity extends Activity {
    private TextView searchAllmusicTitle, searchAblumTitle, searchMusicTitle;
    private ImageView searchLineimag;
    private ViewPager searchViewpage;
    private int screen_width;
    private List<View> listViews = new ArrayList<View>();
    private int currIndex;// 记录当前的位置
    private List<DataModel> dataModels=new ArrayList<DataModel>();
    private ImageFetcher mImageFetcher;
    private StaggeredAdapter mAdapter;
    private LinkedList<DuitangInfo> mInfos=new LinkedList<DuitangInfo>();
    private int currentPage = 0;
    private ContentTask task = new ContentTask(this, 2);
    private String urlPath="http://www.duitang.com/album/1733789/masn/p/%s/24/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getDatas();
        findUI();
        initView();
        AddItemToContainer(currentPage, 2);
    }
    public void getDatas(){
        for(int i=0;i<3;i++){
            DataModel dataModel=new DataModel();
            dataModel.setId(i);
            dataModel.setName("测试"+i);
            dataModels.add(dataModel);
        }
    }
    private void findUI(){
        searchAllmusicTitle =(TextView)findViewById(R.id.search_allmusic_title);
        searchAblumTitle =(TextView)findViewById(R.id.search_ablum_title);
        searchMusicTitle =(TextView)findViewById(R.id.search_music_title);
        searchLineimag=(ImageView)findViewById(R.id.search_lineimag);
        searchViewpage = (ViewPager) findViewById(R.id.search_viewpage);
        searchAllmusicTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewpage.setCurrentItem(0);
            }
        });
        searchAblumTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewpage.setCurrentItem(1);
            }
        });
        searchMusicTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewpage.setCurrentItem(2);
            }
        });
    }
    private void init(){
        screen_width= AppInfoUtils.getInstance().getScreenWidth(this);
        mImageFetcher = new ImageFetcher(this, 240);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.setExitTasksEarly(false);
    }
    private void initView(){
        ViewGroup.LayoutParams params = searchLineimag.getLayoutParams();
        int imagWidth = (screen_width / 3);
        params.width = imagWidth;
        searchLineimag.setLayoutParams(params);
        viewpageUI();
    }
    private int item;
    private void viewpageUI() {
        LayoutInflater inflater = getLayoutInflater();
        for(int i=0;i<3;i++){
            View childView = inflater.inflate(R.layout.viewpage_item, null);
            findChildViewUI(childView);
            listViews.add(childView);
        }
        searchViewpage.setAdapter(new MyPagerAdapter(listViews));
        searchViewpage.setCurrentItem(item);
        searchViewpage.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    private void findChildViewUI(View childView){
        final XListView mAdapterView=(XListView)childView.findViewById(R.id.list);
        mAdapterView.setPullLoadEnable(true);
        mAdapter=new StaggeredAdapter(this,mInfos,mImageFetcher);
        mAdapterView.setAdapter(mAdapter);
        mAdapterView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mAdapterView.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                mAdapterView.stopLoadMore();
            }
        });
    }
    private void setImagPosition(int position) {
        Animation animation = null;
        if (currIndex == 0) {
            animation = new TranslateAnimation(0,
                    (screen_width / 3) * position, 0, 0);
            ;
        } else if (currIndex == 1) {
            animation = new TranslateAnimation((screen_width / 4),
                    (screen_width / 3) * position, 0, 0);
            ;
        } else if (currIndex == 2) {
            animation = new TranslateAnimation((screen_width / 4) * 2,
                    (screen_width / 3) * position, 0, 0);
            ;
        }
        if (position == 0) {
            searchAllmusicTitle.setTextColor(getResources().getColor(
                    R.color.search_select_title));
            searchAblumTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
            searchMusicTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
        } else if (position == 1) {
            searchAllmusicTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
            searchAblumTitle.setTextColor(getResources().getColor(
                    R.color.search_select_title));
            searchMusicTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
        } else if (position == 2) {
            searchAllmusicTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
            searchAblumTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
            searchMusicTitle.setTextColor(getResources().getColor(
                    R.color.search_select_title));
        }
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(300);
        searchLineimag.startAnimation(animation);
        currIndex = position;
    }
    public class MyPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public void onPageSelected(int position) {
            item=position;
            setImagPosition(position);
        }
        /**
         * 状态改变时调用:三种状态(0,1,2)
         * 0:默示什么都没做
         * 1:正在滑动
         * 2:滑动完毕了
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
        /**
         * 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到
         * arg0:当前页面，及你点击滑动的页面
         * arg1:当前页面偏移的百分比
         * arg2:当前页面偏移的像素位置
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
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
                mAdapter.setmInfos(result);
                mAdapter.notifyDataSetChanged();
            } else if (mType == 2) {
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
