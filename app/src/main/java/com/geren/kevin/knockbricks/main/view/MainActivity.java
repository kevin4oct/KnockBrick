package com.geren.kevin.knockbricks.main.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.geren.kevin.knockbricks.R;
import com.geren.kevin.knockbricks.main.bean.Block;
import com.geren.kevin.knockbricks.main.presenter.MainPresenter;
import com.geren.kevin.knockbricks.views.Chessboard;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements IMainView, View.OnClickListener, Chessboard.ChessBoardClickListener {

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
    private TextView tv_hint;
    private TextView tv_clicked;

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

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);

        initView();
        initListener();
    }

    //初始化view
    private void initView() {
        chessboard = findViewById(R.id.chessbord);
        tv = findViewById(R.id.tv_main);
        tv_hint = findViewById(R.id.tv_main_hint);
        tv_clicked = (TextView) findViewById(R.id.tv_main_clickhint);
        btn_hummer = findViewById(R.id.btn_main_hammer);
        btn_last = findViewById(R.id.btn_main_last);
        btn_reset = findViewById(R.id.btn_main_reset);
        tv.setText("第" + level + "关");
    }

    //设置监听
    private void initListener() {
        btn_reset.setOnClickListener(this);
        btn_last.setOnClickListener(this);
        btn_hummer.setOnClickListener(this);
        chessboard.setOnChessBoardClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    //初始化数据
    private void initData() {
        if (level < 5) {
            dataSize = 10;//列数
            dataNumber = level;//砖块种类数
        } else if (level < 10) {
            dataSize = 10;
            dataNumber = level;
        } else {
            dataSize = level;
            dataNumber = 10;
        }
        presenter = new MainPresenter(this);
        presenter.initData(dataSize, dataNumber);
    }

    //设置显示数据
    @Override
    public void setData(Block[][] data) {
        chessboard.setData(dataSize, data);
    }

    @Override
    public void setClickHint(List<Block> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("需要移动的Block\r\n");
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i).toString() + "\r\n");
        }
        tv_hint.setText(builder.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_reset://重置
                presenter.rePlay();
                break;
            case R.id.btn_main_last://上一步
                presenter.lastData();
                break;
            case R.id.btn_main_hammer:
                Toast.makeText(this, "锤子", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void chessboardClicked(Block block) {
        tv_clicked.setText("点击的Block\r\n" + block.toString());
        presenter.knock(block);
    }
}
