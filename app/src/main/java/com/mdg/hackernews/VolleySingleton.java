package com.mdg.hackernews;

/**
 * Created by abhishek on 7/5/15.
 */


        import android.graphics.Bitmap;
        import android.support.v4.util.LruCache;

        import com.android.volley.RequestQueue;
        import com.android.volley.toolbox.ImageLoader;
        import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(HackerNewsInstance.getAppContext());
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            private LruCache<String, Bitmap> cache = new LruCache<>((int) (
                    Runtime.getRuntime().maxMemory() / 1024) / 8);

            @Override
            public Bitmap getBitmap (String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap (String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance(){

        if(sInstance == null){
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
    public ImageLoader getImageLoader(){
        return mImageLoader;
    }

}

