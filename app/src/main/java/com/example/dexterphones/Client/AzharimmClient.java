package com.example.dexterphones.Client;

import static com.example.dexterphones.Constants.Constants.BASE_URL;

import com.example.dexterphones.Interface.AzharimmAPI;
import com.example.dexterphones.model.brands.ListBrands;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class AzharimmClient {

    private static Retrofit retrofit = null;

    public static AzharimmAPI getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(AzharimmAPI.class);
    }
}
