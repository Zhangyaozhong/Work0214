package com.bwie.android.work0214.contract;

import com.bwie.android.work0214.bean.SearchBean;
import com.bwie.android.work0214.model.SearchModel;

import java.util.HashMap;
import java.util.List;

public interface SearchContract {

    abstract class SearchPresenter {
        public abstract void obtinData(HashMap<String, String> params);
    }

    interface ISearchView {
         void success(List<SearchBean.ResultBean> list);
         void failure(String msg);
    }
    interface ISearchModel{
        void obtinData(HashMap<String, String> params, SearchModel.SearchModelCallback callback);
    }
}
