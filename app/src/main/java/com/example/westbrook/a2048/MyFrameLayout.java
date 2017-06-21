package com.example.westbrook.a2048;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private int time=0;
    private int itemWidth;
    private int itemHeight;
    private Context mContext;
    boolean isFirst=true;
    //存放之前的布局，用于撤销
    private List<int[][]> mList;
    private int stepCount=0;
    public MyFrameLayout(@NonNull Context context) {
        this(context,null);
    }
    public MyFrameLayout(Context context,int column){
        this(context);
        this.column=column;
        mItems= new Item[column * column];
        mList=new ArrayList<>();
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
        for (int i = 0; i < column*column; i++) {
            Item item=new Item(mContext);
            mItems[i]=item;
            FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(itemWidth,itemHeight);
            int a=i%column;//在第几个
            int b=i/column;//是第几行
            lp.topMargin=(padding+itemHeight)*b;
            lp.leftMargin=(margin+itemWidth)*a;
            item.setNumber(0);
            this.addView(item,lp);
        }
        int a= (int) (Math.random()*(column*column));
        int b=(int) (Math.random()*(column*column));
        while(b==a){
            b=(int) (Math.random()*(column*column));
        }
        mItems[a].setNumber(2);
        mItems[b].setNumber(2);
    }




    void getMovedType(int type){
        //用于存放数据，可以操作
        int[][] number=new int[column][column];
        //用于缓存，以便还原  如果使用同一个，他们地址是相同的，
        // 代表每次都是操作同一个数组，会引起数据错误
        int[][] data=new int[column][column];
        //保存上次的记录
        for (int i = 0; i < column; i++) {
            StringBuffer bu=new StringBuffer();
            for (int j = 0; j < column; j++) {
                bu.append(number[i][j]);
                number[i][j] = mItems[i * column + j].getNumber();
                data[i][j] = mItems[i * column + j].getNumber();
            }
            Log.d("Size", "cancel: "+i+"行"+bu.toString());
        }
        Log.d(TAG, "getMovedType: "+data.toString()+" "+number.toString());
        mList.add(data);
        stepCount++;
        switch (type){
            case 1://向右
                rightMove(number);
                break;
            case 2://向左
                leftMove(number);
                break;
            case 3://向上
                upMove(number);
                break;
            case 4://向下
                downMove(number);
                break;
        }
        //设置数据
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < column; j++) {
               mItems[i*column+j].setNumber(number[i][j]);
            }
        }
        setNewNumber();
    }
    //右滑的算法
    public void rightMove(int[][] number) {
        //靠右边的先算，两两相同就想加

        for (int i = 0; i < column; i++) {
            List<Integer> list=new ArrayList<>();
            for (int j = column-1; j >-1; j--) {
              if(number[i][j]!=0){
                  list.add(number[i][j]);
              }
            }
            for (int i1 = 0; i1 < list.size(); i1++) {
                if(i1+1>=list.size()){
                    break;
                }
                if(list.get(i1)==list.get(i1+1)){
                    list.set(i1,list.get(i1+1)*2);
                    list.set(i1+1,0);
                }
            }
            int[] a=new int[column];
            int index=column-1;
            for (int j=0;j<list.size();j++) {
                if(list.get(j)!=0){
                    a[index]=list.get(j);
                    index--;
                }
            }
            number[i]=a;
        }
    }
    public void leftMove(int[][] number) {
        for (int i = 0; i < column; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                if (number[i][j] != 0) {
                    //取每一列不为0的数字
                    list.add(number[i][j]);
                }
            }
            for (int k = 0; k < list.size(); k++) {
                if (k + 1 >= list.size()) {
                    break;
                }
                if (list.get(k) == list.get(k + 1)) {
                    list.set(k, list.get(k + 1) * 2);
                    list.set(k + 1, 0);
                }
            }
            int[] a = new int[column];
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) != 0) {
                    a[j] = list.get(j);
                }
            }
            for (int j = 0; j < column; j++) {
                number[i][j]=a[j];
            }
        }

    }
    public void upMove(int[][] number) {
        for (int i = 0; i < column; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                if (number[j][i] != 0) {
                    //取每一列不为0的数字
                    list.add(number[j][i]);
                }
            }
            for (int k = 0; k < list.size(); k++) {
                if (k + 1 >= list.size()) {
                    break;
                }
                if (list.get(k) == list.get(k + 1)) {
                    list.set(k, list.get(k + 1) * 2);
                    list.set(k + 1, 0);
                }
            }
            int[] a = new int[column];
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) != 0) {
                    a[j] = list.get(j);
                }
            }
            for (int j = 0; j < column; j++) {
                number[j][i]=a[j];
            }
        }
    }

    public void downMove(int[][] number) {
        for (int i = 0; i < column; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = column - 1; j > -1; j--) {
                if (number[j][i] != 0) {
                    list.add(number[j][i]);
                }
            }
            for (int k = 0; k < list.size(); k++) {
                if (k + 1 >= list.size()) {
                    break;
                }
                if (list.get(k) == list.get(k + 1)) {
                    list.set(k, list.get(k + 1) * 2);
                    list.set(k + 1, 0);
                }
            }
            int[] a = new int[column];
            int index = column - 1;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) != 0) {
                    a[index] = list.get(j);
                    index--;
                }
            }
            for (int j =column-1; j >-1 ; j--) {
                number[j][i]=a[j];
            }
        }
    }

    //设置数字
    private void setNewNumber() {
        int a= (int) (Math.random()*(column*column));
       int b=0;
       L:while (mItems[a].getNumber()!=0 ){
           a= (int) (Math.random()*(column*column));
       }
        mItems[a].setNumber(getSmallNumber());
    }
    //添加数字的数值
    private int getSmallNumber() {
        if(time==4){
            time=0;
            return 4;
        }
        return 2;
    }
    //设置行数
    public void setColumn(int column) {
        this.column = column;
        isFirst=true;
        invalidate();
    }
    //撤销
    public void cancel() {
        if(mList.size()==0){
            return;
        }
        int[][] number=mList.get(mList.size()-1);
        //设置数据
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < column; j++) {
                mItems[i*column+j].setNumber(number[i][j]);
            }
        }
        mList.remove(mList.size()-1);
    }

}
