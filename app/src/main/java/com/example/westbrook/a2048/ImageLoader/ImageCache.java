package com.example.westbrook.a2048.ImageLoader;

import android.graphics.Bitmap;

/**
 * Created by westbrook on 2017/6/19.
 */

public interface ImageCache {
    void put(String url, Bitmap bitmap);
    Bitmap get(String url);
}
