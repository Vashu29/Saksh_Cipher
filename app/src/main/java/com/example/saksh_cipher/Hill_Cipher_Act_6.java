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

public class Hill_Cipher_Act_6 extends AppCompatActivity {
    Button bt1,bt2;
    EditText et1,et2,et3,et4,et5,et6,et7,et8,et9,et10;
    TextView tv4,tv7;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_cipher_act6);
        
        bt1 = findViewById(R.id.bt1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        tv4 = findViewById(R.id.tv4);

        bt2 = findViewById(R.id.bt2);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);
        et8 = findViewById(R.id.et8);
        et9 = findViewById(R.id.et9);
        et10 = findViewById(R.id.et10);
        tv7 = findViewById(R.id.tv7);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.GREEN);
                String plainT = et1.getText().toString().trim();
                plainT = plainT.toUpperCase();
                if (plainT.isEmpty()) {
                    et1.setError("Cannot be empty");
                    return;
                }

                // Ensure plain text has an even length
                if (plainT.replace(" ", "").length() % 2 != 0) {
                    plainT += "X";
                }

                if (et2.getText().toString().trim().isEmpty()) {
                    et2.setError("Cannot be empty");
                    return;
                }
                if (et3.getText().toString().trim().isEmpty()) {
                    et3.setError("Cannot be empty");
                    return;
                }
                if (et4.getText().toString().trim().isEmpty()) {
                    et4.setError("Cannot be empty");
                    return;
                }
                if (et5.getText().toString().trim().isEmpty()) {
                    et5.setError("Cannot be empty");
                    return;
                }

                int n1 = Integer.parseInt(et2.getText().toString());
                int n2 = Integer.parseInt(et3.getText().toString());
                int n3 = Integer.parseInt(et4.getText().toString());
                int n4 = Integer.parseInt(et5.getText().toString());

                int[][] keyMatrix = {
                        {n1, n2},
                        {n3, n4}
                };

                StringBuilder cipherText = new StringBuilder();
                for (int i = 0; i < plainT.length(); i++) {
                    char currentChar = plainT.charAt(i);

                    // Preserve spaces in the ciphertext
                    if (currentChar == ' ') {
                        cipherText.append(' ');
                        continue;
                    }

                    // Handle pairs of characters for encryption
                    if (i + 1 < plainT.length() && plainT.charAt(i + 1) != ' ') {
                        int[] vector = {
                                currentChar - 'A',
                                plainT.charAt(i + 1) - 'A'
                        };

                        // Multiply and mod 26
                        int[] encryptedPair = new int[2];
                        encryptedPair[0] = (keyMatrix[0][0] * vector[0] + keyMatrix[0][1] * vector[1]) % 26;
                        encryptedPair[1] = (keyMatrix[1][0] * vector[0] + keyMatrix[1][1] * vector[1]) % 26;

                        for (int j = 0; j < 2; j++) {
                            if (encryptedPair[j] < 0) {
                                encryptedPair[j] += 26;
                            }
                        }

                        // Convert to characters and append to the result
                        cipherText.append((char) (encryptedPair[0] + 'A'));
                        cipherText.append((char) (encryptedPair[1] + 'A'));

                        // Skip the next character as it is already processed
                        i++;
                    } else {
                        // If single character left, encrypt it as a single block
                        int vector = currentChar - 'A';
                        int encryptedChar = (keyMatrix[0][0] * vector) % 26;
                        if (encryptedChar < 0) {
                            encryptedChar += 26;
                        }
                        cipherText.append((char) (encryptedChar + 'A'));
                    }
                }

                // Display encrypted text
                tv4.setText(cipherText.toString());
                Toast.makeText(Hill_Cipher_Act_6.this, "Encryption Successful", Toast.LENGTH_SHORT).show();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setBackgroundColor(Color.RED);
                String cipherT = et6.getText().toString().trim().toUpperCase();

                if (cipherT.isEmpty()) {
                    et6.setError("Cannot be empty");
                    return;
                }

                // Validate that all necessary fields are filled
                if (et7.getText().toString().trim().isEmpty()) {
                    et7.setError("Cannot be empty");
                    return;
                }
                if (et8.getText().toString().trim().isEmpty()) {
                    et8.setError("Cannot be empty");
                    return;
                }
                if (et9.getText().toString().trim().isEmpty()) {
                    et9.setError("Cannot be empty");
                    return;
                }
                if (et10.getText().toString().trim().isEmpty()) {
                    et10.setError("Cannot be empty");
                    return;
                }

                int n5 = Integer.parseInt(et7.getText().toString());
                int n6 = Integer.parseInt(et8.getText().toString());
                int n7 = Integer.parseInt(et9.getText().toString());
                int n8 = Integer.parseInt(et10.getText().toString());

                int[][] keyMatrix = {
                        {n5, n6},
                        {n7, n8}
                };

                // Step 1: Calculate the determinant
                int d = (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[1][0] * keyMatrix[0][1]) % 26;
                if (d < 0) {
                    d += 26;
                }

                // Step 2: Find the modular inverse of the determinant
                int dinv = -1;
                for (int i = 1; i < 26; i++) {
                    if ((d * i) % 26 == 1) {
                        dinv = i;
                        break;
                    }
                }

                if (dinv == -1) {
                    Toast.makeText(Hill_Cipher_Act_6.this, "Determinant has no modular inverse; decryption failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Step 3: Calculate the adjoint of the key matrix
                int[][] adjMat = new int[2][2];
                adjMat[0][0] = keyMatrix[1][1];
                adjMat[0][1] = -keyMatrix[0][1];
                adjMat[1][0] = -keyMatrix[1][0];
                adjMat[1][1] = keyMatrix[0][0];

                // Make all adjoint elements positive mod 26
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        adjMat[i][j] = (adjMat[i][j] % 26 + 26) % 26;
                    }
                }

                // Step 4: Calculate the inverse key matrix
                int[][] kinv = new int[2][2];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        kinv[i][j] = (dinv * adjMat[i][j]) % 26;
                    }
                }

                // Step 5: Decrypt each pair of characters, preserving spaces
                StringBuilder plainText2 = new StringBuilder();
                for (int i = 0; i < cipherT.length(); i++) {
                    char currentChar = cipherT.charAt(i);

                    // Preserve spaces in plaintext
                    if (currentChar == ' ') {
                        plainText2.append(' ');
                        continue;
                    }

                    // Process pairs of characters
                    if (i + 1 < cipherT.length() && cipherT.charAt(i + 1) != ' ') {
                        int[] vector2 = {
                                currentChar - 'A',
                                cipherT.charAt(i + 1) - 'A'
                        };

                        // Multiply and mod 26
                        int[] decryptedPair = new int[2];
                        decryptedPair[0] = (kinv[0][0] * vector2[0] + kinv[0][1] * vector2[1]) % 26;
                        decryptedPair[1] = (kinv[1][0] * vector2[0] + kinv[1][1] * vector2[1]) % 26;

                        // Convert to characters and append to result
                        plainText2.append((char) (decryptedPair[0] + 'A'));
                        plainText2.append((char) (decryptedPair[1] + 'A'));

                        // Skip the next character as it has been processed
                        i++;
                    }
                }

                tv7.setText(plainText2.toString());
                Toast.makeText(Hill_Cipher_Act_6.this, "Decryption Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}