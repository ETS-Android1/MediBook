package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button register;
    EditText username, password, password2;
    LoginDatabaseHelper myDbReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.registerBtn);
        username = findViewById(R.id.editTextUsernameRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        password2 = findViewById(R.id.editTextPassword2Register);
        myDbReg = new LoginDatabaseHelper(this);
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String pass2 = password2.getText().toString();

        if(pass.equals(pass2))
        {

        }
        else
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
    }
}