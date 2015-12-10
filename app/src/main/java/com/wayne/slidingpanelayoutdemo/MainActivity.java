package com.wayne.slidingpanelayoutdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wayne.slidingpanelayoutdemo.normal.NormalModeActivity;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private static final int INTENT_NORMAL_MODE = 0;
    private static final int INTENT_WECHAT_MODE = 1;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
    }

    private void initData() {
        String[] strArr = getResources().getStringArray(R.array.home_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strArr);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case INTENT_NORMAL_MODE:
                startActivity(new Intent(this, NormalModeActivity.class));
                break;
            case INTENT_WECHAT_MODE:
                break;
        }
    }
}
