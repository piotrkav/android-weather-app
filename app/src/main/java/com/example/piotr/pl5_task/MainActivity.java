package com.example.piotr.pl5_task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.piotr.pl5_task.apixu.WeatherTask;
import com.example.piotr.pl5_task.entity.Weather;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    private Weather weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            weather = new WeatherTask().execute("Gdansk").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(weather.getForecast().toString());
    }
}
