package com.bwie.android.work0214.app;

import android.app.Application;

import com.bwie.android.work0214.utils.GreenDaoUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        GreenDaoUtil.getInstance(this);
    }

}
