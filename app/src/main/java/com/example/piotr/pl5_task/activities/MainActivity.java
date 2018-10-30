package com.example.piotr.pl5_task.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.piotr.pl5_task.R;
import com.example.piotr.pl5_task.adpater.WeatherFragmentPageAdapter;
import com.example.piotr.pl5_task.fragment.ErrorFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ErrorFragment.OnRetryClickListener {

    private final String WEATHER_FRAGMENT_TAG = "weatherFragment";
    private final String ERROR_FRAGMENT_TAG = "errorFragment";

    @BindView(R.id.weather_loading_progress)
    public
    ProgressBar progressBar;

    @BindView(R.id.viewpager)
    public
    ViewPager viewPager;

    @BindView(R.id.sliding_tabs)
    public
    TabLayout tabLayout;


    //   @BindView(R.id.fragment_container)
    //   public
    //   FrameLayout container;

    //   private WeatherFragment weatherFragment;
    //   private ErrorFragment errorFragment;
    private String cityName = "Porto";

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager.setAdapter(new WeatherFragmentPageAdapter(getSupportFragmentManager(),
                MainActivity.this));
        tabLayout.setupWithViewPager(viewPager);
    }
//
//    private void attachFragment() {
//        if (UtilsHelper.isNetworkConnectionAvailable(getApplicationContext())) {
//            weatherFragment = WeatherFragment.newInstance(cityName);
//            getSupportFragmentManager().beginTransaction().
//                    replace(R.id.fragment_container, weatherFragment, WEATHER_FRAGMENT_TAG).
//                    commit();
//        } else {
//            errorFragment = ErrorFragment.newInstance("No internet connection");
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, errorFragment, ERROR_FRAGMENT_TAG).
//                    commit();
//        }
//
//    }


    @Override
    public void onRetryClicked() {
        //attachFragment();
    }
}
