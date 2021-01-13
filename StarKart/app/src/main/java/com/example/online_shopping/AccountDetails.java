package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AccountDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_account_details);
        String[] user=com.example.online_shopping.MainActivity.db.userdetails();
        TextView n=findViewById(R.id.uname);
        TextView c=findViewById(R.id.contact);
        TextView e=findViewById(R.id.email);
        TextView a=findViewById(R.id.address);
        n.setText("Name : "+user[0]);
        c.setText("Mobile Number : "+user[2]);
        e.setText("E - Mail : "+user[1]);
        if(user[3].equals("NULL"))
            a.setText("Address : "+"Address Not Added");
        else{
            String address=user[3]+"\n\n \t\t\t\t\t\t\t\t\t "+user[4]+"\n\n \t\t\t\t\t\t\t\t\t "+user[5]+"\n\n \t\t\t\t\t\t\t\t\t "+user[6];
            a.setText("Address : "+address);
        }
    }

    public void cart(View v)
    {
        Intent intent = new Intent(this,Cart.class);
        startActivity(intent);
    }
    public void orders(View v)
    {
        Intent intent = new Intent(this,Orders.class);
        startActivity(intent);
    }
}