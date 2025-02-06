package com.example.saksh_cipher;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Caesar_Cipher_Act_5 extends AppCompatActivity {
    Button bt1,bt2;
    EditText et1,et2,et3,et4;
    TextView tv3,tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_cipher_act5);

        bt1=findViewById(R.id.bt1);
        bt2=findViewById(R.id.bt2);
        et1=findViewById(R.id.et1);  // P.T - 1  enc
        et2=findViewById(R.id.et2);  // ShiftKey - 2  enc
        et3=findViewById(R.id.et3);  // P.T - 3  dec
        et4=findViewById(R.id.et4);  // ShiftKey - 4  dec
        tv3=findViewById(R.id.tv3);  // enc setText
        tv5=findViewById(R.id.tv5);  // dec setText

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.GREEN);
                String plainT = et1.getText().toString().trim();
                String ShiftKey = et2.getText().toString().trim();
                plainT = plainT.toUpperCase();
                ShiftKey = ShiftKey.toUpperCase();

                if (plainT.isEmpty()) {
                    et1.setError("Cannot be empty");
                    return; }
                if (ShiftKey.isEmpty()) {
                    et2.setError("Cannot be empty");
                    return; }
                int shiftKey_1 = Integer.parseInt(ShiftKey);
                String cipherText = "";
                int cipher[] = new int[plainT.length()];

                for(int i = 0; i < plainT.length(); i++) {
                    if(plainT.charAt(i) == ' ') {
                        cipherText += ' ';
                    } else {
                        cipher[i] = ((plainT.charAt(i) - 'A') + (shiftKey_1)) % 26; // Wrap around using modulo 26
                        int x = cipher[i] + 'A'; // Convert back to character
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
                String ShiftKey = et4.getText().toString().trim();
                cipherT = cipherT.toUpperCase();
                ShiftKey = ShiftKey.toUpperCase();

                if (cipherT.isEmpty()) {
                    et3.setError("Cannot be empty");
                    return; }
                if (ShiftKey.isEmpty()) {
                    et4.setError("Cannot be empty");
                    return; }
                int shiftKey_1 = Integer.parseInt(ShiftKey);
                String plainText = "";
                int cipher[] = new int[cipherT.length()];

                for(int i = 0; i < cipherT.length(); i++) {
                    if(cipherT.charAt(i) == ' ') {
                        plainText += ' ';
                    } else {
                        cipher[i] = ((cipherT.charAt(i) - 'A') - (shiftKey_1)) % 26; // Wrap around using modulo 26
                        int x;
                        if(cipher[i] < 0){
                            x = cipher[i] + 26 + 'A';
                        }
                        else{
                            x = cipher[i] + 'A'; // Convert back to character
                        }
                        plainText += (char)x;
                    }
                }
                tv5.setText(plainText);
            }
        });
    }
}