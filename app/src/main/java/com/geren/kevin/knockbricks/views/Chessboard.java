package com.geren.kevin.knockbricks.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.geren.kevin.knockbricks.main.bean.Block;

public class Chessboard extends View {

    public static final String TAG = Chessboard.class.getSimpleName();

    //画笔
    private Paint paint;
    //数据
    private Block[][] data;
    //尺寸
    private int size = 1;
    private int row = 1;
    private int paintWith;

    public Chessboard(Context context) {
        this(context, null);
    }

    public Chessboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Chessboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        data = new Block[100][100];
        paint = new Paint();
        paintWith = 10;
        paint.setStrokeWidth(paintWith);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeSpec = widthMeasureSpec > heightMeasureSpec ? heightMeasureSpec : widthMeasureSpec;
        super.onMeasure(sizeSpec, sizeSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    //设置数据
    public void setData(Block[][] data, int row) {

        int width = getWidth();
        int height = getHeight();
        size = width > height ? height : width;

        this.row = row;
        this.data = data;
        //重绘
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //
//        row = 10;
        canvas.drawColor(Color.GREEN);
        //
        int boxWidth = (size - (paintWith * (size + 1))) / (row);

        Log.e(TAG, "size=" + size + ",row=" + row + ",boxWidth =" + boxWidth);

        //画网格
        paint.setColor(Color.GRAY);
        for (int i = 0; i < row; i++) {//画横线
            canvas.drawLine(0, -boxWidth * i * 2, size, -boxWidth * i, paint);
        }
        for (int i = 0; i < row; i++) {//画竖线
            canvas.drawLine(-boxWidth * i * 2, 0, -boxWidth * i, size, paint);
        }


    }


}
