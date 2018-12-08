package com.geren.kevin.knockbricks.main.presenter;

import android.util.Log;

import com.geren.kevin.knockbricks.main.bean.Block;
import com.geren.kevin.knockbricks.main.view.IMainView;
import com.geren.kevin.knockbricks.main.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    private int[][] levelData = {
            {3, 3, 4, 5, 3, 2, 5, 3, 1, 1, 3, 5, 2, 1, 5, 5, 2, 2, 1, 3, 3, 5, 3, 3, 1, 2, 2, 3, 1, 3, 2, 5, 5, 1, 4, 1, 3, 3, 2, 3, 4, 1, 3, 4, 3, 3, 5, 2, 5, 1, 1, 2, 3, 2, 3, 3, 1, 1, 4, 2, 5, 4, 2, 4, 1, 5, 3, 4, 1, 1, 2, 3, 2, 1, 5, 5, 2, 3, 4, 2, 4, 5, 2, 5, 1, 5, 3, 2, 4, 4, 5, 3, 1, 1, 3, 3, 3, 1, 4, 3},
            {2, 1, 4, 3, 1, 3, 4, 3, 3, 5, 5, 4, 4, 4, 2, 4, 5, 5, 3, 4, 1, 4, 2, 2, 5, 1, 2, 5, 3, 4, 1, 4, 4, 2, 3, 5, 1, 5, 2, 2, 1, 5, 4, 1, 5, 2, 3, 4, 1, 3, 5, 3, 5, 4, 1, 2, 3, 3, 4, 3, 4, 3, 4, 3, 2, 3, 1, 1, 3, 3, 4, 2, 3, 4, 5, 2, 4, 5, 3, 5, 1, 4, 1, 3, 5, 3, 4, 3, 2, 1, 4, 5, 5, 1, 4, 4, 2, 4, 4, 2},
            {2, 3, 3, 3, 3, 1, 3, 1, 1, 4, 2, 3, 2, 2, 2, 5, 2, 1, 3, 2, 1, 3, 1, 5, 2, 2, 1, 4, 2, 4, 4, 1, 3, 5, 4, 2, 3, 2, 4, 1, 2, 4, 5, 4, 2, 2, 5, 1, 5, 3, 5, 1, 1, 2, 2, 1, 2, 3, 2, 3, 3, 2, 5, 2, 3, 3, 4, 3, 5, 4, 2, 4, 5, 4, 1, 1, 5, 1, 5, 1, 5, 1, 3, 2, 2, 1, 5, 3, 3, 4, 3, 1, 5, 5, 1, 4, 2, 2, 2, 4},
            {4, 1, 5, 5, 5, 1, 3, 3, 2, 5, 4, 1, 3, 4, 1, 5, 2, 3, 5, 4, 5, 2, 5, 4, 1, 1, 5, 1, 2, 3, 1, 1, 1, 2, 3, 2, 4, 5, 1, 4, 4, 3, 4, 1, 3, 5, 1, 5, 3, 1, 1, 4, 5, 5, 5, 2, 2, 2, 2, 3, 2, 1, 4, 3, 4, 4, 2, 1, 2, 5, 4, 4, 4, 3, 4, 3, 4, 1, 2, 1, 3, 3, 3, 5, 5, 5, 3, 4, 4, 3, 3, 2, 4, 5, 4, 3, 3, 5, 1, 3},
            {2, 3, 4, 3, 2, 3, 4, 1, 1, 4, 5, 4, 4, 3, 1, 1, 1, 1, 2, 4, 3, 2, 3, 1, 3, 3, 5, 3, 3, 5, 3, 1, 4, 3, 3, 1, 2, 5, 3, 4, 2, 1, 3, 3, 1, 4, 5, 1, 4, 2, 3, 4, 5, 4, 1, 1, 5, 4, 3, 1, 5, 5, 2, 5, 2, 2, 2, 2, 3, 2, 1, 1, 3, 5, 4, 4, 1, 1, 1, 2, 2, 1, 3, 3, 3, 4, 4, 3, 5, 4, 5, 4, 3, 1, 1, 2, 1, 1, 3, 3},
            {4, 4, 5, 3, 4, 3, 5, 4, 4, 1, 3, 1, 3, 2, 4, 1, 4, 5, 1, 2, 1, 1, 4, 4, 3, 4, 1, 2, 5, 5, 2, 5, 3, 1, 2, 5, 2, 1, 1, 1, 4, 4, 1, 3, 3, 5, 5, 2, 4, 2, 4, 3, 2, 3, 2, 1, 5, 2, 1, 1, 4, 5, 5, 1, 3, 5, 2, 3, 4, 1, 5, 4, 1, 3, 5, 1, 4, 2, 3, 2, 1, 1, 5, 1, 3, 4, 5, 1, 1, 4, 4, 4, 1, 3, 5, 3, 2, 2, 3, 4},
            {5, 2, 1, 4, 4, 1, 4, 4, 2, 4, 5, 1, 1, 4, 3, 5, 5, 1, 1, 4, 2, 5, 2, 5, 5, 2, 5, 1, 1, 2, 3, 1, 1, 2, 4, 2, 3, 5, 3, 1, 1, 5, 3, 1, 1, 1, 2, 1, 2, 2, 4, 2, 2, 4, 5, 5, 5, 3, 4, 3, 2, 1, 1, 3, 5, 2, 1, 3, 2, 3, 1, 1, 5, 2, 3, 2, 4, 1, 1, 3, 4, 4, 4, 4, 3, 5, 2, 4, 2, 1, 4, 2, 4, 4, 1, 1, 5, 4, 5, 2},
            {1, 5, 2, 1, 3, 4, 3, 3, 2, 3, 4, 2, 5, 3, 2, 5, 2, 1, 2, 1, 3, 4, 1, 3, 1, 2, 3, 3, 2, 3, 5, 1, 5, 4, 2, 5, 5, 1, 3, 2, 4, 3, 3, 2, 5, 5, 5, 5, 3, 4, 3, 2, 3, 1, 2, 5, 2, 1, 1, 5, 3, 5, 1, 4, 1, 2, 1, 1, 3, 2, 2, 1, 4, 4, 5, 5, 5, 5, 3, 1, 1, 5, 5, 1, 5, 4, 3, 1, 5, 5, 2, 4, 3, 3, 4, 4, 2, 1, 2, 3},
            {2, 5, 3, 3, 3, 5, 2, 3, 5, 1, 3, 4, 1, 4, 1, 1, 3, 3, 3, 4, 2, 3, 4, 4, 2, 4, 5, 1, 5, 2, 4, 5, 2, 4, 1, 3, 5, 4, 5, 2, 2, 4, 1, 5, 3, 2, 4, 1, 2, 5, 4, 5, 3, 1, 3, 3, 2, 2, 3, 3, 4, 1, 2, 2, 5, 4, 5, 4, 1, 5, 3, 5, 2, 3, 5, 5, 4, 5, 2, 5, 2, 4, 4, 5, 4, 4, 1, 5, 3, 2, 2, 4, 2, 1, 4, 4, 2, 4, 4, 3},
            {1, 3, 3, 1, 4, 5, 4, 4, 1, 3, 4, 4, 4, 5, 4, 4, 2, 2, 2, 1, 2, 4, 2, 3, 2, 5, 1, 1, 5, 2, 5, 4, 4, 5, 5, 2, 2, 2, 3, 3, 5, 1, 3, 3, 4, 5, 2, 2, 4, 4, 5, 5, 1, 5, 1, 4, 1, 2, 5, 5, 2, 4, 5, 2, 3, 1, 2, 1, 2, 5, 3, 5, 3, 1, 2, 2, 1, 2, 1, 2, 4, 4, 1, 1, 4, 1, 3, 4, 2, 5, 1, 2, 1, 5, 3, 1, 4, 5, 4, 2},
            {3, 4, 4, 1, 1, 4, 3, 1, 5, 2, 4, 2, 5, 1, 3, 5, 2, 1, 5, 2, 5, 1, 1, 1, 3, 5, 4, 3, 4, 5, 2, 1, 2, 2, 3, 5, 3, 5, 2, 4, 5, 1, 4, 3, 5, 5, 3, 2, 1, 1, 3, 1, 5, 3, 1, 2, 5, 3, 1, 1, 4, 1, 4, 2, 2, 1, 4, 3, 4, 5, 2, 1, 5, 1, 1, 1, 1, 5, 4, 2, 1, 3, 5, 3, 4, 2, 1, 1, 5, 4, 4, 5, 1, 5, 2, 1, 5, 5, 5, 5},
            {2, 4, 2, 4, 1, 5, 4, 5, 3, 4, 4, 4, 5, 3, 1, 5, 3, 1, 1, 3, 2, 2, 5, 4, 1, 2, 1, 2, 4, 4, 3, 4, 4, 3, 4, 3, 1, 3, 4, 2, 2, 4, 2, 5, 5, 1, 2, 2, 1, 2, 5, 2, 2, 2, 2, 1, 3, 1, 1, 1, 1, 2, 3, 1, 2, 2, 5, 1, 5, 3, 5, 5, 5, 1, 3, 1, 3, 5, 5, 2, 2, 5, 2, 3, 2, 1, 2, 2, 4, 1, 2, 1, 1, 5, 3, 4, 3, 5, 1, 1},
            {2, 2, 1, 1, 3, 5, 5, 5, 2, 1, 4, 4, 2, 5, 3, 3, 4, 5, 5, 1, 5, 5, 2, 5, 3, 2, 4, 1, 2, 3, 4, 4, 2, 4, 4, 5, 3, 4, 5, 4, 2, 4, 2, 3, 3, 1, 3, 5, 5, 2, 4, 2, 3, 1, 2, 3, 2, 3, 2, 5, 2, 1, 4, 3, 2, 5, 2, 1, 2, 3, 3, 5, 5, 3, 5, 5, 5, 3, 4, 2, 1, 5, 2, 5, 2, 2, 3, 3, 4, 2, 1, 3, 1, 1, 1, 5, 5, 2, 1, 3},
            {3, 5, 2, 3, 1, 3, 4, 2, 3, 3, 1, 5, 2, 5, 4, 5, 5, 2, 2, 5, 5, 1, 1, 3, 3, 5, 5, 5, 2, 3, 4, 2, 3, 5, 4, 2, 4, 5, 1, 2, 1, 1, 4, 3, 5, 3, 2, 3, 3, 3, 1, 2, 3, 1, 1, 1, 4, 2, 5, 5, 1, 5, 4, 5, 3, 1, 5, 3, 1, 2, 3, 3, 2, 5, 5, 2, 4, 2, 3, 3, 3, 5, 4, 4, 4, 1, 4, 2, 2, 4, 2, 4, 4, 3, 4, 2, 1, 3, 2, 4},
            {5, 4, 3, 5, 1, 1, 1, 2, 3, 1, 1, 5, 4, 2, 3, 5, 1, 2, 4, 2, 3, 2, 3, 5, 5, 4, 5, 3, 5, 4, 2, 2, 5, 5, 4, 2, 1, 3, 2, 2, 1, 3, 3, 5, 3, 3, 2, 2, 1, 3, 4, 3, 4, 3, 3, 4, 1, 4, 5, 5, 4, 3, 5, 3, 2, 3, 5, 1, 1, 5, 3, 4, 5, 3, 2, 4, 3, 3, 5, 1, 4, 3, 3, 1, 1, 4, 4, 2, 4, 5, 4, 5, 2, 5, 3, 1, 4, 4, 1, 3},
            {2, 1, 4, 1, 3, 3, 2, 1, 4, 1, 2, 5, 3, 4, 4, 2, 5, 2, 3, 3, 2, 3, 1, 2, 5, 2, 4, 1, 1, 2, 2, 5, 4, 3, 1, 3, 2, 2, 2, 1, 4, 5, 4, 2, 5, 2, 5, 1, 4, 4, 1, 2, 2, 4, 4, 4, 5, 2, 5, 1, 3, 4, 4, 1, 2, 5, 3, 5, 4, 3, 3, 5, 2, 3, 2, 2, 2, 3, 3, 1, 5, 1, 4, 1, 5, 1, 5, 3, 2, 2, 1, 5, 1, 4, 4, 2, 4, 1, 2, 3}
    };

    public static final String TAG = "MainPresenter";
    private IMainView iView;

    private Block[][] baseData;//初始数据集合
    private Block[][] lastData;//上一步数据集合
    private Block[][] nowData;//当前数据集合

    private int level;//关卡
    private int dataSize;//数组的大小
    private int dataNumber;//数据类型数量

    private List<Block> clickedList = new ArrayList<>();//点击后记录颜色块的id的容器
    private int value;//点击的块的value

    public MainPresenter(MainActivity iView) {
        this.iView = iView;
    }

    //初始化数据
    public void initData(int level, int dataSize, int dataNumber) {
        this.dataSize = dataSize;
        this.dataNumber = dataNumber;
        baseData = new Block[dataSize][dataSize];
        lastData = new Block[dataSize][dataSize];
        nowData = new Block[dataSize][dataSize];
        for (int x = 0; x < dataSize; x++) {
            for (int y = 0; y < dataSize; y++) {
                int id = x * dataSize + y;
//                int value = new Random().nextInt(dataNumber) + 1;
                int value = levelData[level][id];
                Block block = new Block(id, x, y, value);
                Block block1 = new Block(id, x, y, value);
                Block block2 = new Block(id, x, y, value);
                baseData[x][y] = block;
                lastData[x][y] = block1;
                nowData[x][y] = block2;
            }
        }

        Log.e(TAG, "dataSize=" + dataSize);

        StringBuilder lala = new StringBuilder();
        for (int i = 0; i < dataSize; i++) {
            Log.e(TAG, "index=" + i);
            for (int j = 0; j < dataSize; j++) {
//                Log.e(TAG, baseData[i][j] + ",");
                lala.append(baseData[i][j].getValue() + ",");
            }
        }
        Log.e(TAG, "initData>>>\r\n" + lala.toString());
        iView.setData(nowData);
    }

    //重置数据
    public void rePlay() {
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < dataSize; j++) {
                nowData[i][j].setValue(baseData[i][j].getValue());
                lastData[i][j].setValue(baseData[i][j].getValue());
            }
        }
        iView.setData(nowData);

