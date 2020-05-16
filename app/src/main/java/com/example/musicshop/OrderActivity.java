package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class OrderActivity extends AppCompatActivity {

    String[] address = {"oleynik624@gmail.com"};
    String subject = "Order from Music Shop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntent");
        int quantity = receivedOrderIntent.getIntExtra("quantityForIntent", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceForIntent", 0);
        double unitPrice = receivedOrderIntent.getDoubleExtra("unitPriceForIntent", 0);

        emailText = "User Name: " + userName +"\n"
                + "Goods Name: " + goodsName +"\n"
                + "Quantity: " + quantity +"\n"
                + "Order Price: " + orderPrice +"\n"
                + "Unit Price: " + unitPrice +"\n";

        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView goodsNameTextView = findViewById(R.id.goodsNameTextView);
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        TextView priceTextView = findViewById(R.id.UnitPriceTextView);
        TextView orderPriceTextView = findViewById(R.id.orderPriceTextView);

        userNameTextView.setText(userName);
        goodsNameTextView.setText(goodsName);
        quantityTextView.setText(String.valueOf(quantity));
        priceTextView.setText(String.valueOf(orderPrice));
        orderPriceTextView.setText(String.valueOf(unitPrice));


    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND); // sendto only for mail
        //intent.setData(Uri.parse("mailto:")); // for mail
        intent.setType("*/*"); // message for each application
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }


}
