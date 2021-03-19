package com.example.phonecallmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt_phoneNumber;

    Button btn_call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_phoneNumber=findViewById(R.id.txt_number);
        btn_call=findViewById(R.id.btncall);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_CALL);
                String number=txt_phoneNumber.getText().toString();

                if(number.trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please Enter Your Number",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    intent.setData(Uri.parse("tel:"+number));
                }

                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(MainActivity.this,"Please grant permission",Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}