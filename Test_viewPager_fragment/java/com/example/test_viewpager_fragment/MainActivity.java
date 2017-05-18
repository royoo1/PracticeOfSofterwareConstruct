package com.example.test_viewpager_fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.model.UserSession;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    Context context = null;
    ViewPager pager = null;
    TextView t1, t2, t3;
    View view1, view2, view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        initTextView();
        initPagerViewer();

    }


    /**
     * 初始化标题
     */
    private void initTextView() {
        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        t3 = (TextView) findViewById(R.id.text3);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);

    }

    /**
     * 初始化PageViewer
     */
    private void initPagerViewer() {
        List<Fragment> fragments = new ArrayList<Fragment>();

        LayoutInflater li = LayoutInflater.from(this);
        view1 = li.inflate(R.layout.layout1, null);
        view2 = li.inflate(R.layout.layout2, null);
        view3 = li.inflate(R.layout.layout3, null);
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new CenterFragment());

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);


        pager = (ViewPager) findViewById(R.id.viewpage);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        pager.setOnPageChangeListener(new MyOnPageChangeListener());
    }


    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageSelected(int arg0) {//通过view 的位置来更改按钮的text
            resert();
            int currentItems = pager.getCurrentItem();
            switch (currentItems) {

                case 0:
                    t1.setText("选中");
                    break;
                case 1:
                    t2.setText("选中");
                    break;
                case 2:
                    t3.setText("选中");
                    break;

                default:
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    @Override
    public void onClick(View v) {
        resert();

        switch (v.getId()) {
            case R.id.text1:
                pager.setCurrentItem(0);
                t1.setText("选中");
                break;
            case R.id.text2:
                pager.setCurrentItem(1);
                t2.setText("选中");
                break;
            case R.id.text3:
                pager.setCurrentItem(2);
                t3.setText("选中");
                break;
        }

    }
    //初始化按钮的选中情况
    public void resert()
    {
        t1.setText("发榜招聘");
        t2.setText("我要兼职");
        t3.setText("个人中心");
    }
}


