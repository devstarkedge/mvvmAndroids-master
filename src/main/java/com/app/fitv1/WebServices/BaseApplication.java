/*
 * Copyright (c) 2017. Code by PRM . Happy coding
 */

package com.app.fitv1.WebServices;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.widget.LinearLayout;

import androidx.multidex.MultiDex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApplication extends Application {

    private static Retrofit retrofit;
    private final static float CLICK_DRAG_TOLERANCE = 10; // Often, there will be a slight, unintentional, drag when the user taps the FAB, so we need to account for this.

    private float downRawX, downRawY;
    private float dX, dY;
    private LinearLayout llMinimizedPlayer;

    /**
     * Change this to {@code false} when you want to use the downloadable Emoji font.
     */

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            initRetrofitModule();
        }
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(getApplicationContext());

        //for camera
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        initRetrofitModule();
    }

    private static void initRetrofitModule() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Web.Path.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okClient())
                .build();

    }

    private static OkHttpClient okClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .build();
    }
}
