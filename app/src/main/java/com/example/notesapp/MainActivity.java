package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.models.User;
import com.example.notesapp.views.HomeActivity;
import com.example.notesapp.views.LoginActivity;
import com.example.notesapp.views.RegisterActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("LoginUserData", Context.MODE_PRIVATE);
        Log.d("NotesApp", "USERNAME: " + preferences.getString("username", ""));
        if(!Objects.equals(preferences.getString("username", ""), "")) {
            User.setCurrentUserName(preferences.getString("username", ""));
            Intent loginIntent = new Intent();
            loginIntent.setClass(this, HomeActivity.class);
            startActivity(loginIntent);
        }
        else {
            Button btnLogin = findViewById(R.id.btnLogin);
            btnLogin.setOnClickListener(v -> {
                Intent loginIntent = new Intent();
                loginIntent.setClass(this, LoginActivity.class);
                startActivity(loginIntent);
            });
            Button btnSignUp = findViewById(R.id.btnSignUp);
            btnSignUp.setOnClickListener(v -> {
                Intent loginIntent = new Intent();
                loginIntent.setClass(this, RegisterActivity.class);
                startActivity(loginIntent);
            });
        }
    }
}