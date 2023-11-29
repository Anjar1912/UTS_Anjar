package com.aryobimo.uts.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aryobimo.uts.network.ApiInterface;
import com.aryobimo.uts.network.RetrofitClient;
import com.aryobimo.uts.network.response.ListFilmResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    String urlDataFilm = "https://api.jsonbin.io/b/618dd6084a56fb3dee0da690/";
    MutableLiveData<ListFilmResponse> listFilmResponse= new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public void getDaftarFilm() {
        loading.postValue(true);
        ApiInterface apiInterface;
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        apiInterface.getListFilm(urlDataFilm).enqueue(new Callback<ListFilmResponse>() {
            @Override
            public void onResponse(Call<ListFilmResponse> call, Response<ListFilmResponse> response) {
                loading.postValue(false);
                listFilmResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ListFilmResponse> call, Throwable t) {
                loading.postValue(false);
                Log.i("onFailure", t.getLocalizedMessage());
            }
        });
    }

}
