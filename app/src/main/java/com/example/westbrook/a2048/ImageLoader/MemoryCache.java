package com.example.westbrook.a2048.ImageLoader;

import android.graphics.Bitmap;
import android.util.LruCache;


/**
 * Created by westbrook on 2017/6/19.
 */

public class MemoryCache implements ImageCache {
    int maxSize= (int) (Runtime.getRuntime().maxMemory()/1024);
    LruCache<String,Bitmap> mLruCache=new LruCache<String,Bitmap>(maxSize){
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
    }
        };
    @Override
    public void put(String url, Bitmap bitmap) {
        mLruCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return mLruCache.get(url);
    }
}
