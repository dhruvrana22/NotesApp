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
import com.example.notesapp.helpers.UserDBAdapter;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText txtUsername = findViewById(R.id.txtLoginUsername);
        EditText txtPassword = findViewById(R.id.txtLoginPassword);

        Button btn = findViewById(R.id.btnSignIn);
        btn.setOnClickListener(v -> {

            UserDBAdapter dbAdapter = new UserDBAdapter(this);
            dbAdapter.open();
            if(dbAdapter.login(txtUsername.getText().toString(), txtPassword.getText().toString())) {
                SharedPreferences preferences = getSharedPreferences("LoginUserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", txtUsername.getText().toString());
                editor.commit();
                Toast.makeText(this, "Successfully logged in as " + txtUsername.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent();
                loginIntent.setClass(this, HomeActivity.class);
                startActivity(loginIntent);
            }
            else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}