package com.example.mvvm_demo;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRepository {

    String[] drinkList = {"Milk", "Coffee", "Orange juice", "Cola", "Red tea"};

    public void suggestDrink(onDrinkCallback drinkCallback) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        final String[] drinkName = {""};

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                    drinkCallback.onErrorOccurred();
                }
                drinkName[0] = drinkList[new Random().nextInt(drinkList.length)];
                drinkCallback.onDrinkSuggested(drinkName[0]);
            }
        });
    }
    public interface onDrinkCallback{
        void onDrinkSuggested(String drinkName);
        void onErrorOccurred();
    }
}
