package com.bwie.yinzhiyou20181123.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bwie.yinzhiyou20181123.medlo.NewsBean;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class NewsDao {
    private static NewsDao instance;
    private SQLiteDatabase database;
    public NewsDao(Context context){
        database=new DBHelpter(context).getWritableDatabase();
    }
public static NewsDao getInstance(Context context){
if (instance==null){
    instance=new NewsDao(context);
}
    return instance;
}
public void add(NewsBean.DataBean bean){
    ContentValues values=new ContentValues();
    values.put("name",bean.getAuthor_name());
    values.put("title",bean.getTitle());
    values.put("thumbnail_pic_s",bean.getThumbnail_pic_s());
    values.put("thumbnail_pic_s02",bean.getThumbnail_pic_s02());
    values.put("thumbnail_pic_s03",bean.getThumbnail_pic_s03());
    //-1   id
    long result = database.insert("adminss", null, values);
    Log.i("TEST", "insert result: " + result);

}
public void addAll(List<NewsBean.DataBean> list){

            for (NewsBean.DataBean data:list){
                add(data);

            }


}
public List<NewsBean.DataBean> select(){
List<NewsBean.DataBean> beans=new ArrayList<>();
    Cursor user = database.query("adminss", null, null, null, null, null, null);
    if (user!=null){
        while (user.moveToNext()){
            String name = user.getString(user.getColumnIndex("name"));
            String title = user.getString(user.getColumnIndex("title"));
            String thumbnail_pic_s = user.getString(user.getColumnIndex("thumbnail_pic_s"));
            String thumbnail_pic_s02 = user.getString(user.getColumnIndex("thumbnail_pic_s02"));
            String thumbnail_pic_s03 = user.getString(user.getColumnIndex("thumbnail_pic_s03"));
            NewsBean.DataBean bean=new NewsBean.DataBean(name,title,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03);
            beans.add(bean);
            ///data/data/xx.xx.xx/cahahe/imge
        }
    }
    return beans;
}
public void delect(){
        database.delete("admin",null,null);
}
}
