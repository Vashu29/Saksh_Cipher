package com.example.saksh_cipher;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Vernam_Cipher_Act_2 extends AppCompatActivity {
    Button bt1,bt2;
    EditText et1,et2,et3,et4;
    TextView tv3,tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vernam_cipher_act2);

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
                if(key.length() != plainT.length()){
                    et2.setError("length should be equal to Plain Text");
                    return;
                }

                String cipherText = "";
                int[] cipher = new int[key.length()];

                for (int i = 0; i < key.length(); i++) {
                    if (plainT.charAt(i) == ' ') {  // Check for space
                        cipherText += " ";  // Add space directly to the cipher text
                    } else {
                        cipher[i] = plainT.charAt(i) - 'A' + key.charAt(i) - 'A';
                        if (cipher[i] > 25) {
                            cipher[i] = cipher[i] - 26;
                        }
                        int x = cipher[i] + 'A';
                        cipherText += (char)x;
                    }
                }
                tv3.setText(cipherText);
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
                if(key.length() != cipherT.length()){
                    et4.setError("length should be equal to Plain Text");
                    return;
                }

                String plainText = "";
                int[] plain = new int[key.length()];

                for (int i = 0; i < key.length(); i++) {
                    if (cipherT.charAt(i) == ' ') {  // Check for space
                        plainText += " ";  // Add space directly to the plaintext
                    } else {
                        plain[i] = cipherT.charAt(i) - 'A' - (key.charAt(i) - 'A');
                        if (plain[i] < 0) {
                            plain[i] = plain[i] + 26;
                        }
                        int x = plain[i] + 'A';
                        plainText += (char)x;
                    }
                }
                tv5.setText(plainText);
            }
        });
    }
}




