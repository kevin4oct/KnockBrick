package com.geren.kevin.knockbricks.level.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.geren.kevin.knockbricks.R;
import com.geren.kevin.knockbricks.level.adapters.LevelRecyclerViewAdapter;
import com.geren.kevin.knockbricks.main.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity
        implements LevelRecyclerViewAdapter.LevelAdapterClickedListener {

    public static final String TAG = LevelActivity.class.getSimpleName();
    private RecyclerView myRecycler;
    private LevelRecyclerViewAdapter adapter;
    List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        initView();
        initListener();
    }

    private void initListener() {
        adapter.setOnLevelAdapterCloickListener(this);
    }

    private void initView() {
        myRecycler = (RecyclerView) findViewById(R.id.recycler_level);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LevelRecyclerViewAdapter(this, myRecycler);
        myRecycler.setAdapter(adapter);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        adapter.refresh(list);
    }

    @Override
    public void levelAdapterClicked(int position) {
        Intent myIntent = MainActivity.getMyIntent(this, position + 1);
        startActivity(myIntent);
    }
}
