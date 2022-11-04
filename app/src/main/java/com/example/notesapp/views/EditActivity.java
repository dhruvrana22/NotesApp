package com.example.notesapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.notesapp.R;
import com.example.notesapp.helpers.NoteDBAdapter;
import com.example.notesapp.models.Note;

public class EditActivity extends AppCompatActivity {
    NoteDBAdapter noteDBAdapter = new NoteDBAdapter(this);
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        note = (Note)i.getSerializableExtra("note");

        EditText editTitle = findViewById(R.id.txtEditTitle);
        EditText editContent = findViewById(R.id.txtEditContent);

        editTitle.setText(note.getTitle());
        editContent.setText(note.getContent());

        Button editNote = findViewById(R.id.btnEditNote1);
        editNote.setOnClickListener(v -> {
            note.setTitle(editTitle.getText().toString());
            note.setContent(editContent.getText().toString());
            noteDBAdapter.open();
            noteDBAdapter.updateNote(note);
            noteDBAdapter.close();
            Intent homeIntent = new Intent();
            homeIntent.setClass(this, HomeActivity.class);
            startActivity(homeIntent);
        });
    }
}