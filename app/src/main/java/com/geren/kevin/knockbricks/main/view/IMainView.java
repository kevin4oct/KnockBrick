package com.geren.kevin.knockbricks.main.view;

import com.geren.kevin.knockbricks.main.bean.Block;

import java.util.List;

public interface IMainView {

    //重置
    void setData(Block[][] data);

    void setClickHint(List<Block> list);

}
