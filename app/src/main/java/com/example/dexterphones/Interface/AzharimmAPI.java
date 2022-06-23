package com.example.dexterphones.Interface;

import com.example.dexterphones.model.brands.ListBrands;
import com.example.dexterphones.model.latest.ListLatest;
import com.example.dexterphones.model.phones.SearchPhone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface    AzharimmAPI {
    //list all brands
    @GET("brands")
    Call<ListBrands> getBrands();
    //latest
    @GET("latest")
    Call<ListLatest> getLatest(); //jackie
    //search
    @GET("search")
    Call<SearchPhone>getDevices(@Query("query") String query);
}
