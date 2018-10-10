package com.albarn.sort;

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
    private static final String TAG="SortActivity";

    private TextView task2AnsLabel=null;
    private EditText arrayEditText=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        task2AnsLabel=(TextView)findViewById(R.id.task2AnsLabel);
        arrayEditText=(EditText)findViewById(R.id.arrayEditText);
    }

    public void sort(View view) {
        String origin= arrayEditText.getText().toString();
        StringBuilder plainTextBuilder=new StringBuilder();
        for(int i=0;i<origin.length();i++){
            if(origin.charAt(i)!=' '){
                plainTextBuilder.append(origin.charAt(i));
            }
        }
        String plainText=plainTextBuilder.toString();
        try {
            Pair<String,double[]> namedArray=ArrayParser.parseNamedArray(plainText);
            for(int i=0;i<namedArray.second.length;i++){
                for(int j=i+1;j<namedArray.second.length;j++){
                    if(namedArray.second[i]>namedArray.second[j]){
                        double z=namedArray.second[i];
                        namedArray.second[i]=namedArray.second[j];
                        namedArray.second[j]=z;
                    }
                }
            }
            StringBuilder ansBuilder=new StringBuilder();
            ansBuilder.append(namedArray.first);
            ansBuilder.append('=');
            ansBuilder.append(namedArray.second.length);
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
            Log.e(TAG,e.getMessage());
            String message="failed to parse array";
            task2AnsLabel.setText(message);
        }
    }
}
