package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
    EditText userNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.userNameEditText);

        createSpinner();
        createHashMap();
    }

    public void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerAraayList = new ArrayList();

        spinnerAraayList.add("music plate");
        spinnerAraayList.add("keyboard");
        spinnerAraayList.add("drums");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerAraayList); // this -> for this class || simple -> element || give ArrayList with elements
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // we install spinner dropdown
        spinner.setAdapter(spinnerAdapter); // install adapter for spinner
    }

    public void createHashMap(){
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

        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        TextView informationTextView = findViewById(R.id.informationTextView);
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        switch (goodsName){
            case ("keyboard"):
//                quantityTextView.setText("0");
//                quantity = 0;
                goodsImageView.setImageResource(R.drawable.keyboard);
                informationTextView.setText("New Bösendorfer 225 Grand Piano\nThe 225, like the bigger brother 290 Imperial – has been crafted dedicating to the tradition of Viennese piano building.");
                break;
            case ("drums"):
//                quantityTextView.setText("0");
//                quantity = 0;
                goodsImageView.setImageResource(R.drawable.drums);
                informationTextView.setText("Yamaha - Stage Custom Birch \n6-Pc Drum Set (10,12,14,16, Sn, 22) \nw/Hardware - Raven Black 100% Birch shell drums, 6-ply\n1.6mm steel hoops");
                break;
            case ("music plate"):
//                quantityTextView.setText("0");
//                quantity = 0;
                goodsImageView.setImageResource(R.drawable.music_plate);
                informationTextView.setText("Bohemian Rhapsody \nPiano, Vocal and Guitar Piano-Vocal-Guitar Songbook. product type Book Only. A product from Hal Leonard");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();

        order.userName = userNameEditText.getText().toString();
        // Log.d("userName", order.userName); // for checking work it or not work in debug
        order.goodsName = goodsName;
        // Log.d("goodsName", order.goodsName);
        order.quantity = quantity;
        // Log.d("quantity", String.valueOf(order.quantity));
        order.orderPrice = quantity * price;
        // Log.d("userName", String.valueOf(order.orderPrice));
        order.price = price;
        // Log.d("price", String.valueOf(order.price));

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsNameForIntent", order.goodsName);
        orderIntent.putExtra("quantityForIntent", order.quantity);
        orderIntent.putExtra("orderPriceForIntent", order.orderPrice);
        orderIntent.putExtra("unitPriceForIntent", order.price);

        startActivity(orderIntent);
    }
}
