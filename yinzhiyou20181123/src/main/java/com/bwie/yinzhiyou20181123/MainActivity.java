package com.bwie.yinzhiyou20181123;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.bwie.yinzhiyou20181123.adpter.MainAdapter;
import com.bwie.yinzhiyou20181123.fragment.FragmentLeft;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState==null){
        getSupportFragmentManager().beginTransaction().add(R.id.fragleft,new FragmentLeft()).commit();
        }

        drawerLayout = findViewById(R.id.drawer);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.colser);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showPager(int position){
        viewPager.setCurrentItem(position);
        drawerLayout.closeDrawer(Gravity.START);
    }
}
