package com.albarn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.albarn.equation.EquationActivity;
import com.albarn.equation.R;
import com.albarn.sort.SortActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}
