package com.bwie.android.work0214.database.greenDao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.bwie.android.work0214.bean.ListBean;

import com.bwie.android.work0214.database.greenDao.db.ListBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig listBeanDaoConfig;

    private final ListBeanDao listBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        listBeanDaoConfig = daoConfigMap.get(ListBeanDao.class).clone();
        listBeanDaoConfig.initIdentityScope(type);

        listBeanDao = new ListBeanDao(listBeanDaoConfig, this);

        registerDao(ListBean.class, listBeanDao);
    }
    
    public void clear() {
        listBeanDaoConfig.clearIdentityScope();
    }

    public ListBeanDao getListBeanDao() {
        return listBeanDao;
    }

}
