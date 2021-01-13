package com.example.online_shopping;


import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import android.app.PendingIntent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.StrictMode;
import java.util.Random;
import java.util.regex.Pattern;


public class Login extends AppCompatActivity {
    Integer otp;
    String res;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public void getotp(View v) {
        EditText mail = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        String m = mail.getText().toString();
        if (isValid(m)) {
            String p = password.getText().toString();
            res = com.example.online_shopping.MainActivity.db.login(m, p);
            if ((res != "False") && (res != "no")) {

                TextView t = findViewById(R.id.warning);
                t.setText(" ");
                ImageView i = findViewById(R.id.image);
                i.setImageResource(R.drawable.login);
                Random random = new Random();
                otp = random.nextInt(999999 - 100000) + 100000;
                String phoneNumber = com.example.online_shopping.MainActivity.db.getcontact();
                EditText o = findViewById(R.id.otp);

                String message = "You have tried to Login. The OTP is " + otp;
                //Intent intent=new Intent(getApplicationContext(),Login.class);
                //PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

//Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNumber, null, message, null, null);
                Toast.makeText(getApplicationContext(), "OTP Sent through SMS",
                        Toast.LENGTH_LONG).show();
                String email = com.example.online_shopping.MainActivity.db.getmail();
                sendMail sm = new sendMail(this, email, " StarKart User Login", message);
                //Executing sendmail to send email
                sm.execute();
                Toast.makeText(this, "OTP Sent through E-Mail and SMS", Toast.LENGTH_LONG).show();


            } else if (res == "False") {
                Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                TextView t = findViewById(R.id.warning);
                t.setText("Wrong Password \n Login Unsuccessful...");
                ImageView i = findViewById(R.id.image);
                i.setImageResource(R.drawable.wrong);
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "You don't have an Account in Starkart \n Click on Sign Up Button", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid E-Mail Address \n Enter a Valid E-Mail Address", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View v) {
        EditText o = findViewById(R.id.otp);
        Integer ot = Integer.parseInt(o.getText().toString());
        if (otp.toString().equals(ot.toString())) {
            if ((res != "False") && (res != "no")) {
                Toast.makeText(getApplicationContext(), "Welcome " + res, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, items.class);
                startActivity(intent);
            } else if (res == "False") {
                Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                TextView t = findViewById(R.id.warning);
                t.setText("Wrong Password \n Login Unsuccessful...");
                ImageView i = findViewById(R.id.image);
                i.setImageResource(R.drawable.wrong);
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "You don't have an Account in Starkart \n Click on Sign Up Button", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            TextView t = findViewById(R.id.warning);
            t.setText("OTP doesn't Match \n Try Again ...");
            ImageView i = findViewById(R.id.image);
            i.setImageResource(R.drawable.wrong);
            Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }
}