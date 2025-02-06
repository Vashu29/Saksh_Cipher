package com.example.saksh_cipher;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VigenerCipher_Act_4 extends AppCompatActivity {
    Button bt1,bt2;
    EditText et1,et2,et3,et4;
    TextView tv3,tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigener_cipher_act4);

        bt1=findViewById(R.id.bt1);
        bt2=findViewById(R.id.bt2);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        tv3=findViewById(R.id.tv3);
        tv5=findViewById(R.id.tv5);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.GREEN);
                String plainT = et1.getText().toString().trim();
                String key = et2.getText().toString().trim();
                plainT = plainT.toUpperCase();
                key = key.toUpperCase();

                if (plainT.isEmpty()) {
                    et1.setError("Cannot be empty");
                    return; }
                if (key.isEmpty()) {
                    et2.setError("Cannot be empty");
                    return; }
                String Fkey = generateKey(plainT,key);
                String cipher_text="";

                for (int i = 0; i < plainT.length(); i++)
                {
                    if (plainT.charAt(i) == ' ') {  // Check for space
                        cipher_text += " ";  // Add space directly to the cipher text
                    } else{
                        int x = (plainT.charAt(i) + Fkey.charAt(i)) %26;
                        x += 'A';
                        cipher_text += (char)(x);
                    }
                }
                tv3.setText(cipher_text);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setBackgroundColor(Color.RED);
                String cipherT = et3.getText().toString().trim();
                String key = et4.getText().toString().trim();
                cipherT = cipherT.toUpperCase();
                key = key.toUpperCase();

                if (cipherT.isEmpty()) {
                    et3.setError("Cannot be empty");
                    return; }
                if (key.isEmpty()) {
                    et4.setError("Cannot be empty");
                    return; }

                String Fkey = generateKey(cipherT,key);
                String plainText="";

                for (int i = 0; i < cipherT.length(); i++)
                {
                    if (cipherT.charAt(i) == ' ') {  // Check for space
                        plainText += " ";  // Add space directly to the plaintext
                    } else{
                        int x = (cipherT.charAt(i) - Fkey.charAt(i)) %26;
                        x += 'A';
                        plainText += (char)(x);
                    }
                }
                tv5.setText(plainText);
            }
        });
    }

    static String generateKey(String str, String key)
    {
        int x = str.length();

        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key+=(key.charAt(i));
        }
        return key;
    }
}