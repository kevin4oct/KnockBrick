package com.geren.kevin.knockbricks.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.geren.kevin.knockbricks.main.bean.Block;

/**
 * 砖块
 */
public class Brick extends View {

    public static final String TAG = Brick.class.getSimpleName();

    private Block block;

    private int[] colors = {Color.BLACK, Color.GREEN, Color.YELLOW, Color.BLUE, Color.RED, Color.MAGENTA};

    private Paint mPaint;

    public Brick(Context context) {
        this(context, null);
    }

    public Brick(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Brick(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mPaint = new Paint();
    }

    public void initBrick(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.e(TAG, "我是卡片,我获取到的数据是>>>" + block.toString());
        canvas.drawColor(colors[block.getValue()]);
    }


}
