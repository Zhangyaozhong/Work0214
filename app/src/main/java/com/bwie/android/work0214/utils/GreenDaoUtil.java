package com.bwie.android.work0214.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bwie.android.work0214.database.greenDao.db.DaoMaster;
import com.bwie.android.work0214.database.greenDao.db.DaoSession;

public class GreenDaoUtil {
    private static GreenDaoUtil mInstance;

    private GreenDaoUtil(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "search.db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();


    }

    public static GreenDaoUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (GreenDaoUtil.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoUtil(context);
                }
            }
        }
        return mInstance;
    }

   /* public void initGreendao(Context context) {

    }*/

    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
