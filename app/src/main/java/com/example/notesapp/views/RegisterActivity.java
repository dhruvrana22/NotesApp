package com.example.notesapp.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.R;
import com.example.notesapp.helpers.UserDBAdapter;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText txtUsername = findViewById(R.id.txtLoginUsername);
        EditText txtPassword = findViewById(R.id.txtPasswordRegister);
        EditText txtPasswordConf = findViewById(R.id.txtPasswordRegisterConfirm);
        Button btnRegister = findViewById(R.id.btnSignUP);
        btnRegister.setOnClickListener(v -> {
            if(txtPassword.getText().toString().equals(txtPasswordConf.getText().toString())) {
                Intent homeIntent = new Intent();
                homeIntent.setClass(this, HomeActivity.class);
                SharedPreferences preferences = getSharedPreferences("LoginUserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", txtUsername.getText().toString());
                editor.commit();
                UserDBAdapter adapter = new UserDBAdapter(this);
                adapter.open();
                adapter.addUser(txtUsername.getText().toString(), txtPassword.getText().toString());
                adapter.close();
                Toast.makeText(this, "Successful Login as " + txtUsername.getText().toString(), Toast.LENGTH_SHORT).show();
                startActivity(homeIntent);
            }
        });

    }
}