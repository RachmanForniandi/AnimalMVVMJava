package com.example.animalmvvmjavaapp.networkSupport;

import com.example.animalmvvmjavaapp.model.Animal;
import com.example.animalmvvmjavaapp.model.ApiKeyModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AnimalService {

    @GET("getKey")
    public Single<ApiKeyModel>getApiKey();

    @POST("getAnimals")
    public Single<List<Animal>>getDataOfAnimals(@Field("key")String key);
}
