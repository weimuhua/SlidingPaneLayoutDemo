package com.wayne.slidingpanelayoutdemo.wechat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;


import com.wayne.slidingpanelayoutdemo.R;

import java.lang.reflect.Field;

public class BaseSwipeBackActivity extends Activity implements SlidingPaneLayout.PanelSlideListener {

    private SlidingPaneLayout mSlidingPaneLayout;
    private FrameLayout mContainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            mSlidingPaneLayout = new SlidingPaneLayout(this);
            //属性
            Field field = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            field.setAccessible(true);
            field.set(mSlidingPaneLayout, 0);

            mSlidingPaneLayout.setPanelSlideListener(this);
            mSlidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);

        View leftView = new View(this);
        leftView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mSlidingPaneLayout.addView(leftView, 0);

        mContainLayout = new ContentFrameLayout(this);
        mContainLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
        mContainLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        mSlidingPaneLayout.addView(mContainLayout, 1);
    }

    @Override
    public void setContentView(int id) {
        setContentView(getLayoutInflater().inflate(id, null));
    }

    public void setContentView(View v) {
        setContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View v, LayoutParams params) {
        super.setContentView(mSlidingPaneLayout, params);
        mContainLayout.removeAllViews();
        mContainLayout.addView(v, params);
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(View panel) {
        finish();
        overridePendingTransition(0, R.anim.sliding_out_right);
    }

    @Override
    public void onPanelClosed(View panel) {

    }
}
