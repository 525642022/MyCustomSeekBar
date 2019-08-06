package com.example.myseekbarlibrary.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;

import com.example.myseekbarlibrary.base.BaseBar;

/***
 * @author ljc
 * 实现一个7彩的进度条
 */
public class CircleProgressBar extends BaseBar {
    public SweepGradient mSweepGradient;
    public int mCenterX;
    public int mCenterY;
    public RectF mRectF;
    public float mSweepAngle;

    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化高和宽
        initWidthAndHeight(w, h);
    }

    @Override
    public void initWidthAndHeight(int w, int h) {
        mStart = getPaddingLeft();
        mEnd = getMeasuredWidth() - getPaddingRight();
        mCenterX = mStart + w / 2;
        mCenterY = h / 2;
        //画矩形
        mRectF = new RectF(mCenterX - mMinRadio - mHeight / 2, mCenterY - mMinRadio - mHeight / 2, mCenterX + mMinRadio + mHeight / 2, mCenterY + mMinRadio + mHeight / 2);
        //初始化渐变圆环
        mSweepGradient = new SweepGradient(mCenterX, mCenterY, new int[]{mStartColor, mCenterColor, mEndColor, mCenterColor, mStartColor}, null);
    }


    @Override
    public void drawBg(Canvas canvas) {
        mBgPaint.setColor(mBgColor);
        mBgPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(mRectF, 360, 360, false, mBgPaint);
    }

    @Override
    public void drawCircle(Canvas canvas) {

    }

    @Override
    public void drawProgress(Canvas canvas) {
        mProgressPaint.setShader(mSweepGradient);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mSweepAngle = mProgress * 360;
        canvas.drawArc(mRectF, 360, mSweepAngle, false, mProgressPaint);
    }

    @Override
    public void drawMyText(Canvas canvas) {
        mBgPaint.setStyle(Paint.Style.FILL);
        String text = (int) (mProgress * 100) + "%";
        mBgPaint.getTextBounds(text, 0, text.length(), mTxtRec);
        mBgPaint.setColor(mTextColor);
        canvas.drawText(text, (float) (mCenterX - mTxtRec.width() / 2), (float) (mCenterY + mTxtRec.height() / 2), mBgPaint);
    }


    public void setProgress(float mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }


}
