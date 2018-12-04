package com.geren.kevin.knockbricks.main.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.geren.kevin.knockbricks.R;
import com.geren.kevin.knockbricks.main.bean.Block;
import com.geren.kevin.knockbricks.main.presenter.MainPresenter;
import com.geren.kevin.knockbricks.views.Chessboard;

public class MainActivity extends AppCompatActivity implements IMainView, View.OnClickListener, Chessboard.ChessBoardClickListener {

    public static final String TAG = "MainActivity";
    private MainPresenter presenter;

    private TextView tv;
    private Button btn_reset;
    private Chessboard chessboard;

    int level;//关卡
    private int dataSize = 1;//数组的大小
    private int dataNumber = 1;//数据类型数量
    private Button btn_hummer;
    private Button btn_last;

    //提供Intent
    public static Intent getMyIntent(Context context, int level) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.putExtra("level", level);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initListener();
    }

    //初始化
    private void initData() {
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        dataSize = 10;
        dataNumber = 5;
        presenter = new MainPresenter(this);
        presenter.initData(dataSize, dataNumber);
    }

    private void initView() {
        chessboard = findViewById(R.id.chessbord);
        tv = findViewById(R.id.tv_main);
        btn_hummer = findViewById(R.id.btn_main_hammer);
        btn_last = findViewById(R.id.btn_main_last);
        btn_reset = findViewById(R.id.btn_main_reset);
        tv.setText("第" + level + "关");
    }

    private void initListener() {
        btn_reset.setOnClickListener(this);
        btn_last.setOnClickListener(this);
        btn_hummer.setOnClickListener(this);
        chessboard.setOnChessBoardClickListener(this);
    }

    //设置数据
    @Override
    public void setData(Block[][] data) {
        if (chessboard == null) {
            Log.e(TAG, "棋盘是空的");
        } else {
            chessboard.setData(dataSize, data);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_reset:
                presenter.rePlay();
                break;
            case R.id.btn_main_hammer:
                Toast.makeText(this, "锤子", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_main_last:
                presenter.lastData();
                break;
        }
    }

    @Override
    public void chessboardClicked(Block block) {
        Toast.makeText(this, block.toString(), Toast.LENGTH_SHORT).show();
        presenter.knock(block);
    }
}
