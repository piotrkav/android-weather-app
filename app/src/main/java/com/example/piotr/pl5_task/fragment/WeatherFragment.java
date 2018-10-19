package com.example.piotr.pl5_task.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piotr.pl5_task.R;
import com.example.piotr.pl5_task.adpater.WeatherAdapter;
import com.example.piotr.pl5_task.apixu.WeatherTask;
import com.example.piotr.pl5_task.entity.Weather;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends Fragment {

    private static final String CITY_NAME = "city";
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private String cityName;
    private Weather weather;
    private WeatherAdapter weatherAdapter;
    private WeatherTask weatherTask;

    public WeatherFragment() {

    }

    public static WeatherFragment newInstance(String cityName) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(CITY_NAME, cityName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.weather_fragment, container, false);
        ButterKnife.bind(this, view);
        weatherTask = new WeatherTask();
        try {
            weather = weatherTask.execute("Gdansk").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (weather != null) {
            fetchList();
        } else {
            Log.d("Error", "cos nie dziala");
        }
        return view;
    }

    public void fetchList() {
        if (weatherAdapter == null) {
            weatherAdapter = new WeatherAdapter(weather.getForecast());
            recyclerView.setAdapter(weatherAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            weatherAdapter.setForecast(weather.getForecast());
            weatherAdapter.notifyDataSetChanged();
        }

    }
}
