package com.albarn.jsoup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.albarn.equation.R;

import java.util.concurrent.ExecutionException;

public class WeatherActivity extends AppCompatActivity {

    private ListView weatherForecastView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        weatherForecastView=findViewById(R.id.weatherForecastListView);
    }

    public void updateWeatherForecast(View view){
        //create weather forecast collector and execute it
        WeatherCollector weather=new WeatherCollector();
        weather.execute();

        //fill adapter with acquired data
        ArrayAdapter<String> adapter= null;
        try {
            adapter = new ArrayAdapter<String>(
                    this,android.R.layout.simple_list_item_1,weather.get()
            );
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        weatherForecastView.setAdapter(adapter);
    }
}
