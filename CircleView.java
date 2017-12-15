package com.tencent.editpackageinfo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zachzeng on 2017/12/14.
 */

public class CircleView extends View{
    private int radius = 0;
    private int color;
    private int borderWidth = 0;
    private int borderColor;
    private int textSize;
    private int textColor;
    private String textContent = "";

    private int defaultPadding = 0;
    private Paint mPaint = null;

    public CircleView(Context context){
        super(context);
        init(context,null,0);
    }

    public CircleView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context,attrs,0);
    }

    public CircleView(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        init(context,attrs,defStyle);
    }

    private void init(Context context,AttributeSet attrs,int defStyle){
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CircleView,defStyle,0);
        int n = a.getIndexCount();
        for(int i = 0;i<n;i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.CircleView_radius:
                    radius = (int)a.getDimension(attr, 0);
                    break;
                case R.styleable.CircleView_color:
                    color = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CircleView_border_width:
                    borderWidth = (int)a.getDimension(attr,0);
                    break;
                case R.styleable.CircleView_border_color:
                    borderColor = a.getColor(attr,Color.WHITE);
                    break;
                case R.styleable.CircleView_text_size:
                    textSize = (int)a.getDimension(attr,0);
                    break;
                case R.styleable.CircleView_text_color:
                    textColor = a.getColor(attr,Color.WHITE);
                    break;
                case R.styleable.CircleView_text_content:
                    textContent = a.getString(attr);
                    break;
            }
        }

        a.recycle();

        //设置默认值
        if(radius == 0){
            radius = ViewTils.dip2px(context,10);
        }

        defaultPadding = ViewTils.dip2px(context,1);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int circleX = getWidth()/2;
        int circleY = getHeight()/2;

        //绘制边界
        if(borderWidth != 0){
            mPaint.setColor(borderColor);
            canvas.drawCircle(circleX,circleY,getWidth()/2,mPaint);
        }

        //绘制圆
        mPaint.setColor(color);
        canvas.drawCircle(circleX,circleY,getWidth()/2-borderWidth,mPaint);

        //设置了文字
        if(!TextUtils.isEmpty(textContent)){
            int stringRadius =  textSize*textContent.length()/2 + defaultPadding;



            //绘制文字
            mPaint.setTextSize(textSize);
            mPaint.setColor(textColor);
            float textWidth = mPaint.measureText(textContent);
            float x = (getWidth() - textWidth)/2;   //文字x坐标
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float y = getHeight()/2 + (Math.abs(fontMetrics.ascent)-fontMetrics.descent)/2;
            canvas.drawText(textContent,x,y,mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;

        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else{
            //设置了文字，则按文字计算宽度
            if(!TextUtils.isEmpty(textContent)){
                width = textSize*textContent.length() + 2*(defaultPadding+borderWidth)
                     + getPaddingLeft() + getPaddingRight();
            }else{
                width = 2*(radius + borderWidth) + getPaddingLeft() + getPaddingRight();
            }
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else{
            height = width;
        }

        setMeasuredDimension(width, height);
    }
}
