package com.bwie.yinzhiyou20181123.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.yinzhiyou20181123.R;
import com.bwie.yinzhiyou20181123.adpter.NewsAdapter;
import com.bwie.yinzhiyou20181123.db.NewsDao;
import com.bwie.yinzhiyou20181123.medlo.NewsBean;
import com.bwie.yinzhiyou20181123.util.NetUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.utils.L;

import java.util.List;

public class FragmentHome extends Fragment {

    private NewsAdapter adapter;
    private PullToRefreshListView pull;

    private int page;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmenthome,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        page=1;
        initView(view);
        intiData();

    }

    private void initView(final View view) {
        pull = view.findViewById(R.id.pull_listview);
        adapter = new NewsAdapter(getActivity());
        pull.setAdapter(adapter);
        pull.setMode(PullToRefreshBase.Mode.BOTH);


        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                intiData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                intiData();
            }
        });
    }

    private String path="http://www.xieast.com/api/news/news.php?page=";
    private void intiData() {
        if (!NetUtils.getInstance().hasNetwork(getActivity())){

            Toast.makeText(getActivity(),"无网络0000",Toast.LENGTH_LONG).show();
            List<NewsBean.DataBean> select = NewsDao.getInstance(getActivity()).select();
            Log.i("TAG",select.size()+"yhjk");
            adapter.setmData(select);
            pull.onRefreshComplete();
            return;
        }else {
            NetUtils.getInstance().getResult(path + page, NewsBean.class, new NetUtils.CallBacK<NewsBean>() {
                @Override
                public void onSuccess(NewsBean o) {
                    if (o == null || !o.ischecked()) {

                        Toast.makeText(getActivity(), "无网络", Toast.LENGTH_LONG).show();

                        return;
                    } else {
                        /*if (page==1){
                        NewsDao.getInstance(getActivity()).delect();
                        }*/
                        NewsDao.getInstance(getActivity()).addAll(o.getData());
                        if (page == 1) {

                            adapter.setmData(o.getData());


                        } else {
                            adapter.addData(o.getData());
                            NewsDao.getInstance(getActivity()).addAll(o.getData());
                        }
                        page++;

                        pull.onRefreshComplete();

                    }
                }
            });
        }
    }

}
