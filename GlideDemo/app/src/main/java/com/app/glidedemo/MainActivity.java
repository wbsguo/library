package com.app.glidedemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.app.glidedemo.conview.LoggingListener;
import com.app.glidedemo.conview.MyTransformation;
import com.app.glidedemo.conview.RotateTransformation;
import com.app.glidedemo.conview.RoundedCornersTransformation;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    String imagePath="/storage/emulated/0/DCIM/100ANDRO/DSC_0006.JPG";
    String imageOul="http://api.v2.pokemonmap.xyz/vtest/data/timages/6693/2656693_0.jpg?1470224503";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView)findViewById(R.id.image);
        imageest3();
    }
    private void netUrl(){
        String url="http://52.192.66.23/data/timages/39/39_0.jpg?1454086351";
        Glide.with(this).load(Uri.parse(url)).centerCrop().into(image);
//        Glide.with(this).load(Uri.parse(url)).placeholder(new ColorDrawable(Color.WHITE)).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
    }
    private void whtest(){
        //设置宽高
        Glide.with(this).load(new File(imagePath)).override(100, 100).centerCrop().into(image);
    }
    private void fileImage(){
        Glide.with(this).load(new File(imagePath)).centerCrop().into(image);
    }

    /**
     * sd卡
     */
    private void sdImage(){
        Glide.with(this).load("file://"+imagePath).centerCrop().into(image);
    }
    /**
     * asset
     */
    private void assertImage(){
        String imageAssert="/android_asset/f003.gif";
        Glide.with(this).load("file://"+imageAssert).centerCrop().into(image);
    }
    /**
     * raw资源
     */
    private void rawImage(){
        String imageRaw="Android.resource://com.frank.glide/raw/raw_1";
//        String imageAssert="android.resource://com.frank.glide/raw/"+R.raw.raw_1;
        Glide.with(this).load(imageRaw).centerCrop().into(image);
    }
    /**
     * drawable资源
     */
    private void drawableImage(){
        String imageDrawable="android.resource://com.github.bumptech.glide/drawable/"+R.drawable.ic_launcher;
        Glide.with(this).load(imageDrawable).centerCrop().into(image);
    }

    /**
     * diskCacheStrategy(DiskCacheStrategy.ALL) 设置缓存全部尺寸,比如:一张图片,有100*100和200*200;它会缓存两张图片
     */
    private void cache(){
        Glide.with(this)
                .load("http://nuuneoi.com/uploads/source/playstore/cover.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
    }
    private void test1(){
        //优先加载
        Glide.with(this)
                .load(imagePath)
                .priority(Priority.HIGH)
                .into(image);
        //后加载
        Glide.with(this)
                .load(imagePath)
                .priority(Priority.LOW)
                .into(image);
    }
    private void thumb(){
        //用原图的1/10作为缩略图
        /*Glide
                .with(this)
                .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
                .thumbnail(0.1f)
                .into(image);*/
        //用其它图片作为缩略图
        String imagethurl="http://api.v2.pokemonmap.xyz/vtest/data/timages/6705/2656705_0_thumb.jpg?1470289930";
        DrawableTypeRequest<Uri> thumbnailRequest = Glide
                .with(this)
                .load(Uri.parse(imagethurl));
        Glide.with(this)
                .load(imageOul)
                .thumbnail(thumbnailRequest)
                .into(image);
    }
    private void percent(){
        Glide.with(this).load(imagePath).centerCrop().into(new GlideDrawableImageViewTarget(image){
				@Override
				public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
					super.onResourceReady(resource, animation);
				}

				@Override
				public void onStart() {
					super.onStart();
				}

				@Override
				public void onStop() {
					super.onStop();
				}

				@Override
				protected void setResource(GlideDrawable resource) {
					super.setResource(resource);
				}
			});
    }
    /**
     * 旋转90
     */
    private void rateImage() {
        Glide.with(this).load(new File(imagePath)).override(100, 100).centerCrop().transform( new RotateTransformation( this, 90f )).into(image);
    }
    /**
     * BitmapTransformation 或transform对图片处理
     */
    private void imageest(){
        //透明度
        Glide.with(this)
                .load(imageOul)
                .asBitmap()
                .transform(new MyTransformation(this))
                .into(image);
    }
    private void imageest2(){
        //透明度
        Glide.with(this)
                .load(imageOul)
                .asBitmap()
                .transform(new RoundedCornersTransformation(this,50))
                .into(image);
    }
    private void imageest3(){
        //监听器
        LoggingListener mCommonRequestListener = new LoggingListener<String, GlideDrawable>();
        Glide.with(this)
                .load(imageOul)
                .listener(mCommonRequestListener)
                .into(image);
    }
}
