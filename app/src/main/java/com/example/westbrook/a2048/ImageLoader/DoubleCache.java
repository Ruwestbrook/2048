package com.example.westbrook.a2048.ImageLoader;

import android.graphics.Bitmap;

/**
 * Created by westbrook on 2017/6/19.
 */

public class DoubleCache implements ImageCache{
    MemoryCache mMemoryCache=new MemoryCache();
    DiskCache mDiskCache=new DiskCache();
    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap=mMemoryCache.get(url);
        if(bitmap==null){
             bitmap=mDiskCache.get(url);
        }
        return bitmap;
    }
}
