package org.jasonhww.customviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import org.jasonhww.customviewdemo.hcp.draw.utils.MainDrawActivity;
import org.jasonhww.customviewdemo.scroolmethod.ScrollActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private MainActivity mMainActivity;
    private RecyclerView mRecycler;
    private String[] titleArray = {"滑动的几种方式~1.1-1.4", "HCP自定义View的绘制"};
    private Class[] activityArray = {ScrollActivity.class, MainDrawActivity.class};
    private MainAdapter mMainAdapter;
    private List<MainViewBean> mMainViewBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainActivity = this;
        init();
        initRecycler();
    }


    private void init() {
        for (int i = 0; i < titleArray.length; i++) {
            MainViewBean mainViewBean = new MainViewBean();
            mainViewBean.setTitle(titleArray[i]);
            mainViewBean.setActivityClass(activityArray[i]);
            mMainViewBeanList.add(mainViewBean);
        }
    }

    private void initRecycler() {
        mRecycler = findViewById(R.id.recycler);

        mMainAdapter = new MainAdapter(mMainViewBeanList);
        mMainAdapter.setOnItemClickListener(onItemClickListener);
        mRecycler.setAdapter(mMainAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(mMainActivity));

    }


    private BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            final MainViewBean mainViewBean = mMainViewBeanList.get(position);
            Intent intent = new Intent(mMainActivity, mainViewBean.getActivityClass());
            startActivity(intent);
        }
    };
}
