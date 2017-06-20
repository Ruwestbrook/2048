package com.example.westbrook.a2048;

import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
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
                Toast.makeText(this, "布局3x3", Toast.LENGTH_SHORT).show();
                MyFrameLayout layout3=new MyFrameLayout(this,3);
                mFrameLayout.removeView(lastView);
                mFrameLayout.addView(layout3,layoutParams);
                lastView=layout3;
                break;
            case R.id.layoutX4:
                Toast.makeText(this, "布局4x4", Toast.LENGTH_SHORT).show();
                MyFrameLayout layout4=new MyFrameLayout(this,4);
                mFrameLayout.removeView(lastView);
                mFrameLayout.addView(layout4,layoutParams);
                lastView=layout4;
                break;
            case R.id.layoutX5:
                Toast.makeText(this, "布局5x5", Toast.LENGTH_SHORT).show();
                MyFrameLayout layout5=new MyFrameLayout(this,5);
                mFrameLayout.removeView(lastView);
                mFrameLayout.addView(layout5,layoutParams);
                lastView=layout5;
                break;
            case R.id.layoutX6:
                Toast.makeText(this, "布局6x6", Toast.LENGTH_SHORT).show();
                MyFrameLayout layout6=new MyFrameLayout(this,6);
                mFrameLayout.removeView(lastView);
                mFrameLayout.addView(layout6,layoutParams);
                lastView=layout6;
                break;
        }
    }
}
