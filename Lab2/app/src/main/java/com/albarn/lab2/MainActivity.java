package com.albarn.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView ansLabel=null;
    private EditText equationEditText=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ansLabel=findViewById(R.id.ansLabel);
        equationEditText =findViewById(R.id.equationEditText);
    }

    public void solve(View view) {
        String origin= equationEditText.getText().toString();
        StringBuilder plainTextBuilder=new StringBuilder();
        for(int i=0;i<origin.length();i++){
            if(origin.charAt(i)!=' '){
                plainTextBuilder.append(origin.charAt(i));
            }
        }
        String plainText=plainTextBuilder.toString();
        int indexA=plainText.indexOf("a=");
        int indexB=plainText.indexOf("b=");
        int indexC=plainText.indexOf("c=");
        try{
            double a=Double.parseDouble(plainText.substring(indexA+2,indexB));
            Log.i("albarn","a parsed");
            double b=Double.parseDouble(plainText.substring(indexB+2,indexC));
            Log.i("albarn","b parsed");
            double c=Double.parseDouble(plainText.substring(indexC+2));
            Log.i("albarn","c parsed");

            double d2=b*b-4*a*c;
            if(d2<0){
                ansLabel.setText(R.string.no_ans);
            }else if(d2==0){
                String message="answer: "+(-b/(2.0*a));
                ansLabel.setText(message);
            }else{
                double d=Math.sqrt(d2);
                String message="answer: "+(d-b)/(2.0*a)+"; "+(-d-b)/(2.0*a);
                ansLabel.setText(message);
            }
        }catch (Exception e){
            Log.e("albarn",e.getMessage());
            ansLabel.setText(R.string.parse_error_message);
        }
    }
}
