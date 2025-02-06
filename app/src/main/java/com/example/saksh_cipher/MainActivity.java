package com.example.saksh_cipher;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Main
    String[] cipher = {"Vernam Cipher", "Playfair Cipher", "Vigener Cipher", "Caesar Cipher", "Hill Cipher(2*2)","Hill Cipher(3*3)"};
    ImageView im;
    // Main
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    EditText number;
    EditText message;
    Button send, btnlast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Main
        im = findViewById(R.id.im);
        im.setImageResource(R.drawable.img5);

        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        im.startAnimation(scaleAnimation);

        ListView lv = findViewById(R.id.lv);
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cipher);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MainActivity", "ListView item clicked: " + i);  // Add this line
                Toast.makeText(MainActivity.this, "Clicked: " + cipher[i], Toast.LENGTH_SHORT).show();
                String str = lv.getAdapter().getItem(i).toString();
                if(i==0) {
                    Intent intent = new Intent(getApplicationContext(), Vernam_Cipher_Act_2.class);
                    startActivity(intent);
                } else if (i==1) {
                    Intent intent = new Intent(getApplicationContext(), PlayFairCipher_Act_3.class);
                    startActivity(intent);
                } else if (i==2) {
                    Intent intent = new Intent(getApplicationContext(), VigenerCipher_Act_4.class);
                    startActivity(intent);
                } else if (i==3) {
                    Intent intent = new Intent(getApplicationContext(), Caesar_Cipher_Act_5.class);
                    startActivity(intent);
                } else if (i==4) {
                    Intent intent = new Intent(getApplicationContext(), Hill_Cipher_Act_6.class);
                    startActivity(intent);
                } else if (i==5) {
                    Intent intent = new Intent(getApplicationContext(), Hill_Cipher_3.class);
                    startActivity(intent);
                }
            }
        });
        // Main
        btnlast = findViewById(R.id.btnlast);
        btnlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Note_Pad_2.class);
                startActivity(intent);
            }
        });
        number = findViewById(R.id.ed1);
        message = findViewById(R.id.ed2);
        send = findViewById(R.id.send);
        send.setEnabled(false);
        if(checkPermission(android.Manifest.permission.SEND_SMS)){
            send.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }
    public void onSend(View v){
        String phoneNumber = number.getText().toString();
        String smsMessage = message.getText().toString();
        if(phoneNumber.isEmpty() || smsMessage.isEmpty()){
            return;
        }
        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber,null,smsMessage,null,null);
            Toast.makeText(this, "message sent",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "message sent",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
