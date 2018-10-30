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

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends Fragment {

    private static final String CITY_NAME = "city";
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private Weather weather;
    private WeatherAdapter weatherAdapter;

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
        String cityName = Objects.requireNonNull(getArguments()).getString(CITY_NAME);

        initializeLayout();

        try {
            WeatherTask weatherTask = new WeatherTask();
            weather = weatherTask.execute(cityName).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (weather != null) {
            fetchList();
        } else {
            Log.d("Error: ", "Weather was not fetched...");
        }

        return view;
    }

    private void initializeLayout() {
        weather = new Weather();
        weatherAdapter = new WeatherAdapter(weather.getForecast());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(weatherAdapter);
    }

    public void fetchList() {
        weatherAdapter.setForecast(weather.getForecast());
        weatherAdapter.notifyDataSetChanged();
    }
}
