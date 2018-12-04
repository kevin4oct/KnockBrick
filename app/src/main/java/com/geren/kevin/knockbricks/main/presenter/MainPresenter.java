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

    private List<Integer> ids = new ArrayList<>();//点击后记录颜色块的id的容器
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


        activity.setData(nowData);

    }

    private void setRow() {

    }

    //测试，将ids中的black弄黑
    private void drawBlack() {
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < dataSize; j++) {
                for (int k = 0; k < ids.size(); k++) {
                    int id = ids.get(k);
                    if (nowData[i][j].getId() == id) {
                        nowData[i][j].setValue(0);
                    }
                }
            }
        }
    }


    //获取周围相同value的id集合
    private void getIds(Block block) {
        ids.clear();
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
            int id = topBlock.getId();
            isT = shoudAdd(topBlock);
            if (isT) {
                ids.add(id);
            }
        }
        if (bottomBlock != null) {
            int id = bottomBlock.getId();
            isB = shoudAdd(bottomBlock);
            if (isB) {
                ids.add(id);
            }
        }
        if (leftBlock != null) {
            int id = leftBlock.getId();
            isL = shoudAdd(leftBlock);
            if (isL) {
                ids.add(id);
            }
        }
        if (leftBlock != null) {
            isR = shoudAdd(rightBlock);
            if (isR) {
                ids.add(rightBlock.getId());
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
            int id = block.getId();
            int value = block.getValue();
            if (!ids.contains(id) && this.value == value) {
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
