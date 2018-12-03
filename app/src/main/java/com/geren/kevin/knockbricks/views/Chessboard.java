package com.geren.kevin.knockbricks.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.geren.kevin.knockbricks.main.bean.Block;
import com.geren.kevin.knockbricks.utils.TransformUtils;

public class Chessboard extends View {

    public static final String TAG = Chessboard.class.getSimpleName();

    private ClickListener clickListener;

    //画笔
    private Paint paint;
    //数据
    private Block[][] data;
    //尺寸
    private int size = 1;
    private int row = 1;
    private int paintWith;
    private Context context;

    public Chessboard(Context context) {
        this(context, null);
    }

    public Chessboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Chessboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        data = new Block[100][100];
        paint = new Paint();
        paint.setAntiAlias(true);
        paintWith = 1;
        paint.setStrokeWidth(paintWith);
    }

    public void setOnBoardClickListener(ClickListener listener) {
        this.clickListener = listener;
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
        this.size = width > height ? height : width;
        this.row = row;
        this.data = data;
        //重绘
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //
        canvas.drawColor(Color.GREEN);
        //
        int paintWith2dip = TransformUtils.px2dip(context, paintWith);
        int boxWidth = (size - (paintWith2dip * (row))) / (row);

        //画网格
        paint.setColor(Color.GRAY);
        for (int i = 0; i <= row; i++) {//画横线
            canvas.drawLine(paintWith2dip * 2, boxWidth * i, size, boxWidth * i, paint);
        }
        for (int i = 0; i <= row; i++) {//画竖线
            canvas.drawLine(boxWidth * i, paintWith2dip * 2, boxWidth * i, size, paint);
        }

        //画棋子图片
        for (int i = 0; i < data.length; i++) {
            Block[] datum = data[i];
            for (int j = 0; j < datum.length; j++) {
                Block block = datum[j];
                int value = 0;
                try {
                    value = block.getValue();
                } catch (Exception e) {
                    value = 1;
                }
                switch (value) {
                    case 1:
                        paint.setColor(Color.BLUE);
                        break;
                    case 2:
                        paint.setColor(Color.YELLOW);
                        break;
                    case 3:
                        paint.setColor(Color.RED);
                        break;
                }
                int x = boxWidth * i + boxWidth / 2;
                int y = boxWidth * j + boxWidth / 2;
                paint.setTextSize(50);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTypeface(Typeface.DEFAULT_BOLD);
                canvas.drawText("" + value, x, y, paint);

            }
        }
    }

    public interface ClickListener {
        void chessClicked(Block block);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        Log.e(TAG, "rawX: " + rawX + ",rawY:" + rawY);

//        rawX/

        return true;
    }
}
