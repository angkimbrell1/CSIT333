package com.example.android.codr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = (EditText)findViewById(R.id.username);
        username.setText(CurrentUser.name);
    }

    public void goToRegisterScreen (View v) {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    public void login(View v) {

        int userIndex = -1;

        // get the username
        String username = ((EditText)findViewById(R.id.username)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        DBHelper db = new DBHelper(this);

        User user = db.getUserByUsername(username);
        if(user == null) {
            Toast.makeText(this, "Incorrect username", Toast.LENGTH_SHORT).show();
            return;
        }

        if(user.password.equals(password) == false) {
            Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Keep their username in memory
        CurrentUser.name = username;

        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }
}
