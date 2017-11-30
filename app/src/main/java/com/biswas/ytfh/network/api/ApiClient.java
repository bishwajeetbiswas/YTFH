package com.biswas.ytfh.network.api;

import android.content.Context;

import com.biswas.ytfh.BuildConfig;
import com.biswas.ytfh.network.ConnectivityInterceptor;
import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    private static Retrofit getClient(String url, Context context, Gson gson) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            OkLogInterceptor okLogInterceptor = OkLogInterceptor.builder().build();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
            builder.addInterceptor(okLogInterceptor);
        }

        builder.addInterceptor(new ConnectivityInterceptor(context));
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

    private static YtfhApi mInterface;

    public static YtfhApi getInterface(Context context) {
        if (mInterface == null) {
            mInterface = getClient(BuildConfig.YTFH_BASE_URL, context, new Gson()).create(YtfhApi.class);
        }
        return mInterface;
    }
}

