package com.example.myseekbarlibrary.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.myseekbarlibrary.progressbar.ColorProgressBar;

/***
 * @author ljc
 * 实现一个7彩的进度条
 */
public class ColorSeekBar extends ColorProgressBar {

    public ColorSeekBar(Context context) {
        super(context);
    }

    public ColorSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void drawCircle(Canvas canvas) {
        int mCenterX = proEnd;
        int mCenterY = mTop;
        mBgPaint.setColor(mCircleColor);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mBgPaint);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mProgress = (getTouchX(event.getX()) - mStart) / (mEnd - mStart);
                mRadius *= 1.5;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                mProgress = (getTouchX(event.getX()) - mStart) / (mEnd - mStart);
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

    /**
     * 将x轴点击坐标与两端对比
     *
     * @param x x轴坐标
     * @return 比较后数值
     */
    public float getTouchX(float x) {
        if (x < mStart) {
            return mStart;
        } else if (x > mEnd) {
            return mEnd;
        } else {
            return x;
        }
    }


}
