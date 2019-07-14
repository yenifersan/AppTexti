package com.santiago.textimaxapp;


import com.santiago.textimaxapp.models.Compra;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String API_BASE_URL = "http://192.168.137.1:9092";

    @GET("api/compras/")
    Call<List<Compra>> getCompras();

    @GET("api/compra/{id}")
    Call<Compra>showCompra(@Path("id")Integer id);

}



