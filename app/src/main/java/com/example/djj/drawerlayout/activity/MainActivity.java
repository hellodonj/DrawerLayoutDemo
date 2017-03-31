package com.example.djj.drawerlayout.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.djj.drawerlayout.R;
import com.example.djj.drawerlayout.adapter.ContentAdapter;
import com.example.djj.drawerlayout.adapter.MyAdapter;
import com.example.djj.drawerlayout.model.ContentModel;
import com.example.djj.drawerlayout.view.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * desc: MainActivty
 * author: dj
 * date: 2017/3/30 15:17
 */
public class MainActivity extends Activity {

    private DrawerLayout drawerLayout;
    private List<ContentModel> list;
    private ContentAdapter adapter;

    private ListView mListView;          //列表控件
    public List<MyObject> mData;       //数据源（模拟）
    private MyAdapter mAdapter;        //自定义适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        View view = findViewById(R.id.drawer_include);
        ListView listView = (ListView) view.findViewById(R.id.left_listview);

        initData();
        adapter = new ContentAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(MainActivity.this, "你点击的" + position + 9, Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "你点击的" + position, Toast.LENGTH_SHORT).show();
            }
        });

        TopBar topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setOnLeftAndRightClickListener(new TopBar.OnLeftAndRightClickListener() {
            @Override
            public void OnLeftButtonClick() {
                drawerLayout.openDrawer(GravityCompat.START);
            }

            @Override
            public void OnRightButtonClick() {

            }
        });

         /* 初始化控件 */
        mListView = (ListView) findViewById(R.id.progress_list);
        /* 初始化数据 */
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            //组装数据
            MyObject myObject = new MyObject();
            myObject.text = "按钮" + i;
            myObject.progress = -1;
            //添加到数据源
            mData.add(myObject);
        }
        /* 填充适配器 */
        mAdapter = new MyAdapter(this, mData);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        list = new ArrayList<ContentModel>();

        list.add(new ContentModel(R.drawable.doctoradvice2, "新闻"));
        list.add(new ContentModel(R.drawable.infusion_selected, "订阅"));
        list.add(new ContentModel(R.drawable.mypatient_selected, "图片"));
        list.add(new ContentModel(R.drawable.mywork_selected, "视频"));
        list.add(new ContentModel(R.drawable.nursingcareplan2, "跟帖"));
        list.add(new ContentModel(R.drawable.personal_selected, "投票"));
    }

    /**
     * 实体对象，用于保存数据
     */
    public class MyObject {
        public Integer progress;       //下载进度
        public String text;            //按钮文字
    }
}
