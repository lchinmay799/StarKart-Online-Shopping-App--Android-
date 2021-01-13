package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.ImageButton;

public class items extends AppCompatActivity {
    Integer index=0;
    String price="19999";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_items);
    }
    public void getdetails(View v)
    {
                index=0;
                switch (v.getId()) {
                    case R.id.one:
                        index = 0;
                        break;
                    case R.id.two:
                        index = 1;
                        break;
                    case R.id.three:
                        index = 2;
                        break;
                    case R.id.four:
                        index = 3;
                        break;
                    case R.id.five:
                        index = 4;
                        break;
                    case R.id.six:
                        index = 5;
                        break;
                    case R.id.seven:
                        index = 6;
                        break;
                    case R.id.eight:
                        index = 7;
                        break;
                    case R.id.nine:
                        index = 8;
                        break;
                    case R.id.ten:
                        index = 9;
                        break;
                    case R.id.eleven:
                        index = 10;
                        break;
                    case R.id.twelve:
                        index = 11;
                        break;
                }
        price=com.example.online_shopping.MainActivity.db.getPrice(index+1);
        Intent intent = new Intent(this, Item_Details.class);
        intent.putExtra("index", index.toString());
        intent.putExtra("price", price);
        startActivity(intent);
    }
    public void cart(View v)
    {
        Intent intent = new Intent(this,Cart.class);
        startActivity(intent);
    }
    public void account(View v)
    {
        Intent intent = new Intent(this,AccountDetails.class);
        startActivity(intent);

    }
    public void orders(View v)
    {
        Intent intent = new Intent(this,Orders.class);
        startActivity(intent);

    }
}