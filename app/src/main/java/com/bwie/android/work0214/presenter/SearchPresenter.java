package com.bwie.android.work0214.presenter;

import com.bwie.android.work0214.bean.SearchBean;
import com.bwie.android.work0214.contract.SearchContract;
import com.bwie.android.work0214.model.SearchModel;

import java.util.HashMap;
import java.util.List;

public class SearchPresenter extends SearchContract.SearchPresenter {
    private SearchContract.ISearchView iSearchView;
    private SearchModel searchModel;

    public SearchPresenter(SearchContract.ISearchView iSearchView) {
        searchModel = new SearchModel();
        this.iSearchView = iSearchView;
    }

    @Override
    public void obtinData(HashMap<String, String> params) {
        if (searchModel != null) {
            searchModel.obtinData(params, new SearchModel.SearchModelCallback() {
                @Override
                public void success(List<SearchBean.ResultBean> resultBeans) {
                    if (iSearchView != null) {
                        iSearchView.success(resultBeans);
                    }
                }

                @Override
                public void failure(String msg) {
                    if (iSearchView != null) {
                        iSearchView.failure(msg);
                    }
                }
            });
        }
    }
}
