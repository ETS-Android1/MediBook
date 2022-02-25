package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddPatient extends AppCompatActivity{

    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv = findViewById(R.id.textView);
                tv.setText("hello this is changed");
                Intent intent = new Intent(AddPatient.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

}