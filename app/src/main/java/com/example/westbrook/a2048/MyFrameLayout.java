package com.example.westbrook.a2048;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by westbrook on 2017/6/16.
 */

public class MyFrameLayout extends FrameLayout {
    private static final String TAG = "MyFrameLayout";
    //设置item的数量column*column  默认是4
    private int column=4;
    //存放item
    private Item[] mItems;
    //每个item的间距
    private int margin=10;
    //内边距
    private int padding=10;

    public void setColumn(int column) {
        this.column = column;
        isFirst=true;
        invalidate();

        Log.d(TAG, "setColumn: 刷新");
    }

    private int itemWidth;
    private int itemHeight;
    private Context mContext;
    boolean isFirst=true;
    public MyFrameLayout(@NonNull Context context) {
        this(context,null);
    }
    public MyFrameLayout(Context context,int column){
        this(context);
        this.column=column;
        mItems= new Item[column * column];
    };
    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(isFirst){
            itemHeight=(this.getMeasuredHeight()-padding*(column-1))/column;
            itemWidth=(this.getMeasuredWidth()-margin*(column-1))/column;
            initView(column);
            isFirst=false;
        }

    }

    private void initView(int column) {
        Log.d(TAG, "initView: "+itemWidth+"  "+itemHeight);
        for (int i = 0; i < column*column; i++) {
            Item item=new Item(mContext);
            mItems[i]=item;
            FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(itemWidth,itemHeight);
            int a=i%column;//在第几个
            int b=i/column;//是第几行
            lp.topMargin=(padding+itemHeight)*b;
            lp.leftMargin=(margin+itemWidth)*a;
            int num=0;
            num+=Math.pow(2,i);
            if(num>2048)
                num=2048;
            item.setNumber(num);
            this.addView(item,lp);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
