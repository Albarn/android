package com.albarn.jsoup;

import android.os.AsyncTask;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

class WeatherCollector extends AsyncTask<Void,Void,String[]>{

    private static final String TAG="com.albarn.calculation";
    WeatherCollector(){
       Log.i(TAG,"weather instance created");
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        try {
            String TARGET="https://weather.com/weather/hourbyhour/l/UPXX0014:1:UP";

            //create url instance for jsoup
            URL source=new URL(TARGET);

            //get html document
            Document doc=Jsoup.parse(source, 10000);
            Log.i(TAG,"got html content");

            //get columns in result table on web page
            Elements timeColumn=doc.getElementsByClass("dsx-date");
            Elements descriptionColumn=doc.getElementsByClass("description");
            Elements tempColumn=doc.getElementsByClass("temp");
            Log.i(TAG,"start parsing");

            //convert weather forecast information to string array
            String[] ans=new String[timeColumn.size()];
            for(int i=0;i<timeColumn.size();i++){

                //parse each line according to html document structure
                String row=timeColumn.get(i).html();
                row+=" ";
                if(i==0){
                    Log.i(TAG,"time parse succeed");
                }
                row+=descriptionColumn.get(i+1).child(0).html();
                row+=" ";
                if(i==0){
                    Log.i(TAG,"description parse succeed");
                }
                String temp=tempColumn.get(i+1).child(0).html();
                //get fahrenheit temperature
                int f=Integer.parseInt(temp.substring(0,temp.indexOf('<')));
                //convert it to celsius
                double cDouble=((f-32)*5.0/9.0);

                //get the closest integer bound
                int cInt=(int)cDouble;
                if(cDouble>0){
                    cInt+=(cDouble-cInt)>=0.5?1:0;
                }else{
                    cInt-=(cDouble-cInt)<=-0.5?1:0;
                }

                //add celsius symbol
                row+=cInt+"°C";
                if(i==0){
                    Log.i(TAG,"temperature parse succeed");
                }

                //record row into array
                ans[i]=row;
            }
            return ans;
        } catch (IOException e) {
            Log.e(TAG,e.getMessage());
            return new String[0];
        }
    }

}
