package com.example.saksh_cipher;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Hill_Cipher_3 extends AppCompatActivity {
    Button bt1, bt2;
    EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, et11, et12, et13, et14, et15, et16, et17, et18, et19, et20;
    TextView tv4, tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_cipher3);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);
        et8 = findViewById(R.id.et8);
        et9 = findViewById(R.id.et9);
        et10 = findViewById(R.id.et10);
        et11 = findViewById(R.id.et11);
        et12 = findViewById(R.id.et12);
        et13 = findViewById(R.id.et13);
        et14 = findViewById(R.id.et14);
        et15 = findViewById(R.id.et15);
        et16 = findViewById(R.id.et16);
        et17 = findViewById(R.id.et17);
        et18 = findViewById(R.id.et18);
        et19 = findViewById(R.id.et19);
        et20 = findViewById(R.id.et20);
        tv4 = findViewById(R.id.tv4);
        tv7 = findViewById(R.id.tv7);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.GREEN);

                String plainT = et1.getText().toString().trim().toUpperCase();

                if (plainT.isEmpty()) {
                    et1.setError("Cannot be empty");
                    return;
                }

                while (plainT.length() % 3 != 0) {
                    plainT += "A";
                }

                EditText[] keyInputs = {et2, et3, et4, et5, et6, et7, et8, et9, et10};
                for (EditText input : keyInputs) {
                    if (input.getText().toString().trim().isEmpty()) {
                        input.setError("Cannot be empty");
                        return;
                    }
                }

                try {
                    int[][] keyMatrix = new int[3][3];
                    keyMatrix[0][0] = Integer.parseInt(et2.getText().toString());
                    keyMatrix[0][1] = Integer.parseInt(et3.getText().toString());
                    keyMatrix[0][2] = Integer.parseInt(et4.getText().toString());
                    keyMatrix[1][0] = Integer.parseInt(et5.getText().toString());
                    keyMatrix[1][1] = Integer.parseInt(et6.getText().toString());
                    keyMatrix[1][2] = Integer.parseInt(et7.getText().toString());
                    keyMatrix[2][0] = Integer.parseInt(et8.getText().toString());
                    keyMatrix[2][1] = Integer.parseInt(et9.getText().toString());
                    keyMatrix[2][2] = Integer.parseInt(et10.getText().toString());

                    // Perform determinant check here if required.

                    StringBuilder cipherText = new StringBuilder();

                    for (int i = 0; i < plainT.length(); i += 3) {
                        int[] vector = {
                                plainT.charAt(i) - 'A',
                                plainT.charAt(i + 1) - 'A',
                                plainT.charAt(i + 2) - 'A'
                        };

                        int[] encryptedVector = new int[3];
                        encryptedVector[0] = (keyMatrix[0][0] * vector[0] + keyMatrix[0][1] * vector[1] + keyMatrix[0][2] * vector[2]) % 26;
                        encryptedVector[1] = (keyMatrix[1][0] * vector[0] + keyMatrix[1][1] * vector[1] + keyMatrix[1][2] * vector[2]) % 26;
                        encryptedVector[2] = (keyMatrix[2][0] * vector[0] + keyMatrix[2][1] * vector[1] + keyMatrix[2][2] * vector[2]) % 26;

                        for(int j=0;j<3;j++){
                            if(encryptedVector[j] < 0){
                                encryptedVector[j] += 26;
                            }
                        }

                        cipherText.append((char) (encryptedVector[0] + 'A'));
                        cipherText.append((char) (encryptedVector[1] + 'A'));
                        cipherText.append((char) (encryptedVector[2] + 'A'));
                    }

                    tv4.setText(cipherText.toString());
                    Toast.makeText(Hill_Cipher_3.this, "Encryption Successful", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(Hill_Cipher_3.this, "Invalid input in key matrix", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setBackgroundColor(Color.RED);

                String cipherT = et11.getText().toString().trim().toUpperCase().replace(" ", "X");

                if (cipherT.isEmpty()) {
                    et11.setError("Cannot be empty");
                    return;
                }

                if (cipherT.length() % 3 != 0) {
                    cipherT += "A".repeat(3 - cipherT.length() % 3);
                }

                EditText[] keyInputs = {et12, et13, et14, et15, et16, et17, et18, et19, et20};
                for (EditText input : keyInputs) {
                    if (input.getText().toString().trim().isEmpty()) {
                        input.setError("Cannot be empty");
                        return;
                    }
                }

                try {
                    int[][] keyMatrix = new int[3][3];
                    keyMatrix[0][0] = Integer.parseInt(et12.getText().toString());
                    keyMatrix[0][1] = Integer.parseInt(et13.getText().toString());
                    keyMatrix[0][2] = Integer.parseInt(et14.getText().toString());
                    keyMatrix[1][0] = Integer.parseInt(et15.getText().toString());
                    keyMatrix[1][1] = Integer.parseInt(et16.getText().toString());
                    keyMatrix[1][2] = Integer.parseInt(et17.getText().toString());
                    keyMatrix[2][0] = Integer.parseInt(et18.getText().toString());
                    keyMatrix[2][1] = Integer.parseInt(et19.getText().toString());
                    keyMatrix[2][2] = Integer.parseInt(et20.getText().toString());

                    // Step 1: Calculate the determinant
                    int d = keyMatrix[0][0] * (keyMatrix[1][1] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][1])
                            - keyMatrix[0][1] * (keyMatrix[1][0] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][0])
                            + keyMatrix[0][2] * (keyMatrix[1][0] * keyMatrix[2][1] - keyMatrix[1][1] * keyMatrix[2][0]);
                    d = d % 26;
                    if (d < 0) {
                        d += 26;
                    }

                    // Step 2: Find the modular inverse of the determinant (dinv)
                    int dinv = -1;
                    for (int i = 1; i < 26; i++) {
                        if ((d * i) % 26 == 1) {
                            dinv = i;
                            break;
                        }
                    }

                    if (dinv == -1) {
                        Toast.makeText(Hill_Cipher_3.this, "Determinant has no modular inverse; decryption failed", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Step 3: Calculate the adjoint of the key matrix
                    int[][] adjMat = new int[3][3];
                    adjMat[0][0] = (keyMatrix[1][1] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][1]);
                    adjMat[0][1] = -(keyMatrix[0][1] * keyMatrix[2][2] - keyMatrix[0][2] * keyMatrix[2][1]);
                    adjMat[0][2] = (keyMatrix[0][1] * keyMatrix[1][2] - keyMatrix[0][2] * keyMatrix[1][1]);

                    adjMat[1][0] = -(keyMatrix[1][0] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][0]);
                    adjMat[1][1] = (keyMatrix[0][0] * keyMatrix[2][2] - keyMatrix[0][2] * keyMatrix[2][0]);
                    adjMat[1][2] = -(keyMatrix[0][0] * keyMatrix[1][2] - keyMatrix[0][2] * keyMatrix[1][0]);

                    adjMat[2][0] = (keyMatrix[1][0] * keyMatrix[2][1] - keyMatrix[1][1] * keyMatrix[2][0]);
                    adjMat[2][1] = -(keyMatrix[0][0] * keyMatrix[2][1] - keyMatrix[0][1] * keyMatrix[2][0]);
                    adjMat[2][2] = (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]);

                    // Make all adjoint elements positive mod 26
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            adjMat[i][j] = (adjMat[i][j] % 26 + 26) % 26;
                        }
                    }

                    // Step 4: Calculate the inverse key matrix (kinv) by multiplying adjMat with dinv and mod 26
                    int[][] kinv = new int[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            kinv[i][j] = (dinv * adjMat[i][j]) % 26;
                        }
                    }

                    // Step 5: Decrypt each triplet of characters
                    StringBuilder plainText2 = new StringBuilder();
                    for (int i = 0; i < cipherT.length(); i += 3) {
                        int[] vector2 = {
                                cipherT.charAt(i) - 'A',
                                cipherT.charAt(i + 1) - 'A',
                                cipherT.charAt(i + 2) - 'A'
                        };

                        // Multiply and mod 26
                        int[] decryptedVector = new int[3];
                        decryptedVector[0] = (kinv[0][0] * vector2[0] + kinv[0][1] * vector2[1] + kinv[0][2] * vector2[2]) % 26;
                        decryptedVector[1] = (kinv[1][0] * vector2[0] + kinv[1][1] * vector2[1] + kinv[1][2] * vector2[2]) % 26;
                        decryptedVector[2] = (kinv[2][0] * vector2[0] + kinv[2][1] * vector2[1] + kinv[2][2] * vector2[2]) % 26;

                        // Convert to characters and append to result
                        plainText2.append((char) (decryptedVector[0] + 'A'));
                        plainText2.append((char) (decryptedVector[1] + 'A'));
                        plainText2.append((char) (decryptedVector[2] + 'A'));
                    }

                    tv7.setText(plainText2.toString());
                    Toast.makeText(Hill_Cipher_3.this, "Decryption Successful", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(Hill_Cipher_3.this, "Invalid input in key matrix", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
