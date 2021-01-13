package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    int[] IMAGE_IDS = {R.drawable.boat, R.drawable.jbl, R.drawable.sannheiser,R.drawable.sony,
            R.drawable.hp,R.drawable.asus,R.drawable.lenovo,R.drawable.acer,
            R.drawable.apple,R.drawable.samsung,R.drawable.rog,R.drawable.vivo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_orders);
        ArrayList<ArrayList<String>> item=com.example.online_shopping.MainActivity.db.orderdetails();
        int l=item.size();
        if(l==0)
        {
            TextView t=findViewById(R.id.noitem);
            t.setText("You Haven't Purchased Any Items ...  ");
        }
        else {
            ListView li = findViewById(R.id.listview);
            CustomAdapter2 ca2 = new CustomAdapter2(getApplicationContext(), item, IMAGE_IDS);
            li.setAdapter(ca2);
        }
    }
    }