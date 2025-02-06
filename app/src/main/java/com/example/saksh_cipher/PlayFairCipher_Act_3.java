package com.example.saksh_cipher;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayFairCipher_Act_3 extends AppCompatActivity {
    Button bt1, bt2;
    EditText et1, et2, et3, et4;
    TextView tv3, tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_fair_cipher_act3);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        tv3 = findViewById(R.id.tv3);
        tv5 = findViewById(R.id.tv5);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.GREEN);

                String plainT = et1.getText().toString().replace(" ", "").trim().toUpperCase().replace("J", "I");
                String key = et2.getText().toString().trim().toUpperCase().replace("J", "I");

                if (plainT.isEmpty()) {
                    et1.setError("Cannot be empty");
                    return;
                }
                if (key.isEmpty()) {
                    et2.setError("Cannot be empty");
                    return;
                }

                String cipherText = playfairEncrypt(plainT, key);
                tv3.setText(cipherText);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setBackgroundColor(Color.RED);

                String cipherT = et3.getText().toString().trim().toUpperCase();
                String key = et4.getText().toString().trim().toUpperCase().replace("J", "I");

                if (cipherT.isEmpty()) {
                    tv3.setError("No ciphertext to decrypt");
                    return;
                }
                if (key.isEmpty()) {
                    et2.setError("Cannot be empty");
                    return;
                }

                String decryptedText = playfairDecrypt(cipherT, key);
                tv5.setText(decryptedText);
            }
        });
    }

    private String playfairEncrypt(String plainText, String key) {
        char[][] keyMatrix = generateKeyMatrix(key);
        StringBuilder formattedText = formatPlainText(plainText);
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < formattedText.length(); i += 2) {
            char firstChar = formattedText.charAt(i);
            char secondChar = formattedText.charAt(i + 1);
            int[] firstPos = findPosition(keyMatrix, firstChar);
            int[] secondPos = findPosition(keyMatrix, secondChar);

            if (firstPos[0] == secondPos[0]) { // Same row
                cipherText.append(keyMatrix[firstPos[0]][(firstPos[1] + 1) % 5]);
                cipherText.append(keyMatrix[secondPos[0]][(secondPos[1] + 1) % 5]);
            } else if (firstPos[1] == secondPos[1]) { // Same column
                cipherText.append(keyMatrix[(firstPos[0] + 1) % 5][firstPos[1]]);
                cipherText.append(keyMatrix[(secondPos[0] + 1) % 5][secondPos[1]]);
            } else { // Rectangle swap
                cipherText.append(keyMatrix[firstPos[0]][secondPos[1]]);
                cipherText.append(keyMatrix[secondPos[0]][firstPos[1]]);
            }
        }

        return cipherText.toString();
    }

    private String playfairDecrypt(String cipherText, String key) {
        char[][] keyMatrix = generateKeyMatrix(key);
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i += 2) {
            char firstChar = cipherText.charAt(i);
            char secondChar = cipherText.charAt(i + 1);
            int[] firstPos = findPosition(keyMatrix, firstChar);
            int[] secondPos = findPosition(keyMatrix, secondChar);

            if (firstPos[0] == secondPos[0]) { // Same row
                decryptedText.append(keyMatrix[firstPos[0]][(firstPos[1] + 4) % 5]);
                decryptedText.append(keyMatrix[secondPos[0]][(secondPos[1] + 4) % 5]);
            } else if (firstPos[1] == secondPos[1]) { // Same column
                decryptedText.append(keyMatrix[(firstPos[0] + 4) % 5][firstPos[1]]);
                decryptedText.append(keyMatrix[(secondPos[0] + 4) % 5][secondPos[1]]);
            } else { // Rectangle swap
                decryptedText.append(keyMatrix[firstPos[0]][secondPos[1]]);
                decryptedText.append(keyMatrix[secondPos[0]][firstPos[1]]);
            }
        }

        // Remove any 'X' padding added during encryption
        String result = decryptedText.toString().replace("X", "");
        return result;
    }

    private char[][] generateKeyMatrix(String key) {
        boolean[] used = new boolean[26];
        char[][] keyMatrix = new char[5][5];
        int row = 0, col = 0;

        for (char c : key.toCharArray()) {
            if (c != 'J' && !used[c - 'A']) {
                keyMatrix[row][col++] = c;
                used[c - 'A'] = true;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used[c - 'A']) {
                keyMatrix[row][col++] = c;
                used[c - 'A'] = true;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
        return keyMatrix;
    }

    private StringBuilder formatPlainText(String text) {
        StringBuilder formattedText = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            if (first == second) {
                formattedText.append(first).append('X');
                i--;
            } else {
                formattedText.append(first).append(second);
            }
        }
        if (formattedText.length() % 2 != 0) {
            formattedText.append('X'); // Padding if odd length
        }
        return formattedText;
    }

    private int[] findPosition(char[][] keyMatrix, char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}








/*package com.example.saksh_cipher;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayFairCipher_Act_3 extends AppCompatActivity {
    Button bt1, bt2;
    EditText et1, et2, et3, et4;
    TextView tv3, tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_fair_cipher_act3);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        tv3 = findViewById(R.id.tv3);
        tv5 = findViewById(R.id.tv5);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.GREEN);

                String plainT = et1.getText().toString().trim().toUpperCase().replace("J", "I");
                String key = et2.getText().toString().trim().toUpperCase().replace("J", "I");

                if (plainT.isEmpty()) {
                    et1.setError("Cannot be empty");
                    return;
                }
                if (key.isEmpty()) {
                    et2.setError("Cannot be empty");
                    return;
                }

                String cipherText = playfairEncrypt(plainT, key);
                tv3.setText(cipherText);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setBackgroundColor(Color.RED);

                String cipherT = tv3.getText().toString().trim().toUpperCase();
                String key = et2.getText().toString().trim().toUpperCase().replace("J", "I");

                if (cipherT.isEmpty()) {
                    tv3.setError("No ciphertext to decrypt");
                    return;
                }
                if (key.isEmpty()) {
                    et2.setError("Cannot be empty");
                    return;
                }

                String decryptedText = playfairDecrypt(cipherT, key);
                tv5.setText(decryptedText);
            }
        });
    }

    private String playfairEncrypt(String plainText, String key) {
        char[][] keyMatrix = generateKeyMatrix(key);
        StringBuilder formattedText = formatPlainText(plainText);
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < formattedText.length(); i += 2) {
            char firstChar = formattedText.charAt(i);
            char secondChar = formattedText.charAt(i + 1);
            int[] firstPos = findPosition(keyMatrix, firstChar);
            int[] secondPos = findPosition(keyMatrix, secondChar);

            // Check positions and apply Playfair Cipher rules for encryption
            if (firstPos[0] == secondPos[0]) { // Same row
                cipherText.append(keyMatrix[firstPos[0]][(firstPos[1] + 1) % 5]);
                cipherText.append(keyMatrix[secondPos[0]][(secondPos[1] + 1) % 5]);
            } else if (firstPos[1] == secondPos[1]) { // Same column
                cipherText.append(keyMatrix[(firstPos[0] + 1) % 5][firstPos[1]]);
                cipherText.append(keyMatrix[(secondPos[0] + 1) % 5][secondPos[1]]);
            } else { // Rectangle swap
                cipherText.append(keyMatrix[firstPos[0]][secondPos[1]]);
                cipherText.append(keyMatrix[secondPos[0]][firstPos[1]]);
            }
        }

        return cipherText.toString();
    }

    private String playfairDecrypt(String cipherText, String key) {
        char[][] keyMatrix = generateKeyMatrix(key);
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i += 2) {
            char firstChar = cipherText.charAt(i);
            char secondChar = cipherText.charAt(i + 1);
            int[] firstPos = findPosition(keyMatrix, firstChar);
            int[] secondPos = findPosition(keyMatrix, secondChar);

            // Check positions and apply Playfair Cipher rules for decryption
            if (firstPos[0] == secondPos[0]) { // Same row
                decryptedText.append(keyMatrix[firstPos[0]][(firstPos[1] + 4) % 5]);
                decryptedText.append(keyMatrix[secondPos[0]][(secondPos[1] + 4) % 5]);
            } else if (firstPos[1] == secondPos[1]) { // Same column
                decryptedText.append(keyMatrix[(firstPos[0] + 4) % 5][firstPos[1]]);
                decryptedText.append(keyMatrix[(secondPos[0] + 4) % 5][secondPos[1]]);
            } else { // Rectangle swap
                decryptedText.append(keyMatrix[firstPos[0]][secondPos[1]]);
                decryptedText.append(keyMatrix[secondPos[0]][firstPos[1]]);
            }
        }

        return decryptedText.toString();
    }

    private char[][] generateKeyMatrix(String key) {
        boolean[] used = new boolean[26];
        char[][] keyMatrix = new char[5][5];
        int row = 0, col = 0;

        for (char c : key.toCharArray()) {
            if (c != 'J' && !used[c - 'A']) {
                keyMatrix[row][col++] = c;
                used[c - 'A'] = true;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used[c - 'A']) {
                keyMatrix[row][col++] = c;
                used[c - 'A'] = true;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
        return keyMatrix;
    }

    private StringBuilder formatPlainText(String text) {
        StringBuilder formattedText = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            if (first == second) {
                formattedText.append(first).append('X');
                i--;
            } else {
                formattedText.append(first).append(second);
            }
        }
        if (formattedText.length() % 2 != 0) {
            formattedText.append('X'); // Padding if odd length
        }
        return formattedText;
    }

    private int[] findPosition(char[][] keyMatrix, char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}*/
