package com.example.piotr.pl5_task.adpater;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;

import com.example.piotr.pl5_task.R;
import com.example.piotr.pl5_task.entity.Forecast;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder> {

    private Forecast forecast;
    private OnItemClickListener listener;

    public WeatherAdapter(Forecast forecast){
        this.forecast = forecast;
    }

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        // Inflate the custom layout
        View  v = inflater.inflate(R.layout.item_forcast_days, viewGroup, false);
        // Return a new holder instance
        return new WeatherHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder weatherHolder, int i) {
        weatherHolder.bindForecast(i, getForecast());
    }

    @Override
    public int getItemCount() {
        return forecast.getForecastday().size();
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
