package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address extends AppCompatActivity {
    String[] user;
    EditText n,c,e,s,ci,st,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_address);
        user=com.example.online_shopping.MainActivity.db.userdetails();
        n=findViewById(R.id.uname);
        c=findViewById(R.id.contact);
        e=findViewById(R.id.email);
        s=findViewById(R.id.street);
        ci=findViewById(R.id.city);
        st=findViewById(R.id.state);
        p=findViewById(R.id.pincode);
        n.setText(user[0]);
        c.setText(user[1]);
        e.setText(user[2]);
        if( !user[3].equals("NULL"))
        {
            s.setText(user[3]);
            ci.setText(user[4]);
            st.setText(user[5]);
            p.setText(user[6]);
        }
    }
    public static boolean isValidPinCode(String pinCode)
    {

        String regex
                = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";

        Pattern p = Pattern.compile(regex);

        if (pinCode == null) {
            return false;
        }


        Matcher m = p.matcher(pinCode);


        return m.matches();
    }
    public void buynow(View v)
    {
        String pin=p.getText().toString();
        if (isValidPinCode(pin)) {
            String[] address = new String[4];
            address[0] = s.getText().toString();
            address[1] = ci.getText().toString();
            address[2] = st.getText().toString();
            address[3] = p.getText().toString();
            com.example.online_shopping.MainActivity.db.addAddress(address);
            String price = getIntent().getStringExtra("price");
            Intent intent = new Intent(this, Payment.class);
            intent.putExtra("price", price);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid Pin Code \n Enter a Valid Pin Code", Toast.LENGTH_SHORT).show();

        }
    }
}