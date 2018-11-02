package com.albarn.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.albarn.equation.R;

import java.util.ArrayList;
import java.util.Comparator;

public class AdapterActivity extends AppCompatActivity {

    public class RemovableListView extends ListView implements AdapterView.OnItemClickListener{

        public RemovableListView(Context context) {
            super(context);
            setOnItemClickListener(this);
        }

        void update(){
            ((ArrayAdapter<Integer>)getAdapter()).sort((a,b)->(a-b));
        }

        void add(Integer value){
            ((ArrayAdapter<Integer>)getAdapter()).add(value);
            update();
        }

        public void setIntegerAdapter(ArrayAdapter<Integer> adapter){
            super.setAdapter(adapter);
            update();
        }

        @Override
        public void setAdapter(ListAdapter adapter) { }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            parent.removeView(view);
            update();
        }
    }

    RemovableListView numbersListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        numbersListView=findViewById(R.id.numbersListView);
        ArrayList<Integer> numList=new ArrayList<>();
        for (int number:
                getResources().getIntArray(R.array.strange_numbers)) {
            numList.add(number);
        }
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(
                this, android.R.layout.simple_list_item_1, numList);
        numbersListView.setIntegerAdapter(arrayAdapter);
    }
}
