package com.example.myseekbarlibrary.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.myseekbarlibrary.R;

import static com.example.myseekbarlibrary.utils.CustomSeekBarUtils.dp2px;

public abstract class BaseBar extends View {
    //画背景的Paint
    public Paint mBgPaint;
    //画进度条的Paint
    public Paint mProgressPaint;
    //进度条高度
    public int mHeight ;
    //开始位置
    public int mStart;
    //结束位置
    public int mEnd;
    //渐变颜色 三个
    public int mStartColor;
    public int mEndColor;
    public int mCenterColor;
    //背景颜色
    public int mBgColor;
    //拖动小圆点颜色
    public int mCircleColor;
    //当前进度
    public float mProgress;
    //小圆点半径
    public int mRadius;
    //文字颜色
    public int mTextColor;
    //文字大小
    public float mTextSize;
    //显示文字的Rect
    public Rect mTxtRec;
    //最里面白色圆的半径
    public int mMinRadio;
    //线中心距离上方距离
    public int mTop;
    //文字距离线的距离
    public int mTextTop;
    //进度条改变回调
    public ProgressChange progressChange;

    public BaseBar(Context context) {
        this(context, null);
    }

    public BaseBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context,attrs,defStyleAttr);
        initPaint();
    }
    private void initPaint() {
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setTextSize(mTextSize);
        mBgPaint.setStrokeCap(Paint.Cap.ROUND);
        mBgPaint.setStrokeWidth((float) mHeight);
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressPaint.setStrokeWidth((float) mHeight);
    }


    /***
     * 初始化公共参数
     */
    public void initParams(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray seekArray = context.obtainStyledAttributes(attrs, R.styleable.BaseBar, defStyleAttr, 0);
        mStartColor = seekArray.getColor(R.styleable.BaseBar_startColor, ContextCompat.getColor(context, R.color.startColor));
        mCenterColor = seekArray.getColor(R.styleable.BaseBar_centerColor, ContextCompat.getColor(context, R.color.centerColor));
        mEndColor = seekArray.getColor(R.styleable.BaseBar_endColor, ContextCompat.getColor(context, R.color.endColor));
        mBgColor = seekArray.getColor(R.styleable.BaseBar_bgColor, ContextCompat.getColor(context, R.color.bgColor));
        mTextColor = seekArray.getColor(R.styleable.BaseBar_textColor, ContextCompat.getColor(context, R.color.bgColor));
        mCircleColor = seekArray.getColor(R.styleable.BaseBar_circleColor, ContextCompat.getColor(context, R.color.circleColor));
        mTextSize = seekArray.getDimension(R.styleable.BaseBar_textSize, dp2px(15));
        mHeight = (int)seekArray.getDimension(R.styleable.BaseBar_height,dp2px(10));
        mRadius = (int)seekArray.getDimension(R.styleable.BaseBar_radius,dp2px(8));
        mMinRadio = (int)seekArray.getDimension(R.styleable.BaseBar_minRadio,dp2px(40));
        mTop = (int)seekArray.getDimension(R.styleable.BaseBar_top,dp2px(15));
        mTextTop = (int)seekArray.getDimension(R.styleable.BaseBar_textTop,dp2px(8));
        mTxtRec = new Rect();
        seekArray.recycle();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化高和宽
        initWidthAndHeight(w,h);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawMyText(canvas);
        drawProgress(canvas);
        drawCircle(canvas);
    }

    /**
     * 根据初始的高度和宽度设置一些信息
     */
    protected abstract void initWidthAndHeight(int w, int h);
    /**
     * 绘制进度条背景
     */
    protected abstract void drawBg(Canvas canvas);

    /**
     * 绘制显示百分比的文字
     */
    protected abstract void drawMyText(Canvas canvas);

    /**
     * 绘制进度条
     */
    protected abstract void drawProgress(Canvas canvas);

    /**
     * 绘制可以拖动的按钮
     */
    protected abstract void drawCircle(Canvas canvas);



    public interface ProgressChange {
        void onProgressChange(float progress);
    }

    public void setProgress(float mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }

    public void setProgressChange(ProgressChange progressChange) {
        this.progressChange = progressChange;
    }
}
