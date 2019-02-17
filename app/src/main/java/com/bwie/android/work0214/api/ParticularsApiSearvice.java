package com.bwie.android.work0214.api;

import com.bwie.android.work0214.bean.ParticularsBean;
import com.bwie.android.work0214.bean.SearchBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ParticularsApiSearvice {
//    @GET
//    void search(@Url String url, @Query("keyword") String keyword, @Query("page") String apge, @Query("count") String count);

    @GET(ParticularsApi.PARTICULARS_URL)
    Call<ParticularsBean> particulars(@QueryMap HashMap<String, String> params);
}
