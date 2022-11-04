package com.example.notesapp.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.MainActivity;
import com.example.notesapp.R;

import com.example.notesapp.helpers.NoteAdapter;
import com.example.notesapp.helpers.NoteDBAdapter;
import com.example.notesapp.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ListView l;
//    String tutorials[]
//            = {"Algorithms", "Data Structures",
//            "Languages", "Interview Corner",
//            "GATE", "ISRO CS",
//            "UGC NET CS", "CS Subjects",
//            "Web Technologies"};
    ArrayList<Note> notes = new ArrayList<Note>();
    NoteDBAdapter dbAdapter = new NoteDBAdapter(this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.logout) {
            SharedPreferences preferences = getSharedPreferences("LoginUserData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent mainIntent = new Intent();
            mainIntent.setClass(this, MainActivity.class);
            startActivity(mainIntent);
            return true;
        } else if (item.getItemId() == R.id.refresh) {
            fetchNotes(dbAdapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbAdapter.open();
        l = findViewById(R.id.list);
        FloatingActionButton floatingActionButton = findViewById(R.id.createNote);
        floatingActionButton.setOnClickListener(v -> {
            Intent createNoteIntent = new Intent();
            createNoteIntent.setClass(this, CreateNoteActivity.class);
            startActivity(createNoteIntent);
        });
        fetchNotes(dbAdapter);
    }

    private void fetchNotes(NoteDBAdapter dbAdapter) {
        notes = dbAdapter.getNotes();
        NoteAdapter noteAdap = new NoteAdapter(this, notes);
        l = findViewById(R.id.list);
        l.setAdapter(noteAdap);
        l.setOnItemClickListener((parent, view, position, id) -> {
            Intent editIntent = new Intent();
            editIntent.putExtra("note", notes.get(position));
            editIntent.setClass(this, EditActivity.class);
            startActivity(editIntent);
        });
    }
}