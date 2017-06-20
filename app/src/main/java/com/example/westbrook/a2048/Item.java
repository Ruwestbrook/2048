package com.example.westbrook.a2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

/**
 * Created by westbrook on 2017/6/16.
 */

public class Item extends View {
    private static final String TAG = "MyFrameLayout";
    //数字
    private int number;
    //显示的字体
    private String textNumber;
    //画笔
    private Paint mPaint;
    //绘制文字的区域
    private Rect bound;
    public Item(Context context) {
        this(context,null);
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        textNumber=number+"";
        bound=new Rect();
        mPaint.getTextBounds(textNumber,0,textNumber.length(),bound);
    }
    public Item(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor(getColor(number)));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        if(number!=0)
            DrawText(canvas);
    }

    public Item(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
    }
    //绘制文字
    void DrawText(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);
        Log.d(TAG, "DrawText: "+getWidth()+"  "+getHeight()+"  "+
                bound.width()+"  "+bound.height());
        float x = (getWidth() - bound.width()) / 2;
        float y = getHeight() / 2 + bound.height() / 2;
        canvas.drawText(textNumber,x,y,mPaint);
    }
   String getColor(int number){
       String mBgColor = "";
       switch (number)
       {
           case 0:
               mBgColor = "#CCC0B3";
               break;
           case 2:
               mBgColor = "#EEE4DA";
               break;
           case 4:
               mBgColor = "#EDE0C8";
               break;
           case 8:
               mBgColor = "#F2B179";// #F2B179
               break;
           case 16:
               mBgColor = "#F49563";
               break;
           case 32:
               mBgColor = "#F5794D";
               break;
           case 64:
               mBgColor = "#F55D37";
               break;
           case 128:
               mBgColor = "#EEE863";
               break;
           case 256:
               mBgColor = "#EDB04D";
               break;
           case 512:
               mBgColor = "#ECB04D";
               break;
           case 1024:
               mBgColor = "#EB9437";
               break;
           case 2048:
               mBgColor = "#EA7821";
               break;
           default:
               mBgColor = "#EA7821";
               break;
       }
       return mBgColor;
   }

}
