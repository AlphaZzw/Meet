package com.alpha.framework.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.alpha.framework.R;


/**
 * FileName: PictureV
 * Founder: LiuGuiLin
 * Create Date: 2019/8/3 13:32
 * Email: lgl@szokl.com.cn
 * Profile: Android 拖动滑块完成验证的View实现
 */
public class PictureV extends View {

    //计时器
    private static final int MOVE_CLOCK = 1000;

    //成功与否
    private boolean isSucceed = false;
    //操作时间
    private int moveTime = 0;

    //背景
    private Bitmap bgBitmap;
    //背景画笔
    private Paint mPaintBg;
    //空方块
    private Bitmap nullBitmap;
    //空方块画笔
    private Paint mPaintNull;
    //移动方块
    private Bitmap moveBitmap;
    //移动方块画笔
    private Paint mPaintMove;
    //成功文字的画笔
    private Paint mPaintText;
    //成功文字背景的画笔
    private Paint mPaintTextBg;
    //成功区域的高度
    private int SUCCEED_CARD_H = 100;
    //文字左间距
    private int TEXT_LEFT_SIZE = 50;
    //文字的大小
    private int TEXT_SIZE = 50;
    //文字的颜色
    private int TEXT_COLOR = Color.WHITE;
    //文字的背景
    private int TEXT_BG_COLOR;
    //文字提示
    private String textTips = "";

    //可变化的移动X轴
    private int moveX = 100;
    //允许的误差值
    private int errorValue = 10;

    //方块大小
    private int CARD_SIZE = 200;
    //控件宽高
    private int mWidth = 0;
    private int mHeight = 0;

    //固定水平线
    private int LINE_W = 0;
    private int LINE_H = 0;

    private OnPictureVSucceed mPictureVSucceed;

    public void setPictureVSucceed(OnPictureVSucceed mPictureVSucceed) {
        this.mPictureVSucceed = mPictureVSucceed;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case MOVE_CLOCK:
                    moveTime++;
                    mHandler.sendEmptyMessageDelayed(MOVE_CLOCK, 1000);
                    break;
            }
            return false;
        }
    });

    public PictureV(Context context) {
        super(context);
        init();
    }

    public PictureV(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PictureV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        TEXT_BG_COLOR = getResources().getColor(R.color.color_tips_bg);
        textTips = getResources().getString(R.string.text_succeed_tips);

        mPaintBg = new Paint();
        mPaintNull = new Paint();
        mPaintMove = new Paint();
        mPaintText = new Paint();
        mPaintTextBg = new Paint();

        mPaintText.setTextSize(TEXT_SIZE);
        mPaintText.setColor(TEXT_COLOR);

        //设置填充
        mPaintTextBg.setStyle(Paint.Style.FILL);
        mPaintTextBg.setColor(TEXT_BG_COLOR);
        mPaintTextBg.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBg(canvas);
        drawNullCard(canvas);
        drawMoveCard(canvas);

        if (isSucceed) {
            drawSucceedText(textTips + moveTime + "s", canvas);
        }
        if (null != mPictureVSucceed) {
            mPictureVSucceed.outValue(moveX, mWidth - CARD_SIZE);
        }
    }

    //绘制成功文字
    private void drawSucceedText(String text, Canvas canvas) {
        RectF rectF = new RectF(0, mHeight - SUCCEED_CARD_H, mWidth, mHeight);
        canvas.drawRect(rectF, mPaintTextBg);

        Paint.FontMetrics fontMetrics = mPaintText.getFontMetrics();
        // 计算文字高度
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        // 计算文字baseline
        float textBaseY = SUCCEED_CARD_H - (SUCCEED_CARD_H - fontHeight) / 2 - fontMetrics.bottom;

        canvas.drawText(text, TEXT_LEFT_SIZE, mHeight - SUCCEED_CARD_H + textBaseY, mPaintText);
        isSucceed = false;
    }

    //绘制移动方块
    private void drawMoveCard(Canvas canvas) {
        if (moveBitmap == null) {
            moveBitmap = Bitmap.createBitmap(bgBitmap, LINE_W, LINE_H, CARD_SIZE, CARD_SIZE);
        }
        canvas.drawBitmap(moveBitmap, moveX, LINE_H, mPaintMove);
    }

    //绘制空的方块
    private void drawNullCard(Canvas canvas) {
        if (nullBitmap == null) {
            nullBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_null_card);
            //计算值
            CARD_SIZE = nullBitmap.getWidth();
            LINE_W = mWidth / 3 * 2;
            //居中
            LINE_H = mHeight / 2 - (CARD_SIZE / 2);
        }
        canvas.drawBitmap(nullBitmap, LINE_W, LINE_H, mPaintNull);
    }

    // 绘制背景图
    private void drawBg(Canvas canvas) {
        Bitmap mBitmap = null;
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_bg2);
        }
        if (bgBitmap == null) {
            bgBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        }
        Canvas bgCanvas = new Canvas(bgBitmap);
        bgCanvas.drawBitmap(mBitmap, null, new Rect(0, 0, mWidth, mHeight), mPaintBg);
        canvas.drawBitmap(bgBitmap, null, new Rect(0, 0, mWidth, mHeight), mPaintBg);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    //更新移动块
    public void updateX(int value) {
        if (value > 0 && value < (mWidth - CARD_SIZE)) {
            moveX = value;
            invalidate();
            //计算成功结果
            if (moveX > (LINE_W - errorValue) && moveX < (LINE_W + errorValue)) {
                //验证成功
                if (null != mPictureVSucceed) {
                    mPictureVSucceed.onSucceed();
                }
                isSucceed = true;
                invalidate();
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下的时候开始计时
                mHandler.sendEmptyMessage(MOVE_CLOCK);
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getX() > 0 && event.getX() < (mWidth - CARD_SIZE)) {
                    moveX = (int) event.getX();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                /**
                 * 在抬起的时候做验证
                 * moveX = LINE_W
                 * 允许一点小误差
                 * 不管有没有移动成功，都重新计时
                 */
                mHandler.removeMessages(MOVE_CLOCK);
                if (moveX > (LINE_W - errorValue) && moveX < (LINE_W + errorValue)) {
                    //验证成功
                    if (null != mPictureVSucceed) {
                        mPictureVSucceed.onSucceed();
                    }
                    isSucceed = true;
                    invalidate();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public interface OnPictureVSucceed {
        //外部调用
        void outValue(int pos, int maxValue);

        //成功通知
        void onSucceed();
    }
}

