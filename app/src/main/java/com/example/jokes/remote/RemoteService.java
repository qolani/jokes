package com.example.jokes.remote;

import com.example.jokes.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteService {

    private static volatile Retrofit instance = null;

    /****
     * OKHttp setup
     */

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build();

    /****
     * Retrofit setup
     * @return
     */
    public static RemoteClient getInstance() {

        // Android concept - Double checked locking
        // Safe threading
        // Only created once it is needed
        // Once the instance is created, the instance won't be changed and the next thread will use the same instance rather to create a new instance.

        if (instance == null) {
            synchronized (RemoteService.class) {
                if (instance == null) {
                    instance = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(BuildConfig.BASE_URL)
                            .client(httpClient)
                            .build();
                }
            }
        }

        return instance.create(RemoteClient.class);
    }
}