//        StringBuilder dataBuilder = new StringBuilder();
//        StringBuilder nowBuilder = new StringBuilder();
//        //显示数据
//        for (int i = 0; i < dataSize; i++) {
//            for (int j = 0; j < dataSize; j++) {
//                dataBuilder.append(baseData[i][j].toString());
//                nowBuilder.append(nowData[i][j].toString());
//            }
//        }
//        Log.e(TAG, "data="+dataBuilder.toString() );
//        Log.e(TAG, "nowData="+nowBuilder.toString() );
    }

    //恢复上一步
    public void lastData() {
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < dataSize; j++) {
                nowData[i][j].setValue(lastData[i][j].getValue());
            }
        }
        iView.setData(nowData);
    }

    //敲击
    public void knock(Block block) {
        //记录上一步
        if (block.getValue() != 0) {
            for (int i = 0; i < dataSize; i++) {
                for (int j = 0; j < dataSize; j++) {
                    lastData[i][j].setValue(nowData[i][j].getValue());
                }
            }
        }

        getIds(block);
        iView.setClickHint(clickedList);
        drawBlack();
        moveBlock();
        if (!isMove()) {//
            iView.cannotMove();
            return;
        }
        getScore();
        iView.setData(nowData);
    }

    //获得分数
    public void getScore() {
        int score = 0;
        int size = clickedList.size();
        if (size > 1 && size <= 3) {
            score = 10 * size;
        } else if (size > 3 && size <= 10) {
            score = 20 * size;
        } else if (size > 10) {
            score = 30 * size;
        }
        iView.setScore(score);
    }

    //是否还有可消除的方块
    public boolean isMove() {

        return true;
    }

    //移动砖块
    private void moveBlock() {

        //移动行
        for (int i = 0; i < clickedList.size(); i++) {
            Block block = clickedList.get(i);
            int x = block.getX();
            int y = block.getY();
            int temp = 0;
            for (int j = y; j >= 0; j--) {
                if (j == 0) {
                    nowData[x][j].setValue(temp);
                    continue;
                }
                if (j == y) {
                    temp = block.getValue();
                }
                int blo = 0;
                if (j - 1 >= 0) {
                    blo = nowData[x][j].getValue();
                    nowData[x][j].setValue(nowData[x][j - 1].getValue());
                    nowData[x][j - 1].setValue(blo);
                }
            }
        }

        // 移动列
        for (int i = 0; i < dataSize; i++) {
            int count = 0;
            for (int j = 0; j < dataSize; j++) {
                int value = nowData[i][j].getValue();
                if (value != 0) {
                    count++;
                }
            }
            if (count == 0) {//整列消除了，移动
                for (int m = i; m < dataSize; m++) {
                    for (int n = 0; n < dataSize; n++) {
                        if (m + 1 < dataSize) {
                            nowData[m][n].setValue(nowData[m + 1][n].getValue());
                        } else {
                            nowData[m][n].setValue(0);
                        }

                    }
                }
            }
        }


    }

    //将ids中的black弄黑
    private void drawBlack() {
        //
        for (int i = 0; i < clickedList.size(); i++) {
            Block block = clickedList.get(i);
            nowData[block.getX()][block.getY()].setValue(0);
        }
    }

    //获取周围相同value的集合
    private void getIds(Block block) {
        clickedList.clear();
        value = block.getValue();
        digui(block);//找到颜色块
        //排序
        for (int i = 0; i < clickedList.size(); i++) {
            for (int j = 0; j < clickedList.size() - i - 1; j++) {
                Block blo_a = clickedList.get(j);
                Block blo_b = clickedList.get(j + 1);
                Block temp = null;
                if (blo_a.getY() > blo_b.getY()) {
                    temp = blo_a;
                    clickedList.set(j, clickedList.get(j + 1));
                    clickedList.set(j + 1, temp);
                }
            }
        }
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
        if (rightBlock != null) {
            isR = shoudAdd(rightBlock);
            if (isR) {
                clickedList.add(rightBlock);
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
//                Log.e("添加么？", "this.value=" + value + ",block=" + block);
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
