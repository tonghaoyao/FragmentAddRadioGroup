package com.tonghaoyao.fragmentradiogroup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.tonghaoyao.fragmentradiogroup.base.BaseFragment;
import com.tonghaoyao.fragmentradiogroup.fragment.FirstFragment;
import com.tonghaoyao.fragmentradiogroup.fragment.SecondFragment;
import com.tonghaoyao.fragmentradiogroup.fragment.ForthFragment;
import com.tonghaoyao.fragmentradiogroup.fragment.ThirdFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_main;

//    private FrameLayout fl_main;

    private ArrayList<BaseFragment> mFragments;

    private RGOnCheckedChangeListener mOnCheckedChangeListener;


    //在底部的RadioGroup改变时,改变值--设置第几个页面显示
    private int position = 0;

    //上次切换的Fragment
    private Fragment mContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initmFragments();

        switchFragment(null, getFragment());


    }


    private void initView() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);

        mOnCheckedChangeListener = new RGOnCheckedChangeListener();
        rg_main.setOnCheckedChangeListener(mOnCheckedChangeListener);
        rg_main.check(R.id.rb_video);

    }

    private void initmFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new FirstFragment());
        mFragments.add(new SecondFragment());
        mFragments.add(new ThirdFragment());
        mFragments.add(new ForthFragment());
    }


    class RGOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Fragment fromFragment = getFragment();

            switch (checkedId) {
                case R.id.rb_video:
                    position = 0;
                    group.check(R.id.rb_video);
                    break;

                case R.id.rb_audio:
                    position = 1;
                    group.check(R.id.rb_audio);
                    break;

                case R.id.rb_netvideo:
                    position = 2;
                    group.check(R.id.rb_netvideo);
                    break;

                case R.id.rb_netaudio:
                    position = 3;
                    group.check(R.id.rb_netaudio);
                    break;

                default:
                    position = 0;
                    group.check(R.id.rb_video);
                    break;

            }

            Fragment toFragmrent = getFragment();
            switchFragment(fromFragment, toFragmrent);
        }
    }

    private void switchFragment(Fragment from, Fragment to) {

        if (mContent != to) {
            mContent = to;  //mContent在这里赋值
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();

            //先判断下一个Fragment有没有被add过
            if (!to.isAdded()) {
                //没有被add过

                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null){
                    transaction.add(R.id.fl_main, to).commit();
                }
            }else{
                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null){
                    //show()方法不同于add
                    transaction.show(to).commit();
                }
            }
        }

        /**
         * 这里可以优化
         */
//        //得到FragmentManager
//        FragmentManager fm = getSupportFragmentManager();
//
//        //开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//
//        //替换
//        transaction.replace(R.id.fl_main, fragment);
//
//        //提交事务
//        transaction.commit();

    }

    private Fragment getFragment() {
        if (mFragments != null) {

            BaseFragment baseFragment = mFragments.get(position);
            return baseFragment;
        }
        return null;
    }
}
