package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText txt_pNumber,txt_msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_pNumber= (EditText)findViewById(R.id.txt_phone_Number);
        txt_msg=(EditText)findViewById(R.id.txt_messege);

    }


    public void btn_send(View view) {
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissionCheck== PackageManager.PERMISSION_GRANTED)
        {
            MyMessege();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);

        }


    }


    private void MyMessege() {
        String phoneNumber=txt_pNumber.getText().toString().trim();
        String Messege=txt_msg.getText().toString().trim();
        if(!txt_pNumber.getText().toString().equals("") || !txt_msg.getText().toString().equals("")){
         SmsManager smsManager=SmsManager.getDefault();
         smsManager.sendTextMessage(phoneNumber,null,Messege,null,null);
        Toast.makeText(this,"Messege sent",Toast.LENGTH_SHORT).show();
        }
        else
        {
          Toast.makeText(this,"enter valid no and messege",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        { case 0:
             if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
             {
                 MyMessege();
             }
             else
             { Toast.makeText(this,"You don,t have permission ",Toast.LENGTH_SHORT).show();

             }

        }
    }
}