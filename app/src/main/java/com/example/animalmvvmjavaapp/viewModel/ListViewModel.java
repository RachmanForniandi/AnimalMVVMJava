package com.example.animalmvvmjavaapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.animalmvvmjavaapp.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<Animal>> animals = new MutableLiveData<List<Animal>>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void setDummyData(){
        Animal animalData1 = new Animal("Lion");
        Animal animalData2 = new Animal("Zebra");
        Animal animalData3 = new Animal("Bear");
        Animal animalData4 = new Animal("Cheetah");
        Animal animalData5 = new Animal("Jaguar");

        List<Animal> animalList = new ArrayList<>();
        animalList.add(animalData1);
        animalList.add(animalData2);
        animalList.add(animalData3);
        animalList.add(animalData4);
        animalList.add(animalData5);

        animals.setValue(animalList);
        loadError.setValue(false);
        loading.setValue(false);

    }


}
