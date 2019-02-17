package com.ay3524.mvvmlearn.main;

import com.ay3524.mvvmlearn.NetworkingConstants;
import com.ay3524.mvvmlearn.StateLiveData;
import com.ay3524.mvvmlearn.data.ApiCall;
import com.ay3524.mvvmlearn.data.RetrofitController;
import com.ay3524.mvvmlearn.pojo.PixaBay;
import com.ay3524.mvvmlearn.pojo.Wallpaper;

import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MainRepository {

    private ApiCall apiCall;

    public MainRepository() {
        apiCall = RetrofitController.getInstance().getApiCallInstance();
    }

     public StateLiveData<List<Wallpaper>> getImages() {
        // This isn't an optimal implementation. We'll fix it later.
        StateLiveData<List<Wallpaper>> data = new StateLiveData<>();
        data.postLoading();

        apiCall.getWallpaper(NetworkingConstants.API_KEY).enqueue(new Callback<PixaBay>() {
            @Override
            public void onResponse(@NonNull Call<PixaBay> call, @NonNull Response<PixaBay> response) {
                if (response.body() != null) {
                    data.postSuccess(response.body().getHits());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PixaBay> call, @NonNull Throwable t) {
                data.postError(t);
            }
            // Error case is left out for brevity :).
        });
        return data;
    }
}
