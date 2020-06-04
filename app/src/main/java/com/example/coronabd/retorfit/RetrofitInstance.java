package com.example.coronabd.retorfit;
import com.example.coronabd.manager.StaticDataManager;
import com.example.coronabd.other.MyServiceInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private RetrofitInstance(){}

    private static Retrofit retrofit = null;

    private static API api=getInstance().create(API.class);

    private static Retrofit getInstance(){
        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new MyServiceInterceptor())
                    .build();

            retrofit = new Retrofit.Builder().baseUrl(StaticDataManager.END_POINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;
    }

    public static API getApi(){
        return api;
    }


}
