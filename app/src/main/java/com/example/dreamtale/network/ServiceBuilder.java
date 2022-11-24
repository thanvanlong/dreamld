package com.example.dreamtale.network;

import android.util.Log;

import com.example.dreamtale.App;
import com.example.dreamtale.BuildConfig;
import com.example.dreamtale.utils.PrefManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String BASE_URL = BuildConfig.BASE_URL;

    private static Retrofit sInstance;
    private static DreamService dreamService;
    private synchronized static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        long nomalTimeout = 5;
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(nomalTimeout, TimeUnit.SECONDS)
                .writeTimeout(nomalTimeout, TimeUnit.SECONDS)
                .connectTimeout(nomalTimeout, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder();
                        builder.addHeader("Content-Type", "application/json");
                        if (!PrefManager.getAccessToken(App.getInstance()).matches("")){
                            Log.e("longtv", "intercept: " + PrefManager.getAccessToken(App.getInstance()) );
                            builder.addHeader("Authorization", "Bearer " + PrefManager.getAccessToken(App.getInstance()));
                        }
                        Request request = builder.method(original.method(), original.body()).build();
                        return chain.proceed(request);
                    }
                })
                .build();

        if (sInstance == null) {
            // User agent
            sInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return sInstance;
    }

    public static DreamService getService() {
        if (dreamService == null) {
            dreamService = getRetrofit().create(DreamService.class);
        }
        return dreamService;
    }
}
