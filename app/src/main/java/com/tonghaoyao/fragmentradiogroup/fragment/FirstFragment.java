package com.tonghaoyao.fragmentradiogroup.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tonghaoyao.fragmentradiogroup.base.BaseFragment;

/**
 * Created by dell1 on 2017-03-31 .
 * 作者: 童浩瑶 on 13:33
 * QQ号: 1339170870
 * 作用: 继承BaseFragment
 */

public class FirstFragment extends BaseFragment {

    private TextView textView;
    private String TAG = FirstFragment.class.getSimpleName();

    @Override
    protected View initView() {
        Log.e(TAG, "initView()第一个页面被初始化", null);
        textView = new TextView(mContext);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "initData()第一个页面的数据被初始化", null);
        textView.setText("我是第一个页面");

    }
}
