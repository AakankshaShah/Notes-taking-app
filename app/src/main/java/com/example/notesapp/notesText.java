package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;

public class notesText extends AppCompatActivity {
     int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_text);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText ed=findViewById(R.id.editText);
        Intent in=getIntent();
        noteId=in.getIntExtra("NoteId",-1);
        if(noteId!=-1)
        ed.setText(MainActivity.notes.get(noteId));
        else {
            MainActivity.notes.add("");
            noteId=MainActivity.notes.size()-1;
            MainActivity.ad.notifyDataSetChanged();
        }

        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {MainActivity.notes.set(noteId,String.valueOf(s));
            MainActivity.ad.notifyDataSetChanged();
                SharedPreferences sf=getApplicationContext().getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
                HashSet<String> set;
                set = new HashSet(MainActivity.notes);
                sf.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

}
