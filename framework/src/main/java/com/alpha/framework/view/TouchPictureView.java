package com.alpha.framework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.alpha.framework.R;

public class TouchPictureView extends View {

    //背景
    private Bitmap mBgBitmap;
    //背景画笔
    private Paint mPaintBg;

    //空白
    private Bitmap mNullBitmap;
    //空白快画笔
    private Paint mPaintNull;

    //移动方块
    private Bitmap mMoveBitMap;
    //移动画笔
    private Paint mPaintMove;

    //View的宽高
    private int mWidth;
    private int mHeight;

    //方块大小
    private int mCARD_SIZE = 200;
    //方块坐标
    private int LINE_W, LINE_H = 0;
    //移动方块横坐标
    private int moveX = 200;


    public TouchPictureView(Context context) {
        super(context);
        init();
    }

    public TouchPictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchPictureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintBg = new Paint();
        mPaintNull = new Paint();
        mPaintMove = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawNullCard(canvas);
        drawMoveCard(canvas);
    }

    private void drawMoveCard(Canvas canvas) {
        //截取空白块位置坐标的Bitmap图像
        mMoveBitMap = Bitmap.createBitmap(mBgBitmap, LINE_W, LINE_H, mCARD_SIZE, mCARD_SIZE);
        canvas.drawBitmap(mMoveBitMap, moveX, LINE_H, mPaintMove);
    }

    /**
     * 绘制空白块
     *
     * @param canvas
     */
    private void drawNullCard(Canvas canvas) {
        //1.获取图片
        mNullBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_null_card);
        //2.计算值
        mCARD_SIZE = mNullBitmap.getWidth();

        //99 / 3 = 33 * 2 = 66
        LINE_W = mWidth / 3 * 2;
        //除以2并不是中心
        LINE_H = mHeight / 2 - (mCARD_SIZE / 2);
        canvas.drawBitmap(mNullBitmap, LINE_W, LINE_H, mPaintNull);


    }

    /**
     * 绘制背景
     *
     * @param canvas
     */
    private void drawBg(Canvas canvas) {
        //1.获取图片
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_bg);
        //2.创建一个空的Bitmap Bitmap w h = View w h
        mBgBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        //3.将图片绘制到空的Bitmap
        Canvas bgCanvas = new Canvas(mBgBitmap);
        bgCanvas.drawBitmap(mBitmap, null, new Rect(0, 0, mWidth, mHeight), mPaintBg);
        //4.将mBgBitmap绘制到View上
        canvas.drawBitmap(mBgBitmap, null, new Rect(0, 0, mWidth, mHeight), mPaintBg);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

        }
        return true;
    }
}
