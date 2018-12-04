package com.geren.kevin.knockbricks.main.presenter;

import com.geren.kevin.knockbricks.main.bean.Block;
import com.geren.kevin.knockbricks.main.view.IMainView;
import com.geren.kevin.knockbricks.main.view.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPresenter {

    public static final String TAG = "MainPresenter";
    private IMainView activity;

    private Block[][] baseData;//初始数据集合
    private Block[][] lastData;//上一步数据集合
    private Block[][] nowData;//当前数据集合

    private int dataSize;//数组的大小
    private int dataNumber;//数据类型数量

    private List<Block> clickedList = new ArrayList<>();//点击后记录颜色块的id的容器
    private int value;//点击的块的value

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    //初始化数据
    public void initData(int dataSize, int dataNumber) {
        this.dataSize = dataSize;
        this.dataNumber = dataNumber;
        baseData = new Block[dataSize][dataSize];
        lastData = new Block[dataSize][dataSize];
        nowData = new Block[dataSize][dataSize];
        for (int x = 0; x < dataSize; x++) {
            for (int y = 0; y < dataSize; y++) {
                int id = x * dataSize + y;
                int value = new Random().nextInt(dataNumber) + 1;
                Block block = new Block(id, x, y, value);
                Block block1 = new Block(id, x, y, value);
                Block block2 = new Block(id, x, y, value);
                baseData[x][y] = block;
                lastData[x][y] = block1;
                nowData[x][y] = block2;
            }
        }
        activity.setData(nowData);
    }

    //重置数据
    public void rePlay() {
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < dataSize; j++) {
                nowData[i][j].setValue(baseData[i][j].getValue());
            }
        }
        activity.setData(nowData);
    }

    //恢复上一步
    public void lastData() {
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < dataSize; j++) {
                nowData[i][j].setValue(lastData[i][j].getValue());
            }
        }
        activity.setData(nowData);
    }

    //敲击
    public void knock(Block block) {
        //记录上一步
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < dataSize; j++) {
                lastData[i][j].setValue(nowData[i][j].getValue());
            }
        }
//
        getIds(block);
        drawBlack();

//        setRow();

        activity.setData(nowData);

    }

    //将value==0的放在最上边去
    private void setRow() {
        for (int i = 0; i < clickedList.size(); i++) {
// TODO: 2018/12/4
            Block block = clickedList.get(i);
            int x = block.getX();
            int y = block.getY();
            for (int j = 0; j <= y; j++) {
                if (j < y) {
                    nowData[x][j].setValue(nowData[x][j + 1].getValue());
                } else {
                    int m = 0;
                    while (true) {
                        int value = nowData[x][m].getValue();
                        if (value != 0) {
                            nowData[x][m].setValue(0);
                            break;
                        }
                        m++;
                    }
                }
            }

        }
    }

    //测试，将ids中的black弄黑
    private void drawBlack() {
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < dataSize; j++) {
                for (int k = 0; k < clickedList.size(); k++) {
                    int id = clickedList.get(k).getId();
                    if (nowData[i][j].getId() == id) {
                        nowData[i][j].setValue(0);
                    }
                }
            }
        }
    }


    //获取周围相同value的id集合
    private void getIds(Block block) {
        clickedList.clear();
        value = block.getValue();
        digui(block);
    }

    private void digui(Block block) {

        Block topBlock = getTopBlock(block);
        Block bottomBlock = getBottomBlock(block);
        Block leftBlock = getLeftBlock(block);
        Block rightBlock = getRightBlock(block);

        boolean isT = false;
        boolean isB = false;
        boolean isL = false;
        boolean isR = false;

        if (topBlock != null) {
            isT = shoudAdd(topBlock);
            if (isT) {
                clickedList.add(topBlock);
            }
        }
        if (bottomBlock != null) {
            isB = shoudAdd(bottomBlock);
            if (isB) {
                clickedList.add(bottomBlock);
            }
        }
        if (leftBlock != null) {
            isL = shoudAdd(leftBlock);
            if (isL) {
                clickedList.add(leftBlock);
            }
        }
        if (leftBlock != null) {
            isR = shoudAdd(rightBlock);
            if (isR) {
                clickedList.add(leftBlock);
            }
        }
        //递归
        if (isT) {
            digui(topBlock);
        }
        if (isB) {
            digui(bottomBlock);
        }
        if (isL) {
            digui(leftBlock);
        }
        if (isR) {
            digui(rightBlock);
        }

    }

    private boolean shoudAdd(Block block) {
        if (block != null) {
            int value = block.getValue();
            if (!clickedList.contains(block) && this.value == value && value != 0) {
                return true;
            }
        }
        return false;
    }

    private Block getTopBlock(Block block) {
        int x = block.getX();
        int y = block.getY();
        int by = y - 1;
        if (by < 0) {
            return null;
        }
        return nowData[x][by];
    }

    private Block getBottomBlock(Block block) {
        int x = block.getX();
        int y = block.getY();
        int by = y + 1;
        if (by > dataSize - 1) {
            return null;
        }
        return nowData[x][by];
    }

    private Block getLeftBlock(Block block) {
        int x = block.getX();
        int y = block.getY();
        int bx = x - 1;
        if (bx < 0) {
            return null;
        }
        return nowData[bx][y];
    }

    private Block getRightBlock(Block block) {
        int x = block.getX();
        int y = block.getY();
        int bx = x + 1;
        if (bx > dataSize - 1) {
            return null;
        }
        return nowData[bx][y];
    }


}
