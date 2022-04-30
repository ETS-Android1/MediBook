package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddHistory extends AppCompatActivity {

    PatientHistoryDatabaseHelper myDb;
    PatientDatabaseHelper myDbP;
    EditText id,date,remarks,med;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);

        myDb = new PatientHistoryDatabaseHelper(this);
        myDbP = new PatientDatabaseHelper(this);
        id = findViewById(R.id.editTextIDH);
        date = findViewById(R.id.editTextDateH);
        remarks = findViewById(R.id.editTextRemarksH);
        med = findViewById(R.id.editTextMedicationH);
        add = findViewById(R.id.addHistoryBtn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myDbP.searchPatient(id.getText().toString())) {
                    boolean isInserted = myDb.insertData(id.getText().toString(), date.getText().toString(), remarks.getText().toString(), med.getText().toString());

                    if (isInserted) {
                        Toast.makeText(AddHistory.this, "Added!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddHistory.this, MainActivity2.class));
                    } else {
                        Toast.makeText(AddHistory.this, "Error while inserting data! Try again", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(AddHistory.this, "blah blah", Toast.LENGTH_SHORT).show();

            }
        });

    }
}