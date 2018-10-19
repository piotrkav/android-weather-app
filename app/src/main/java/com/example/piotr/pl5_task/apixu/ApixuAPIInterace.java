package com.example.piotr.pl5_task.apixu;

import com.example.piotr.pl5_task.entity.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApixuAPIInterace {

    @GET("forecast.json ")
    Call<Weather> getWeather(@Query("key") String key, @Query("q") String city, @Query("days") String days);
}
