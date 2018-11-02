package com.albarn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.albarn.async.AsyncActivity;
import com.albarn.equation.EquationActivity;
import com.albarn.equation.R;
import com.albarn.sort.SortActivity;
import com.albarn.web.WebActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        Log.d(getString(R.string.log_tag_lifecycle),"MainActivity.onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(getString(R.string.log_tag_lifecycle),"MainActivity.onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(getString(R.string.log_tag_lifecycle),"MainActivity.onResume");
        super.onResume();
        TextView header=findViewById(R.id.headerMain);
        String message=getString(R.string.header)+visited;
        header.setText(message);
    }

    @Override
    protected void onPause() {
        Log.d(getString(R.string.log_tag_lifecycle),"MainActivity.onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(getString(R.string.log_tag_lifecycle),"MainActivity.onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(getString(R.string.log_tag_lifecycle),"MainActivity.onDestroy");
        super.onDestroy();
    }

    private int visited=0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(getString(R.string.log_tag_lifecycle),"MainActivity.onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt(getString(R.string.header),visited);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(getString(R.string.log_tag_lifecycle),"MainActivity.onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        visited=savedInstanceState.getInt(getString(R.string.header));
        visited++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(getString(R.string.log_tag_lifecycle),"MainActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void solveEquation(View view) {
        Intent equation=new Intent(this, EquationActivity.class);
        startActivity(equation);
    }

    public void sortArray(View view) {
        Intent equation=new Intent(this, SortActivity.class);
        startActivity(equation);
    }

    public void runAsyncTask(View view) {
        Intent equation=new Intent(this, AsyncActivity.class);
        startActivity(equation);
    }

    public void webView(View view){
        Intent equation=new Intent(this, WebActivity.class);
        startActivity(equation);
    }

    public void sortWithAdapter(View view){
        /*
        Intent equation=new Intent(this, AdapterActivity.class);
        startActivity(equation);
        */
    }
}
