package com.example.mvvm_demo;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<Integer> progressMutableData = new MutableLiveData<>();
    MutableLiveData<String> drinkMutableData = new MutableLiveData<>();

    MainRepository mainRepository;

    public MainViewModel() {
        progressMutableData.postValue(View.INVISIBLE);
        drinkMutableData.postValue("");
        mainRepository = new MainRepository();
    }

    public void suggestDrinkName(){
        progressMutableData.postValue(View.VISIBLE);
        mainRepository.suggestDrink(new MainRepository.onDrinkCallback() {
            @Override
            public void onDrinkSuggested(String drinkName) {
                progressMutableData.postValue(View.INVISIBLE);
                drinkMutableData.postValue(drinkName);
            }

            @Override
            public void onErrorOccurred() {

            }
        });
    }

    public LiveData<Integer> getProgress(){
        return progressMutableData;
    }
    public LiveData<String> getDrink(){
        return drinkMutableData;
    }
}
