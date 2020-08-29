package com.gopal.gopalagentapptest.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gopal.gopalagentapptest.model.Cities;
import com.gopal.gopalagentapptest.model.Result;
import com.gopal.gopalagentapptest.repositories.CitiesRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private LiveData<Cities> citiesLiveData;
    CitiesRepository citiesRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        citiesRepository = new CitiesRepository();
        this.citiesLiveData = citiesRepository.getMovieArticles();
    }

    public LiveData<Cities> getCitiesLiveData() {
        return citiesLiveData;
    }
}
