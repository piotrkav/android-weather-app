package com.example.piotr.pl5_task.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.piotr.pl5_task.R;
import com.example.piotr.pl5_task.consts.UtilsHelper;
import com.example.piotr.pl5_task.fragment.ErrorFragment;
import com.example.piotr.pl5_task.fragment.WeatherFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements ErrorFragment.OnRetryClickListener{

    private final String WEATHER_FRAGMENT_TAG = "weatherFragment";
    private final String ERROR_FRAGMENT_TAG = "errorFragment";

    @BindView(R.id.weather_loading_progress)
    public
    ProgressBar progressBar;
    @BindView(R.id.fragment_container)
    public
    FrameLayout container;

    private WeatherFragment weatherFragment;
    private ErrorFragment errorFragment;
    private String cityName = "Porto";

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
        if (UtilsHelper.isNetworkConnectionAvailable(getApplicationContext())) {
            weatherFragment = WeatherFragment.newInstance(cityName);
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, weatherFragment, WEATHER_FRAGMENT_TAG).
                    commit();
        } else {
            errorFragment = ErrorFragment.newInstance("No internet connection");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, errorFragment, ERROR_FRAGMENT_TAG).
                    commit();
        }

    }


    @Override
    public void onRetryClicked() {
        attachFragment();
    }
}
