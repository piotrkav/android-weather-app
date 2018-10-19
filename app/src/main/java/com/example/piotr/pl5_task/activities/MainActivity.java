package com.example.piotr.pl5_task.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.piotr.pl5_task.R;
import com.example.piotr.pl5_task.apixu.WeatherTask;
import com.example.piotr.pl5_task.entity.Weather;
import com.example.piotr.pl5_task.fragment.WeatherFragment;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private Weather weather;
    private final String WEATHER_FRAGMENT_TAG = "weatherFragment";
    @BindView(R.id.weather_loading_progress)
    public
    ProgressBar progressBar;
    @BindView(R.id.fragment_container)
    public
    FrameLayout container;

    private WeatherFragment weatherFragment;
    private String cityName;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachFragment();

//        try {
//            weather = new WeatherTask().execute("Gdansk").get();
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(weather.getForecast().toString());
//
}
    private void attachFragment() {
      //  if (!Util.isNetworkAvailable(this)) {
      //      errorMessageFragment = ErrorMessageFragment.newInstance(getResources().getString(R.string.check_your_internet_connection));
      //      getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, errorMessageFragment).commit();
      //  } else {
            weatherFragment = WeatherFragment.newInstance(cityName);
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, weatherFragment, WEATHER_FRAGMENT_TAG).
                    commit();
      //  }
    }
}
