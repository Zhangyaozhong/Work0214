package com.bwie.android.work0214.app;

import android.app.Application;
import android.os.Environment;

import com.bwie.android.work0214.utils.GreenDaoUtil;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        Fresco自定义缓存路径
        DiskCacheConfig config = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("images")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();
        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(config)
                .build();
        Fresco.initialize(this,build);
        GreenDaoUtil.getInstance(this);
    }

}
