package com.example.animalmvvmjavaapp.networkSupport;

import com.example.animalmvvmjavaapp.model.Animal;
import com.example.animalmvvmjavaapp.model.ApiKeyModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceRemote {

    private static final String BASE_URL ="https://us-central1-apis-4674e.cloudfunctions.net/";


    AnimalService animalService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AnimalService.class);

    public Single<ApiKeyModel> useApiKey(){
        return animalService.getApiKey();
    }

    public Single<List<Animal>> useAnimalData(String key){
        return animalService.getDataOfAnimals(key);
    }
}
