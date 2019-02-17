package com.ay3524.mvvmlearn.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitController {

    private static final String BASE_URL = "https://pixabay.com/";

    private static RetrofitController retrofitController;

    private RetrofitController() {

    }

    public static synchronized RetrofitController getInstance() {
        if (retrofitController == null) {
            retrofitController = new RetrofitController();
        }
        return retrofitController;
    }

    /**
     * This method creates the api call instance
     */
    public ApiCall getApiCallInstance() {

        //TODO Add cache and interceptor later
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .build();

        return retrofit.create(ApiCall.class);
    }

    /**
     * This method returns the Gson object.
     * Gson converts json to java object model
     */
    private Gson getGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    /**
     * This method returns the HttpLoggingInterceptor.
     * HttpLoggingInterceptor is needed to check the response and request send to the backend
     */
    private HttpLoggingInterceptor getLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Log.e(RetrofitController.class.getSimpleName(), message)).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    /**
     * This method returns the OkHttpClient.
     * As Retrofit uses OkHttpClient, so we can configure timeouts,interceptor,etc. here.
     */
    private OkHttpClient getOkHttpClient() {

        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(120 * 1000, TimeUnit.MILLISECONDS)
                .build();
    }
}
