package com.gopal.gopalagentapptest.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gopal.gopalagentapptest.API.ClientApi;
import com.gopal.gopalagentapptest.model.Cities;
import com.gopal.gopalagentapptest.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitiesRepository {
    private static CitiesRepository instance;

    public static CitiesRepository getInstance(){
        if(instance == null){
            instance = new CitiesRepository();
        }
        return instance;
    }

    public LiveData<Cities> getMovieArticles() {
        final MutableLiveData<Cities> data = new MutableLiveData<>();
        ClientApi.getService().CITIES_LIVE_DATA("All").enqueue(new Callback<Cities>() {
                    @Override
                    public void onResponse(Call<Cities> call, Response<Cities> response) {
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Cities> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

}
