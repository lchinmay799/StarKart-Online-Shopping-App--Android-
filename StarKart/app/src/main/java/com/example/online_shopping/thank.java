package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class thank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_thank);
    }
    public void customer(View v)
    {
        String phone="57575";
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" +phone));
        startActivity(intent);
    }

    public void buyproducts(View v)
    {
        Intent intent = new Intent(this,items.class);
        startActivity(intent);
    }
}