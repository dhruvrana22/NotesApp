package com.example.notesapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.helpers.NoteDBAdapter;
import com.example.notesapp.models.Note;

public class CreateNoteActivity extends AppCompatActivity {
    NoteDBAdapter noteDBAdapter = new NoteDBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        Button btnCreateNote = findViewById(R.id.btnCreateNote);
        SharedPreferences preferences = getSharedPreferences("LoginUserData", Context.MODE_PRIVATE);

        btnCreateNote.setOnClickListener(v -> {
            EditText title = findViewById(R.id.txtTitle);
            EditText content = findViewById(R.id.txtContent);
            Note note = new Note(1, title.getText().toString(), content.getText().toString(), preferences.getString("username", ""));
            noteDBAdapter.open();
            noteDBAdapter.addNote(note);
            noteDBAdapter.close();
            Toast.makeText(this, "Created Note", Toast.LENGTH_SHORT).show();
            Intent homeIntent = new Intent();
            homeIntent.setClass(this, HomeActivity.class);
            startActivity(homeIntent);
        });
    }
}