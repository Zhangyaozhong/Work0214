package com.bwie.android.work0214.model;

import com.bwie.android.work0214.api.ParticularsApiSearvice;
import com.bwie.android.work0214.bean.ParticularsBean;
import com.bwie.android.work0214.contract.ParticularsContract;
import com.bwie.android.work0214.utils.RetrofitUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParticularsModel implements ParticularsContract.IParticularsModel {

    @Override
    public void obtinData(HashMap<String, String> params, final ParticularsModelCallback callback) {
//        创建请求接口类对象，体现一个动态代理模式
        ParticularsApiSearvice ParticularsApiSearvice = RetrofitUtil.getInstance().create(ParticularsApiSearvice.class);
        //第三步，创建请求对象
        Call<ParticularsBean> Particulars = ParticularsApiSearvice.particulars(params);
        //执行异步请求
        Particulars.enqueue(new Callback<ParticularsBean>() {
            @Override
            public void onResponse(Call<ParticularsBean> call, Response<ParticularsBean> response) {
                ParticularsBean particularsBean = response.body();
                assert particularsBean != null;
                ParticularsBean.ResultBean result = particularsBean.getResult();
                if (callback != null) {
                    callback.success(result);
                }
            }

            @Override
            public void onFailure(Call<ParticularsBean> call, Throwable t) {
                if (callback != null) {
                    callback.failure("网络异常，请稍后再试");
                }
            }
        });
    }

    public interface ParticularsModelCallback {
        void success(ParticularsBean.ResultBean resultBeans);

        void failure(String msg);
    }

    private ParticularsModelCallback ParticularsModelCallback;

    public void setParticularsModelCallback(ParticularsModelCallback ParticularsModelCallback) {
        this.ParticularsModelCallback = ParticularsModelCallback;
    }
}
