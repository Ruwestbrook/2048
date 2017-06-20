package com.example.westbrook.a2048.ImageLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by westbrook on 2017/6/19.
 */

public class DiskCache implements ImageCache {
    private static String PATH="sdcard/cache/";
    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream stream=null;
        try {
            stream=new FileOutputStream(PATH+url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            {
                if(stream!=null){
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public Bitmap get(String url) {

        return BitmapFactory.decodeFile(PATH+url);
    }
}
