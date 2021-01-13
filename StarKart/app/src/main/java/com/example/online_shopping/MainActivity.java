package com.example.online_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int SEND_PERMISSION=1;
    private int RECEIVE_PERMISSION=2;
    public static Database db;
    ImageView slidingimage;
    int currentimageindex=0;
    TextView msg;

    int[] IMAGE_IDS = {R.drawable.headphone, R.drawable.laptop, R.drawable.mobile};
    int[] IMAGE_IDS2 = {R.drawable.headphone2, R.drawable.laptop2, R.drawable.mobile2};
    String[] msgs={"Amazing Offers"," On Laptop, Mobiles, Headphones"," And other Electronic Gadgets "," Available Only in STARKART"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        db=new Database(this);
        db.getWritableDatabase();
        db.insert();
        checkPermission(1);
        TextView tv = findViewById(R.id.textview);
        tv.setSelected(true);
        final Handler mHandler = new Handler();
        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();
            }
        };
        int delay = 1000; // delay for 1 sec.

        int period = 4000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);

    }

    public void requestpermissions(int p)
    {
        if(p==1)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)){
                new AlertDialog.Builder(this)
                        .setTitle("Permission Needed")
                        .setMessage("Permission needed to \n 1) Request for OTP \n 2) Read OTP")
                        .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.SEND_SMS},SEND_PERMISSION);
                            }
                        })
                        .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.SEND_SMS},SEND_PERMISSION);
            }

        }
        else if(p==2)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS)){
                new AlertDialog.Builder(this)
                        .setTitle("Permission Needed")
                        .setMessage("Permission needed to Read OTP")
                        .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.RECEIVE_SMS},RECEIVE_PERMISSION);
                            }
                        })
                        .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.SEND_SMS},RECEIVE_PERMISSION);
            }
        }
    }

    public boolean checkPermission(int p)
    {
        if(p==1) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                return true;
            else {
                requestpermissions(p);
            }
        }
        else if(p==2)
        {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS)== PackageManager.PERMISSION_GRANTED)
                return true;
            else
            {
                requestpermissions(p);
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==SEND_PERMISSION)
        {
            if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted to SMS",Toast.LENGTH_SHORT).show();
            else
            {
                Toast.makeText(this,"Permission Denied to Read SMS",Toast.LENGTH_SHORT).show();

            }
        }
        if(requestCode==RECEIVE_PERMISSION)
        {
            if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted to SMS",Toast.LENGTH_SHORT).show();
            else
            {
                Toast.makeText(this,"Permission Denied to Read SMS",Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void AnimateandSlideShow() {

        slidingimage = (ImageView)findViewById(R.id.marque1);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);
        slidingimage = (ImageView)findViewById(R.id.marque2);
        slidingimage.setImageResource(IMAGE_IDS2[currentimageindex%IMAGE_IDS.length]);
        msg=findViewById(R.id.textview);
        msg.setText(msgs[currentimageindex%msgs.length]);
        currentimageindex++;
    }
    public void login(View v)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void signup(View v)
    {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
    public void customercare(View v)
    {
        String phone="57575";
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" +phone));
        startActivity(intent);
    }
}

