package com.example.medibook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button register;
    AES aes = new AES();
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

        register.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();

                if(pass.equals(pass2))
                {
                    boolean isInserted = myDbReg.insertCredentials(aes.encrypt(user),aes.encrypt(pass));
                    if(isInserted) {
                        Toast.makeText(Register.this, "Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this,MainActivity.class));
                    }
                }
                else
                    Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}