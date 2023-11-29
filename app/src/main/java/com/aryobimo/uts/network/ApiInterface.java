package com.aryobimo.uts.network;

import com.aryobimo.uts.network.response.ListFilmResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<ListFilmResponse> getListFilm(@Url String url);

}