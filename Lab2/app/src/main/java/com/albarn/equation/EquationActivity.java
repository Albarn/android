package com.albarn.equation;

import android.os.PersistableBundle;
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
    protected void onStart() {
        Log.d(getString(R.string.log_tag_lifecycle),"EquationActivity.onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(getString(R.string.log_tag_lifecycle),"EquationActivity.onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(getString(R.string.log_tag_lifecycle),"EquationActivity.onResume");
        super.onResume();
        TextView header=findViewById(R.id.headerEquation);
        String message=getString(R.string.header)+visited;
        header.setText(message);
    }

    @Override
    protected void onPause() {
        Log.d(getString(R.string.log_tag_lifecycle),"EquationActivity.onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(getString(R.string.log_tag_lifecycle),"EquationActivity.onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(getString(R.string.log_tag_lifecycle),"EquationActivity.onDestroy");
        super.onDestroy();
    }

    private int visited=0;
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(getString(R.string.log_tag_lifecycle),"EquationActivity.onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt(getString(R.string.header),visited);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(getString(R.string.log_tag_lifecycle),"EquationActivity.onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        visited=savedInstanceState.getInt(getString(R.string.header));
        visited++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(getString(R.string.log_tag_lifecycle),"EquationActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_linear);

        //get controls for answer label & equation text box
        ansLabel=findViewById(R.id.ansLabel);
        equationEditText =findViewById(R.id.equationEditText);
    }

    //solve equation button handler
    public void solve(View view) {

        //convert origin string to plain string without spaces
        String origin= equationEditText.getText().toString();
        StringBuilder plainTextBuilder=new StringBuilder();
        for(int i=0;i<origin.length();i++){
            if(origin.charAt(i)!=' '){
                plainTextBuilder.append(origin.charAt(i));
            }
        }
        String plainText=plainTextBuilder.toString();

        //parse equation and show the answer
        try{
            ansLabel.setText(EquationParser.parseEquation(plainText));
        }catch (Exception e){

            //if exception during parse, show it to user
            //and show details in log
            Log.e(getString(R.string.log_tag_calculation),e.getMessage());
            ansLabel.setText(R.string.parse_error_message);
        }
    }
}
