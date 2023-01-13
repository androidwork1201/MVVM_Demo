package com.example.mvvm_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.mvvm_demo.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                binding.progressBar.setVisibility(visibility);
            }
        });

        mainViewModel.getDrink().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String drinkName) {
                binding.textResult.setVisibility(View.VISIBLE);
                binding.textResult.setText(String.valueOf(drinkName));
            }
        });

        binding.btnGetAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textResult.setVisibility(View.INVISIBLE);
                mainViewModel.suggestDrinkName();
            }
        });
    }

}