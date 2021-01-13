package com.example.online_shopping;



import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
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
    public void signup2(View v) {
        EditText name = findViewById(R.id.uname);
        EditText mail = findViewById(R.id.email);
        EditText contact = findViewById(R.id.contact);
        EditText password = findViewById(R.id.password);
        EditText cpassword = findViewById(R.id.confirmpassword);
        String n = name.getText().toString();
        String m = mail.getText().toString();
        if (isValid(m)) {
            Long c = Long.parseLong(contact.getText().toString());
            String p1 = password.getText().toString();
            String p2 = cpassword.getText().toString();
            if (p1.equals(p2)) {
                boolean res = com.example.online_shopping.MainActivity.db.signup(n, m, c, p1);
                if (res) {
                    Intent intent = new Intent(this, items.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Your Account alreay Exists \n Click on Sign In", Toast.LENGTH_SHORT).show();
                }

            } else {
                ImageView i = findViewById(R.id.image);
                i.setImageResource(R.drawable.wrong);
                TextView t = findViewById(R.id.wrong);
                t.setText("Passwords Don't Match");
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid E-Mail Address \n Enter a Valid E-Mail Address", Toast.LENGTH_SHORT).show();
        }
    }
}