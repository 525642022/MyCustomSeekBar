package com.example.myseekbarlibrary.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.myseekbarlibrary.progressbar.CircleProgressBar;

/***
 * @author ljc
 * 实现一个7彩的进度条
 */
public class CircleSeekBar extends CircleProgressBar {

    public CircleSeekBar(Context context) {
        super(context);
    }

    public CircleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化高和宽
        initWidthAndHeight(w, h);
    }

    @Override
    public void drawCircle(Canvas canvas) {
        int centerX = (int) (mCenterX + (mMinRadio + mRadius / 2) * Math.cos(mSweepAngle * Math.PI / 180));
        int centerY = (int) (mCenterY + (mMinRadio + mRadius / 2) * Math.sin(mSweepAngle * Math.PI / 180));
        mBgPaint.setColor(mCircleColor);
        canvas.drawCircle(centerX, centerY, mRadius, mBgPaint);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mProgress = getProgress(event) / 360;
                mRadius *= 1.5;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                mProgress = getProgress(event) / 360;
                if (progressChange != null) {
                    progressChange.onProgressChange(mProgress);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getRootView().performClick();
                mRadius /= 1.5;
                invalidate();
                break;
        }
        return true;
    }

    /***
     * 计算移动角度
     */
    private float getProgress(MotionEvent event) {
        float x = event.getX() - mCenterX;
        float y = event.getY() - mCenterY;
        if (x > 0 && y > 0) {
            return (float) Math.toDegrees(Math.atan(y / x));
        } else if (x < 0 && y > 0) {
            return 180 + (float) Math.toDegrees(Math.atan(y / x));
        } else if (x < 0 && y < 0) {
            return 180 + (float) Math.toDegrees(Math.atan(y / x));
        } else {
            return 360 + (float) Math.toDegrees(Math.atan(y / x));
        }
    }

}
