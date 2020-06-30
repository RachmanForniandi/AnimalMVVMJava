package com.example.animalmvvmjavaapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.animalmvvmjavaapp.model.Animal;
import com.example.animalmvvmjavaapp.model.ApiKeyModel;
import com.example.animalmvvmjavaapp.networkSupport.AnimalService;
import com.example.animalmvvmjavaapp.networkSupport.ServiceRemote;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {
    private ServiceRemote serviceRemote = new ServiceRemote();
    private CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<List<Animal>> animals = new MutableLiveData<List<Animal>>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void setDummyData(){
        /*Animal animalData1 = new Animal("Lion");
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
        loading.setValue(false);*/
        loading.setValue(true);
        obtainKeyApi();


    }

    private void obtainKeyApi(){
        disposable.add(
                serviceRemote.useApiKey()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiKeyModel>() {
                    @Override
                    public void onSuccess(ApiKeyModel apiKeyModel) {
                        if (apiKeyModel.key.isEmpty()){
                            loadError.setValue(true);
                            loading.setValue(false);
                        }else {
                            obtainAnimalData(apiKeyModel.key);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        loading.setValue(true);
                        loadError.setValue(false);
                    }
                })
        );
    }

    private void obtainAnimalData(String key) {
        disposable.add(
                serviceRemote.useAnimalData(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Animal>>() {
                    @Override
                    public void onSuccess(List<Animal> animalsData) {
                        loading.setValue(false);
                        animals.setValue(animalsData);
                        loadError.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        loading.setValue(false);
                        loadError.setValue(true);
                    }
                })
        );
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
