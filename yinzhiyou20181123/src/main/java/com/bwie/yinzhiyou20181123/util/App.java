package com.bwie.yinzhiyou20181123.util;

import android.app.Application;
import android.graphics.Bitmap;

import com.bwie.yinzhiyou20181123.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSizePercentage(13)
                .memoryCacheSize(50*1024*1024)
                .defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                        .showImageOnLoading(R.drawable.ic_launcher_background)
                        .showImageOnFail(R.drawable.ic_launcher_background)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .cacheOnDisk(true)
                        .cacheInMemory(true)
                        .displayer(new RoundedBitmapDisplayer(18)).build()).build());
    }
}
