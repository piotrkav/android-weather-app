package com.example.piotr.pl5_task.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.piotr.pl5_task.R;
import com.example.piotr.pl5_task.adpater.WeatherAdapter;
import com.example.piotr.pl5_task.apixu.WeatherTask;
import com.example.piotr.pl5_task.entity.Weather;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.content.ContextCompat.getSystemService;

public class WeatherFragment extends Fragment {

    private static final String CITY_NAME = "city";
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.city_name_text)
    EditText cityNameInput;
    @BindView(R.id.get_weather_btn)
    Button getWeatherButton;
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


        getWeatherButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = cityNameInput.getText().toString();
                if (TextUtils.isEmpty(cityName)) {
                    cityName = Objects.requireNonNull(getArguments()).getString(CITY_NAME);
                }
                try {
                    weatherTask = new WeatherTask();
                    weather = weatherTask.execute(cityName).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                if (weather != null) {
                    fetchList();
                } else {
                    Log.d("Error", "cos nie dziala");
                }
            }
        });
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
