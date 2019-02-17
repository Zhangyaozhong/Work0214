package com.bwie.android.work0214.api;

import com.bwie.android.work0214.bean.SearchBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface SearchApiSearvice {
//    @GET
//    void search(@Url String url, @Query("keyword") String keyword, @Query("page") String apge, @Query("count") String count);

    @GET(SearchApi.SEARCH_URL)
    Call<SearchBean> search(@QueryMap HashMap<String, String> params);
}
