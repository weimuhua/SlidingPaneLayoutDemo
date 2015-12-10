package com.wayne.slidingpanelayoutdemo.normal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wayne.slidingpanelayoutdemo.R;

public class NormalModeActivity extends Activity implements SlidingPaneLayout.PanelSlideListener,
        AdapterView.OnItemClickListener {

    private static final String TAG = "NormalModeActivity";

    private WebView mWebview;
    private ListView mListView;
    private SlidingPaneLayout mSlidingPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_mode);
        initView();
        initData();
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        mListView = (ListView) findViewById(R.id.url_listview);
        mSlidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_panel_layout);
    }

    private void initData() {
        String[] strArr = getResources().getStringArray(R.array.url_arr);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strArr);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mWebview.setWebViewClient(new MyWebviewClient());
        mSlidingPaneLayout.setPanelSlideListener(this);
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        Log.d(TAG, "onPanelSlide");
    }

    @Override
    public void onPanelOpened(View panel) {
        Log.d(TAG, "onPanelOpened");
    }

    @Override
    public void onPanelClosed(View panel) {
        Log.d(TAG, "onPanelClosed");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                mWebview.loadUrl("http://www.sina.com.cn");
                break;
            case 1:
                mWebview.loadUrl("http://www.qq.com");
                break;
            case 2:
                mWebview.loadUrl("http://www.baidu.com");
                break;
        }
        mSlidingPaneLayout.closePane();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private class MyWebviewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            mWebview.loadUrl(url);
            return true;
        }
    }
}