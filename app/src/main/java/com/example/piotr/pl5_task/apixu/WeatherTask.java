package com.example.piotr.pl5_task.apixu;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piotr.pl5_task.consts.Common;
import com.example.piotr.pl5_task.entity.Weather;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class WeatherTask extends AsyncTask<String, Void, Weather> {

    final private ApixuAPIInterace apixuAPI;
    private Weather weather = null;

    public WeatherTask()
    {
        apixuAPI = ApixuAPIHelper.getClient().create(ApixuAPIInterace.class);
    }
    @Override
    protected Weather doInBackground(String... city) {
        Log.e("city name", city[0]);
        Response<Weather> response = null;
        Call<Weather> call = apixuAPI.getWeather(Common.API_KEY, city[0], "7");
        try {
            if (isCancelled())
                return null;
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isCancelled())
            return null;
        if (response != null) {
            weather = response.body();
        }
        return weather;
    }
}
