package com.example.practiceproject.services;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "https://gadsapi.herokuapp.com";

    // Create Logger
    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    // Create OkHttp Client
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder().addInterceptor(logger);

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    public static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }

    private static OkHttpClient.Builder oBuilder2 = new OkHttpClient.Builder().addInterceptor(logger);

    public static final String SUBMIT_API_URL = "https://docs.google.com/forms/d/e/";
    private static Retrofit.Builder sBuilderSubmit = new Retrofit.Builder().baseUrl(SUBMIT_API_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(oBuilder2.build());

    private static Retrofit sRetrofitSubmit = sBuilderSubmit.build();

    public static <S> S buildSubmitService(Class<S> serviceType) {
        return sRetrofitSubmit.create(serviceType);
    }
}

