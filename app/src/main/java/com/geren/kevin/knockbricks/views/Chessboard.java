package com.geren.kevin.knockbricks.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.geren.kevin.knockbricks.main.bean.Block;
import com.geren.kevin.knockbricks.utils.TransformUtils;

/**
 * 棋盘
 */
public class Chessboard extends ViewGroup implements View.OnClickListener {

    public static final String TAG = "Chessboard";

    private ChessBoardClickListener listener;

    private Context context;
    private Block[][] data;
    private int rowNumber;
    private int rowWith;
    private int boxWidth;

    private Paint mPaint;

    public Chessboard(Context context) {
        this(context, null);
    }

    public Chessboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public Chessboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);//调用ondraw方法
        this.rowNumber = 1;
        data = new Block[rowNumber][rowNumber];
        mPaint = new Paint();
    }

    //设置数据，重绘
    public void setData(int rowNumber, Block[][] blocks) {
        removeAllViews();
        Log.e(TAG, "加载数据");
        this.rowNumber = rowNumber;
        this.data = blocks;
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < rowNumber; j++) {
                Brick brick = new Brick(context);
                brick.initBrick(data[i][j]);
                addView(brick);
            }
        }
        this.invalidate();
    }

    /**
     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int rowWith = widthMeasureSpec > heightMeasureSpec ? heightMeasureSpec : widthMeasureSpec;
        super.onMeasure(rowWith, rowWith);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.rowWith = sizeWidth > sizeHeight ? sizeHeight : sizeWidth;
        this.boxWidth = ((Float) ((rowWith - ((float) TransformUtils.px2dip(context, mPaint.getStrokeWidth()))) / rowNumber)).intValue();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        if (childCount > 0) {
            Log.e(TAG, "childCount= " + childCount);
            for (int i = 0; i < rowNumber; i++) {//行
                for (int j = 0; j < rowNumber; j++) {//列
                    int index = rowNumber * j + i;
                    Log.i(TAG, "rowNumber=" + rowNumber + ",i=" + i + ",j=" + j + ",我是卡片，我的下标为》》》" + index);
                    Brick brick = (Brick) getChildAt(index);
//                    brick.setLeft(108 * j);
//                    brick.setTop(108 * i);
//                    brick.setRight(108 * (j + 1));
//                    brick.setBottom(108 * (i + 1));
                    //
                    brick.setTop(108 * i);
                    brick.setBottom(108 * (i + 1));
                    brick.setLeft(108 * j);
                    brick.setRight(108 * (j + 1));

                    brick.setOnClickListener(this);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(1);
////        Log.e(TAG, "boxWidth=" + boxWidth);
//        //画网格
//        for (int i = 0; i <= rowNumber; i++) {//横线
////            Log.e(TAG, "boxWidth=" + boxWidth + ",i=" + i + ",boxWidth * i=" + boxWidth * i);
//            canvas.drawLine(0, boxWidth * i, rowWith, boxWidth * i, mPaint);
//        }
//        for (int j = 0; j <= rowNumber; j++) {//竖线
//            canvas.drawLine(boxWidth * j, 0, boxWidth * j, rowWith, mPaint);
//        }
    }

    @Override
    public void onClick(View v) {
        Brick brick = (Brick) v;
        Block block = brick.getBlock();
        if (listener != null) {
            listener.chessboardClicked(block);
        }
    }

    public void setOnChessBoardClickListener(ChessBoardClickListener listener) {
        this.listener = listener;
    }

    public interface ChessBoardClickListener {
        void chessboardClicked(Block block);
    }
}
