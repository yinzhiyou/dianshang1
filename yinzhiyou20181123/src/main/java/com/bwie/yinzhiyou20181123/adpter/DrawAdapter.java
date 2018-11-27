package com.bwie.yinzhiyou20181123.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.yinzhiyou20181123.R;

public class DrawAdapter extends BaseAdapter {
    private String[] menuse=new String[]{"日志","帮助","无障碍服务","悬浮窗","连接电脑","关闭所有运行脚本"};
    private Context context;

    public DrawAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return menuse.length;
    }

    @Override
    public String getItem(int position) {
        return menuse[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.item_left,parent,false);
            holder=new ViewHolder(convertView);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.bindData(position);
        return convertView;
    }
    class ViewHolder{
        TextView textView;
        public ViewHolder(View view){
            textView=view.findViewById(R.id.text_left);
            view.setTag(this);
        }
        public void bindData(int position){
            textView.setText(menuse[position]);
        }
    }

}
