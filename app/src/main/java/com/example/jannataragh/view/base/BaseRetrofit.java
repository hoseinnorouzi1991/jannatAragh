package com.example.jannataragh.view.base;

import com.example.jannataragh.date.IStoreService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseRetrofit {

    String baseUrl = "http://grafik.computertalk.ir/StoreCode";
    IStoreService mIStoreService;

    public void RetrofitExecute()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mIStoreService = retrofit.create(IStoreService.class);
        
    }

}