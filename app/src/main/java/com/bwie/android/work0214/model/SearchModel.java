package com.bwie.android.work0214.model;

import com.bwie.android.work0214.api.SearchApiSearvice;
import com.bwie.android.work0214.bean.SearchBean;
import com.bwie.android.work0214.contract.SearchContract;
import com.bwie.android.work0214.utils.RetrofitUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchModel implements SearchContract.ISearchModel {

    @Override
    public void obtinData(HashMap<String, String> params, final SearchModelCallback callback) {
//        创建请求接口类对象，体现一个动态代理模式
        SearchApiSearvice searchApiSearvice = RetrofitUtil.getInstance().create(SearchApiSearvice.class);
        //第三步，创建请求对象
        Call<SearchBean> search = searchApiSearvice.search(params);
        search.enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                SearchBean searchBean = response.body();
                List<SearchBean.ResultBean> result = searchBean.result;
                if (callback != null) {
                    callback.success(result);
                }
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {
                if (callback != null) {
                    callback.failure("网络异常，请稍后再试");
                }
            }
        });
    }

    public interface SearchModelCallback {
        void success(List<SearchBean.ResultBean> resultBeans);

        void failure(String msg);
    }

    private SearchModelCallback searchModelCallback;

    public void setSearchModelCallback(SearchModelCallback searchModelCallback) {
        this.searchModelCallback = searchModelCallback;
    }
}
