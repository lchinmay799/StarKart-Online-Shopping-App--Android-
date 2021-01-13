package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import java.util.*;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {
    Integer otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_payment);
    }
    public void generate(View v)
    {
        String price=getIntent().getStringExtra("price");
        Random random=new Random();
        otp=random.nextInt(999999 - 100000) + 100000;
       String message = "You have intiated a txn of INR "+price+" at STARKART PAYMENTS of CMM Bank. The OTP is " + otp+" . DON'T SHARE WITH ANYONE. BANK NEVER CALL TO VERIFY OTP.";
        //Intent intent=new Intent(getApplicationContext(),Login.class);
        //PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        String phoneNumber = com.example.online_shopping.MainActivity.db.getcontact();
//Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null,null);
        Toast.makeText(getApplicationContext(), "OTP Sent through SMS",
                Toast.LENGTH_LONG).show();
        String email=com.example.online_shopping.MainActivity.db.getmail();
        sendMail sm = new sendMail(this, email, " StarKart Payment OTP", message);
        sm.execute();
        EditText o=findViewById(R.id.otp);
        Toast.makeText(this,"OTP Sent through E-Mail and SMS",Toast.LENGTH_LONG).show();
    }

    public void thankyou(View v)
    {
        EditText o=findViewById(R.id.otp);
        Integer ot = Integer.parseInt(o.getText().toString());
        if (otp.toString().equals(ot.toString())) {
            int b = (int)(Math.random()*(15-1+1)+1);
                   String message = "Your Order has been placed by StarKart. Your Product will be delivered in "+b+" days. You will receive an SMS in each step. For further queries contact our Customer Care Service or customer.service.cs7@gmail.com. Thank you for placing the order in StarKart :) ";
                 //Intent intent=new Intent(getApplicationContext(),Login.class);
                //PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
               String phoneNumber = com.example.online_shopping.MainActivity.db.getcontact();
//Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
               sms.sendTextMessage(phoneNumber, null, message, null,null);
             String email=com.example.online_shopping.MainActivity.db.getmail();
              sendMail sm = new sendMail(this, email, "StarKart Order Confirmation", message);
              sm.execute();
            Intent intent = new Intent(this, thank.class);
            startActivity(intent);
        }
        else
        {
            TextView war=findViewById(R.id.warning);
            war.setText("You have entered Wrong OTP \n Re-enter the OTP");
        }
    }
}