package com.small.game.whodrunk;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by smallstrong on 16/6/3.
 */
public class WineGlass extends View {
    private int num;
    /**
     * 圆的直径
     */
    private int mRadius;
    /**
     * 控件的padding，这里我们认为4个padding的值一致，以paddingleft为标准
     */
    private int mPadding;
    /**
     * 控件的中心位置
     */
    private int mCenter;
    private Context context;
    private RectF mRange;


    /**
     * 与文字对应的图片
     */
    private ArrayList<Bitmap> pics = new ArrayList<>();


    public WineGlass(Context context) {
        this(context, null);
    }

    public WineGlass(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public WineGlass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Drunk, defStyleAttr, 0);
        num = typedArray.getInt(R.styleable.Drunk_count, 8);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
        // 获取圆形的直径
        mRadius = width - getPaddingLeft() - getPaddingRight();
        // padding值
        mPadding = getPaddingLeft();
        // 中心点
        mCenter = width / 2;
        // 圆弧的绘制范围
        mRange = new RectF(getPaddingLeft(), getPaddingLeft(), mRadius
                + getPaddingLeft(), mRadius + getPaddingLeft());
        initData();
        setMeasuredDimension(width, width);
    }

    private void initData() {


    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float tmpAngle = 0;
        float sweepAngle = (float) (360 / num);
        Log.i("cdcdscsdcsdc","wocao");
        pics.clear();
        for(int j = 0 ;j < num;j++){
            pics.add(BitmapFactory.decodeResource(getResources(),R.drawable.game_glass));
        }

        for (int i = 0; i < num; i++) {
            // 绘制Icon
            drawIcon(canvas, tmpAngle, i);
            tmpAngle += sweepAngle;
        }
        super.onDraw(canvas);
    }

    private void drawIcon(Canvas canvas, float tmpAngle, int i) {


        // 设置图片的宽度为直径的1/8
        int imgWidth = mRadius / 5;

        float angle = (float) ((tmpAngle) * (Math.PI / 180));

        int x = (int) (mCenter + mRadius / 2.5 * Math.cos(angle));
        int y = (int) (mCenter + mRadius / 2.5 * Math.sin(angle));

        // 确定绘制图片的位置
        Rect rect = new Rect(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth
                / 2, y + imgWidth / 2);


        Log.i("从第三层的市场",pics.size()+"");
        canvas.drawBitmap(pics.get(i), null, rect, null);
    }
    public void setCount(int count){
        num = count;
        invalidate();

    }
}
