package com.example.piotr.pl5_task.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.piotr.pl5_task.R;
import com.example.piotr.pl5_task.entity.Forecast;
import com.example.piotr.pl5_task.entity.Forecastday;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.AdapterView.OnItemClickListener;


class WeatherHolder extends RecyclerView.ViewHolder {

    Forecastday forecastDay;
    private OnItemClickListener listener;

    @BindView(R.id.ic_condition_item)
    ImageView conditionIcon;
    @BindView(R.id.date_item)
    TextView dateItem;
    @BindView(R.id.condition_item_txt)
    TextView conditionItem;
    @BindView(R.id.max_temp_item)
    TextView maxTempItem;
    @BindView(R.id.min_temp_item)
    TextView minTempItem;

    public WeatherHolder(final View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    // listener.onItemClick(itemView, getLayoutPosition());
                }
            }
        });
    }


    public void bindForecast(int position, Forecast forecast) {

        forecastDay = forecast.getForecastday().get(position);

        Picasso.get().load("http://".concat(forecastDay.getDay().getCondition().getIcon().substring(2, forecastDay.getDay().getCondition().getIcon().length()))).fit().centerCrop().into(conditionIcon);
        dateItem.setText((forecastDay.getDate()));
        conditionItem.setText(forecastDay.getDay().getCondition().getText());
        maxTempItem.setText(String.format("%s°", forecastDay.getDay().getMaxtempC()));
        minTempItem.setText(String.format("%s°", forecastDay.getDay().getMintempC()));
    }
}
