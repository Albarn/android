package com.albarn.equation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EquationActivity extends AppCompatActivity {

    private TextView ansLabel=null;
    private EditText equationEditText=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_linear);
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
        try{
            ansLabel.setText(EquationParser.parseEquation(plainText));
        }catch (Exception e){
            Log.e("albarn",e.getMessage());
            ansLabel.setText(R.string.parse_error_message);
        }
    }
}
