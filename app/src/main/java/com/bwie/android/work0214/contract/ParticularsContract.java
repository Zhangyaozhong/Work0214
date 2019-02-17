package com.bwie.android.work0214.contract;

import com.bwie.android.work0214.bean.ParticularsBean;
import com.bwie.android.work0214.model.ParticularsModel;

import java.util.HashMap;
import java.util.List;

public interface ParticularsContract {

    abstract class ParticularsPresenter {
        public abstract void obtinData(HashMap<String, String> params);
    }

    interface IParticularsView {
         void success(ParticularsBean.ResultBean resultBean);
         void failure(String msg);
    }
    interface IParticularsModel{
        void obtinData(HashMap<String, String> params, ParticularsModel.ParticularsModelCallback callback);
    }
}
