package com.example.dianshang1;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dianshang1.model.BannerBean;
import com.example.dianshang1.util.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  
    private TextView commdotidy;
    private TextView original;
    private TextView discounts;
    private List<String> list;
    private Banner banner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    private String path="http://www.zhaoapi.cn/product/getProductDetail?pid=";
    private int page;
    private void initData() {
        Utils.getInstance().getResult(path + page, BannerBean.class, new Utils.CallCack<BannerBean>() {
            @Override
            public void onSuccess(BannerBean o) {

            }
        });

    }

    private void initView() {
        
        commdotidy = findViewById(R.id.text_commdotidy);
        original = findViewById(R.id.text_original);
        discounts = findViewById(R.id.text_discounts);


         banner= findViewById(R.id.image_pager);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                BannerBean.DataBean  dataBean= (BannerBean.DataBean) path;
                ImageLoader.getInstance().displayImage((String) path,imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });
    }
}
