package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.Toast;

public class Cart extends AppCompatActivity {
    Integer price;
    int[] IMAGE_IDS = {R.drawable.boat, R.drawable.jbl, R.drawable.sannheiser,R.drawable.sony,
            R.drawable.hp,R.drawable.asus,R.drawable.lenovo,R.drawable.acer,
            R.drawable.apple,R.drawable.samsung,R.drawable.rog,R.drawable.vivo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart);
        ArrayList<ArrayList<String>> item=com.example.online_shopping.MainActivity.db.cartdetails();
        int l=item.size();
        System.out.println(l);
        if(l==0)
        {
            Button b=findViewById(R.id.buy);
            b.setVisibility(View.GONE);
            Button b1=findViewById(R.id.remove);
            b1.setVisibility(View.GONE);
            TextView t1=findViewById(R.id.total);
            t1.setVisibility(View.GONE);
            TextView t=findViewById(R.id.noitem);
            t.setText("No Items in the Cart ");
        }
        else {
            Button b=findViewById(R.id.buy);
            b.setVisibility(View.VISIBLE);
            Button b1=findViewById(R.id.remove);
            b1.setVisibility(View.VISIBLE);
            TextView t1=findViewById(R.id.total);
            t1.setVisibility(View.VISIBLE);
            price=0;
            for(int i=0;i<l;i++)
                price+=Integer.parseInt(item.get(i).get(2));
            t1.setText("Total : Rs "+price.toString());
            ListView li = findViewById(R.id.listview);
            CustomAdapter ca = new CustomAdapter(getApplicationContext(), item, IMAGE_IDS);
            li.setAdapter(ca);
        }
    }
    public void buyall(View v)
    {
        int[] cartid=com.example.online_shopping.MainActivity.db.getcart();
        System.out.println("len"+" "+cartid.length);
        for(int i=0;i<cartid.length;i++) {
            System.out.println("product"+" "+cartid[i]);
            com.example.online_shopping.MainActivity.db.purchase(cartid[i]);
        }
        com.example.online_shopping.MainActivity.db.emptycart();
        Intent intent = new Intent(this,Address.class);
        intent.putExtra("price", price.toString());
        startActivity(intent);
    }

    public void removeall(View v)
    {
        com.example.online_shopping.MainActivity.db.emptycart();
        Toast.makeText(this,"Removed All Items from the Cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,Cart.class);
        startActivity(intent);
    }
}