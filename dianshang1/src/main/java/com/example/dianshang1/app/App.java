package com.example.dianshang1.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.example.dianshang1.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(50*1024*1024)
                .memoryCacheSizePercentage(13)
                .defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                        .showImageOnFail(R.drawable.ic_launcher_background)
                        .showImageOnLoading(R.drawable.ic_launcher_background)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .cacheOnDisk(true)
                        .cacheInMemory(true)
                        .displayer(new RoundedBitmapDisplayer(18)).build()).build());
    }
}
