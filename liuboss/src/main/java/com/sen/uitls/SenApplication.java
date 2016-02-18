package com.sen.uitls;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;

import java.io.File;

/**
 * Created by Sen on 2016/1/6.
 */
public class SenApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initBmob();
        initImageLoader(this);//初始化图像缓存加载器
    }

    private void initBmob() {

        //sBmob.initialize(this,"6d66f030aaf8a34c92f5b13d13b3d0dc");
    }

    /**
     * 初始化ImageLoader
     */
    @SuppressWarnings("deprecation")
    public static void initImageLoader(Context context) {
        File cacheDir = new File(Environment.getExternalStorageDirectory()
                + "/cache");// 获取到缓存的目录地址
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                // 线程池内加载的数量
                .writeDebugLogs()
                .threadPoolSize(4).threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheSize(50 * 1024 * 1024)
                .discCache(new UnlimitedDiskCache(cacheDir))// 自定义缓存路径
                // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置

        com.nostra13.universalimageloader.utils.L.writeLogs(false);
    }
}
