package com.example.myseekbarlibrary.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.example.myseekbarlibrary.base.BaseBar;

import static com.example.myseekbarlibrary.utils.CustomSeekBarUtils.dp2px;

/***
 * @author ljc
 * 实现一个7彩的进度条
 */
public class ColorProgressBar extends BaseBar {

    public int proEnd;

    private LinearGradient mLinearGradient;

    public ColorProgressBar(Context context) {
        super(context);
    }

    public ColorProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void initWidthAndHeight(int w, int h) {
        mStart = getPaddingLeft();
        mEnd = getMeasuredWidth() - getPaddingRight();
        mLinearGradient = new LinearGradient(0, 0, w, 0
                , new int[]{mStartColor, mCenterColor, mEndColor}
                , new float[]{0, .5f, 1F}
                , Shader.TileMode.CLAMP);
    }


    @Override
    public void drawCircle(Canvas canvas) {

    }

    @Override
    public void drawBg(Canvas canvas) {
        mBgPaint.setColor(mBgColor);
        canvas.drawLine(mStart, mTop, mEnd, mTop, mBgPaint);
    }

    @Override
    public void drawProgress(Canvas canvas) {
        mProgressPaint.setShader(mLinearGradient);
        proEnd = (int) ((mProgress) * (mEnd - mStart)) + mStart;
        canvas.drawLine(mStart, mTop, proEnd, mTop, mProgressPaint);
    }

    @Override
    public void drawMyText(Canvas canvas) {
        String text = (int) (mProgress * 100) + "%";
        mBgPaint.getTextBounds(text, 0, text.length(), mTxtRec);
        mBgPaint.setColor(mTextColor);
        canvas.drawText(text, (float) (mStart + mEnd - mTxtRec.width()) / 2, (float) (mTop + mHeight / 2 + mTxtRec.height() + mTextTop), mBgPaint);
    }


}
