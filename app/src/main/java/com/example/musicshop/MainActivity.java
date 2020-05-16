package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerAraayList;
    ArrayAdapter spinnerAdapter;

    HashMap goodsMap;
    String goodsName;
    double price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerAraayList = new ArrayList();

        spinnerAraayList.add("music plate");
        spinnerAraayList.add("keyboard");
        spinnerAraayList.add("drums");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerAraayList); // this -> for this class || simple -> element || give ArrayList with elements
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // we install spinner dropdown
        spinner.setAdapter(spinnerAdapter); // install adapter for spinner

        goodsMap = new HashMap();
        goodsMap.put("music plate", 990.0);
        goodsMap.put("keyboard", 9990.0);
        goodsMap.put("drums", 5990.0);
    }

    public void increaseQuantity(View view) {
        quantity += 1;
        TextView increaseTextView = findViewById(R.id.quantityTextView);
        increaseTextView.setText(String.valueOf(quantity));
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(String.valueOf(quantity * price));
    }

    public void decreaseQuantity(View view) {
        if(quantity > 0){
            quantity -= 1;
            TextView increaseTextView = findViewById(R.id.quantityTextView);
            increaseTextView.setText(String.valueOf(quantity));
            TextView priceTextView = findViewById(R.id.priceTextView);
            priceTextView.setText(String.valueOf(quantity * price));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(String.valueOf(quantity * price));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
