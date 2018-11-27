package com.bwie.yinzhiyou20181123.adpter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwie.yinzhiyou20181123.fragment.FragmentHome;
import com.bwie.yinzhiyou20181123.fragment.FragmentPerson;
import com.bwie.yinzhiyou20181123.fragment.FrangmentLong;

public class MainAdapter extends FragmentPagerAdapter {
    private String[] menus=new String[]{
            "首页","找人","未登录"
    };
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 :
                return new FragmentHome();
            case 1 :
                return new FragmentPerson();
            case 2 :
                return new FrangmentLong();
        }
        return null;
    }

    @Override
    public int getCount() {
        return menus.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return menus[position];
    }
}
