package com.bwie.yinzhiyou20181123.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.yinzhiyou20181123.R;
import com.bwie.yinzhiyou20181123.medlo.NewsBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<NewsBean.DataBean> mData;

    public NewsAdapter(Context context) {
        this.context = context;
        mData=new ArrayList<>();
    }

    public void setmData(List<NewsBean.DataBean> data) {
        mData.clear();
        if(data!=null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    public void addData(List<NewsBean.DataBean> data){
        if (data!=null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    private final int COUNT=2;
    private final int TYPE_IMAGE=0;
    private final int TYPE_TEXT=1;
    @Override
    public int getViewTypeCount() {
        return COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position==TYPE_IMAGE?TYPE_IMAGE:TYPE_TEXT;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public NewsBean.DataBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(getItemViewType(position)==TYPE_IMAGE? R.layout.item_image:R.layout.item,parent,false);
            holder=new ViewHolder(convertView);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if (getItemViewType(position)==TYPE_IMAGE){
            holder.bindData(position);
        }else if (getItemViewType(position)==TYPE_TEXT){
            holder.bindIcon(position);
        }
        return convertView;
    }
    class ViewHolder{
    ImageView imageView_h,imageView_hd,icon_h,icon_hd;
    TextView textView_h,textView_hd;
    public ViewHolder(View view){
        imageView_h=view.findViewById(R.id.image_h);
        imageView_hd=view.findViewById(R.id.image_hd);
        icon_h=view.findViewById(R.id.icon_h);
        icon_hd=view.findViewById(R.id.icon_hd);
        textView_h=view.findViewById(R.id.text_h);
        textView_hd=view.findViewById(R.id.text_hd);
        view.setTag(this);
    }
    public void bindData(int position){
        textView_h.setText(mData.get(position).getAuthor_name());
        ImageLoader.getInstance().displayImage(mData.get(position).getThumbnail_pic_s(),imageView_h);

    }
    public void bindIcon(int position){
        textView_hd.setText(mData.get(position).getTitle());
        ImageLoader.getInstance().displayImage(mData.get(position).getThumbnail_pic_s(),imageView_hd);
        ImageLoader.getInstance().displayImage(mData.get(position).getThumbnail_pic_s02(),icon_h);
        ImageLoader.getInstance().displayImage(mData.get(position).getThumbnail_pic_s03(),icon_hd);
    }
    }
}
