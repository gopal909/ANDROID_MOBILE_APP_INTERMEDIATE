package com.gopal.gopalagentapptest.API;

import androidx.lifecycle.LiveData;

import com.gopal.gopalagentapptest.model.Cities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("search")
    Call<Cities> CITIES_LIVE_DATA (@Query("term") String term);

}
