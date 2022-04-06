package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnStart, login, register;
    EditText username, password;
    LoginDatabaseHelper myDbLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.startButton);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        myDbLogin = new LoginDatabaseHelper(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                Cursor res = myDbLogin.getAllCredentials();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Please register first", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Register.class));
                    return;
                }

                while(res.moveToNext()){
                    if(user.equals("") || pass.equals("")) break;
                    if(user.equals(res.getString(0)) && pass.equals(res.getString(1))){
                        Toast.makeText(MainActivity.this, res.getString(0)+res.getString(1), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,MainActivity2.class));
                        return;
                    }
                }
                Toast.makeText(MainActivity.this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}