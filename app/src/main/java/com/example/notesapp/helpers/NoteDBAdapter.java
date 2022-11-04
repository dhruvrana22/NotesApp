package com.example.notesapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.notesapp.models.Note;

import java.util.ArrayList;

public class NoteDBAdapter {
    private static final String dbName = "notesapp";
    private static final int dbVersion = 4;
    private final NoteDBHelper noteDBHelper;
    public static String tableName = "Notes";
    private SQLiteDatabase db;

    public NoteDBAdapter(Context context) {
        noteDBHelper = new NoteDBHelper(context, dbName, null, dbVersion);
    }

    public void open() {
        Log.d("NotesApp","Opening Notes Table");
        try {
            db = noteDBHelper.getWritableDatabase();
            Log.d("NotesApp", db.toString());
        } catch (Exception e) {
            Log.e("NotesApp", e.toString());
        }
    }

    public void close() {
        db.close();
        db = null;
    }

    public ArrayList<Note> getNotes() {
        try {
            Cursor cursor = db.query(tableName, new String[]{"id", "title", "content", "username"}, null, null, null, null, null);
            ArrayList<Note> notes = new ArrayList<Note>();
            int i = 0;
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                String userName = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                Note note = new Note(id, title, content, userName);
                notes.add(note);
            }
            return notes;
        }
        catch (Exception e) {
            Log.e("NotesApp", e.toString());
        }
        return new ArrayList<Note>();
    }

    public void updateNote(Note note) {
        ContentValues cv = new ContentValues();
        cv.put("title", note.getTitle());
        cv.put("content", note.getContent());
        db.update(tableName, cv, "id=?", new String[]{String.valueOf(note.getId())});
    }


    public String[] getNoteString() {
        Cursor cursor = db.query(tableName, new String[]{"id", "title", "content", "username"}, null, null, null, null, null);
        String[] notes = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            String userName = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            Note note = new Note(id, title, content, userName);
            notes[i++] = note.toString();
        }
        return notes;
    }

    public long addNote(Note note) {
        ContentValues cv = new ContentValues();
        cv.put("title", note.getTitle());
        cv.put("content", note.getContent());
        cv.put("username", note.getUserName());
        return db.insert(tableName, null, cv);
    }

    private static class NoteDBHelper extends SQLiteOpenHelper {

        public NoteDBHelper(Context context, String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("NotesApp", "Creating Notes Table");
            db.execSQL("CREATE TABLE " + tableName + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, content TEXT NOT NULL , username TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("NotesApp", "Upgrading Notes Table");
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            onCreate(db);
        }
    }
}
