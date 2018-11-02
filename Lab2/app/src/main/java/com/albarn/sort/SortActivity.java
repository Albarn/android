package com.albarn.sort;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.albarn.ParseException;
import com.albarn.equation.R;

import java.util.ArrayList;

public class SortActivity extends AppCompatActivity {

    private TextView task2AnsLabel=null;
    private EditText arrayEditText=null;

    @Override
    protected void onStart() {
        Log.d(getString(R.string.log_tag_lifecycle),"SortActivity.onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(getString(R.string.log_tag_lifecycle),"SortActivity.onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(getString(R.string.log_tag_lifecycle),"SortActivity.onResume");
        super.onResume();
        TextView header=findViewById(R.id.headerSort);
        String message=getString(R.string.header)+visited;
        header.setText(message);
    }

    @Override
    protected void onPause() {
        Log.d(getString(R.string.log_tag_lifecycle),"SortActivity.onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(getString(R.string.log_tag_lifecycle),"SortActivity.onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(getString(R.string.log_tag_lifecycle),"SortActivity.onDestroy");
        super.onDestroy();
    }

    private int visited=0;
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(getString(R.string.log_tag_lifecycle),"SortActivity.onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt(getString(R.string.header),visited);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(getString(R.string.log_tag_lifecycle),"SortActivity.onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        visited=savedInstanceState.getInt(getString(R.string.header));
        visited++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(getString(R.string.log_tag_lifecycle),"SortActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        task2AnsLabel=(TextView)findViewById(R.id.task2AnsLabel);
        arrayEditText=(EditText)findViewById(R.id.arrayEditText);
    }

    //sort array, that user wrote in arrayEditText
    //and show it in task2AnsLabel
    public void sort(View view) {

        //get origin text and remove spaces
        String origin= arrayEditText.getText().toString();
        StringBuilder plainTextBuilder=new StringBuilder();
        for(int i=0;i<origin.length();i++){
            if(origin.charAt(i)!=' '){
                plainTextBuilder.append(origin.charAt(i));
            }
        }
        String plainText=plainTextBuilder.toString();

        //then, parse plainText and get double array
        try {
            Pair<String,double[]> namedArray=ArrayParser.parseNamedArray(plainText);

            //make bubble sort of this
            for(int i=0;i<namedArray.second.length;i++){
                for(int j=i+1;j<namedArray.second.length;j++){
                    if(namedArray.second[i]>namedArray.second[j]){
                        double z=namedArray.second[i];
                        namedArray.second[i]=namedArray.second[j];
                        namedArray.second[j]=z;
                    }
                }
            }

            //build answer according to ArrayParser rules
            StringBuilder ansBuilder=new StringBuilder();

            //name
            ansBuilder.append(namedArray.first);
            ansBuilder.append('=');
            //then array size
            ansBuilder.append(namedArray.second.length);

            //after that list of decimal numbers
            ansBuilder.append('(');
            for(int i=0;i<namedArray.second.length;i++){
                ansBuilder.append(namedArray.second[i]);
                if(i<namedArray.second.length-1){
                    ansBuilder.append(',');
                }else{
                    ansBuilder.append(')');
                }
            }
            task2AnsLabel.setText(ansBuilder.toString());
        } catch (ParseException e) {
            Log.e(getString(R.string.log_tag_calculation),e.getMessage());
            String message="failed to parse array";
            task2AnsLabel.setText(message);
        }
    }
}
