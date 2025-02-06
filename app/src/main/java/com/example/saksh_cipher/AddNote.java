package com.example.saksh_cipher;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.saksh_cipher.NoteDatabase;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitle, noteDetail;
    Calendar c;
    String todaysDate;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white2));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteDetail = findViewById(R.id.noteDetail);
        noteTitle = findViewById(R.id.noteTitle);

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // get current date and time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE))+":"+pad(c.get(Calendar.SECOND));

        Log.d("calendar", "Date and Time: " + todaysDate +" and " + currentTime);
    }

    private String pad(int i) {
        if(i < 10)
            return "0"+i;
        return  String.valueOf(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Note not Saved", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if(item.getItemId() == R.id.save){
            Note note = new Note(noteTitle.getText().toString(),noteDetail.getText().toString(),todaysDate, currentTime);
            NoteDatabase db = new NoteDatabase(this);
            db.addNote(note);
            Toast.makeText(this, "Save btn", Toast.LENGTH_SHORT).show();
            goToMain();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain(){
        Intent i = new Intent(this, Notepad.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
