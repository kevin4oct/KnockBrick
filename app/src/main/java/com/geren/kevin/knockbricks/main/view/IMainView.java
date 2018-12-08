package com.geren.kevin.knockbricks.main.view;

import com.geren.kevin.knockbricks.main.bean.Block;

import java.util.List;

public interface IMainView {

    //重置
    void setData(Block[][] data);

    //设置点击提示
    void setClickHint(List<Block> list);

    //
    void setScore(int score);

    //不能移动
    void cannotMove();

    //

}
