package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddPatient extends AppCompatActivity{

    Button btn;
    TextView id,name,address,mobile,age,bg,sex;
    PatientDatabaseHelper myDbPat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        btn = findViewById(R.id.addHistoryBtn);
        id  = findViewById(R.id.editTextIDH);
        name = findViewById(R.id.editTextDateH);
        address = findViewById(R.id.editTextMedicationH);
        mobile = findViewById(R.id.editTextRemarksH);
        age = findViewById(R.id.editTextAgeP);
        bg = findViewById(R.id.editTextBgP);
        sex = findViewById(R.id.editTextSexP);
        myDbPat = new PatientDatabaseHelper(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDbPat.insertData(id.getText().toString(),name.getText().toString(),mobile.getText().toString(),
                        address.getText().toString(), age.getText().toString(), bg.getText().toString(), sex.getText().toString());

                if(isInserted) {
                    Toast.makeText(AddPatient.this, "Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddPatient.this,MainActivity2.class));
                }
                else
                    Toast.makeText(AddPatient.this, "Error! Try again", Toast.LENGTH_SHORT).show();
            }

        });

    }

}