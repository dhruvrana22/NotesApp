package com.example.notesapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserDBAdapter {
    private static final String dbName = "notesapp";
    private static final int dbVersion = 1;
    private static final String tableName = "User";
    private UserDBHelper userDbHelper;
    private SQLiteDatabase db;

    public UserDBAdapter(Context context) {
        userDbHelper = new UserDBHelper(context, dbName, null, dbVersion);
    }

    public void open() {
        Log.d("NotesApp", "Open User DB");
        db = userDbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
        db = null;
    }

    public boolean login(String username, String password) {
        try {
            Cursor cursor = db.rawQuery("SELECT id, username, password FROM " + tableName + " WHERE username=\"" + username + "\" AND password=\"" + password + "\"", null);

            Log.d("NotesApp", "Query Count: " + cursor.getCount());
            return cursor.getCount() > 0;
        }
        catch (Exception e) {
            Log.e("NotesApp", e.toString());
        }
        return false;
    }

    public long addUser(String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        Log.d("NotesApp", "Added user");
        return db.insert(tableName, null, cv);
    }

    private static class UserDBHelper extends SQLiteOpenHelper {

        public UserDBHelper(Context context, String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("NotesApp", "Creating Table");
            db.execSQL("CREATE TABLE " + tableName + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL UNIQUE, password TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            onCreate(db);
        }
    }
}
