package com.bwie.yinzhiyou20181123.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.yinzhiyou20181123.R;
import com.bwie.yinzhiyou20181123.adpter.DrawAdapter;

public class FragmentLeft extends Fragment {
    private DrawAdapter drawAdapter;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentleft,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    initData();

    }

    private void initView(View view) {
        listView=view.findViewById(R.id.listview);
        drawAdapter=new DrawAdapter(getActivity());
        listView.setAdapter(drawAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"wu",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initData() {

    }
}
