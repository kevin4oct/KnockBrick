package com.geren.kevin.knockbricks.main.presenter;

import com.geren.kevin.knockbricks.main.bean.Block;
import com.geren.kevin.knockbricks.main.view.IMainView;
import com.geren.kevin.knockbricks.main.view.MainActivity;

import java.util.Random;

public class MainPresenter {

    private IMainView activity;

    private Block[][] baseData;//初始数据集合
    private Block[][] lastData;//上一步数据集合
    private Block[][] nowData;//当前数据集合

    private int dataSize;//数组的大小
    private int dataNumber;//数据类型数量

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    //初始化数据
    public void initData(int dataSize,int dataNumber) {
        this.dataSize = dataSize;
        this.dataNumber = dataNumber;
        baseData = new Block[dataSize][dataSize];
        nowData = new Block[dataSize][dataSize];
        lastData = new Block[dataSize][dataSize];
        for (int x = 0; x < dataSize; x++) {
            for (int y = 0; y < dataSize; y++) {
                int value = new Random().nextInt(dataNumber) + 1;
                Block block = new Block(x, y, value);
                baseData[x][y] = block;
                lastData[x][y] = block;
                nowData[x][y] = block;
            }
        }
        activity.setData(nowData);
    }

    //重置数据
    public void rePlay() {
        activity.setData(baseData);
    }

    //敲击
    public void knock() {

    }


}
