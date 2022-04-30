package com.example.medibook;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    Button login, register;
    EditText username, password;
    AES aes = new AES();
    LoginDatabaseHelper myDbLogin;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        myDbLogin = new LoginDatabaseHelper(this);

        BiometricManager biometricManager = BiometricManager.from(this);
        Executor executor = ContextCompat.getMainExecutor(this);

        BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MainActivity.this, "Login Success !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("Use your fingerprint to login to your app")
                .setNegativeButtonText("Cancel")
                .build();

        biometricPrompt.authenticate(promptInfo);

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
                    if(user.equals(aes.decrypt(res.getString(0))) && pass.equals(aes.decrypt(res.getString(1)))){
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
    }


}