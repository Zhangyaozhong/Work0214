package com.bwie.android.work0214.presenter;

import com.bwie.android.work0214.bean.ParticularsBean;
import com.bwie.android.work0214.contract.ParticularsContract;
import com.bwie.android.work0214.model.ParticularsModel;

import java.util.HashMap;
import java.util.List;

public class ParticularsPresenter extends ParticularsContract.ParticularsPresenter {
    private ParticularsContract.IParticularsView iParticularsView;
    private ParticularsModel ParticularsModel;

    public ParticularsPresenter(ParticularsContract.IParticularsView iParticularsView) {
        ParticularsModel = new ParticularsModel();
        this.iParticularsView = iParticularsView;
    }

    @Override
    public void obtinData(HashMap<String, String> params) {
        if (ParticularsModel != null) {
            ParticularsModel.obtinData(params, new ParticularsModel.ParticularsModelCallback() {
                @Override
                public void success(ParticularsBean.ResultBean resultBeans) {
                    if (iParticularsView != null) {
                        iParticularsView.success(resultBeans);
                    }
                }

                @Override
                public void failure(String msg) {
                    if (iParticularsView != null) {
                        iParticularsView.failure(msg);
                    }
                }
            });
        }
    }
}
