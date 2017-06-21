package com.example.westbrook.a2048;

import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.westbrook.a2048.ImageLoader.DiskCache;
import com.example.westbrook.a2048.ImageLoader.DoubleCache;
import com.example.westbrook.a2048.ImageLoader.ImageCache;
import com.example.westbrook.a2048.ImageLoader.ImageLoader;
import com.example.westbrook.a2048.ImageLoader.MemoryCache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private RelativeLayout mFrameLayout;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private View lastView;
    private ViewGroup.LayoutParams layoutParams;
    private MyFrameLayout mMyFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout= (RelativeLayout) findViewById(R.id.frame_layout);
        mButton1= (Button) findViewById(R.id.layoutX3);
        mButton1.setOnClickListener(this);
        mButton2= (Button) findViewById(R.id.layoutX4);
        mButton2.setOnClickListener(this);
        mButton3= (Button) findViewById(R.id.layoutX5);
        mButton3.setOnClickListener(this);
        mButton4= (Button) findViewById(R.id.layoutX6);
        mButton4.setOnClickListener(this);
         layoutParams=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMyFragment=new MyFrameLayout(this,4);
        mFrameLayout.addView(mMyFragment,layoutParams);
        lastView=mMyFragment;

    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.layoutX3:
//                mMyFragment.setColumn(3);
//            case R.id.layoutX4:
//            case R.id.layoutX5:
//            case R.id.layoutX6:
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layoutX3:
                //撤销
                mMyFragment.cancel();
                break;
            case R.id.layoutX4:
                Toast.makeText(this, "布局4x4", Toast.LENGTH_SHORT).show();
                mMyFragment=new MyFrameLayout(this,4);
                mFrameLayout.removeView(lastView);
                mFrameLayout.addView(mMyFragment,layoutParams);
                lastView=mMyFragment;
                break;
            case R.id.layoutX5:
                Toast.makeText(this, "布局5x5", Toast.LENGTH_SHORT).show();
                mMyFragment=new MyFrameLayout(this,5);
                mFrameLayout.removeView(lastView);
                mFrameLayout.addView(mMyFragment,layoutParams);
                lastView=mMyFragment;
                break;
            case R.id.layoutX6:
                Toast.makeText(this, "布局6x6", Toast.LENGTH_SHORT).show();
                mMyFragment=new MyFrameLayout(this,6);
                mFrameLayout.removeView(lastView);
                mFrameLayout.addView(mMyFragment,layoutParams);
                lastView=mMyFragment;
                break;
        }
    }
    private float downX;
    private float downY;
    private float distanceX;
    private float distanceY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //按下
                downX=x;
                downY=y;
                break;
            case MotionEvent.ACTION_UP:
                //抬起
                distanceX=x;
                distanceY=y;
                Log.d(TAG, "onTouchEvent:X "+distanceX+"   "+downX);
                Log.d(TAG, "onTouchEvent:Y "+distanceY+"   "+downY);
                if(distanceX-downX>80){
                    //向右划
                    mMyFragment.getMovedType(1);
                }else if(downX-distanceX>50){
                    //向左划
                    mMyFragment.getMovedType(2);
                }else if(downY-distanceY>20){
                    //向上划
                    mMyFragment.getMovedType(3);
                }else if(distanceY-downY>50){
                    //向下滑
                    mMyFragment.getMovedType(4);
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
