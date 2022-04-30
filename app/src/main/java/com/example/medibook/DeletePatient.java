package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeletePatient extends AppCompatActivity {
    PatientDatabaseHelper myDb;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_patient);
        editText = findViewById(R.id.editTextSearchH);
        button = findViewById(R.id.searchBtnH);
        myDb = new PatientDatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = myDb.deleteData(editText.getText().toString());
                if(deletedRows>0) {
                    Toast.makeText(DeletePatient.this, "Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DeletePatient.this, MainActivity2.class));
                }
                else
                    Toast.makeText(DeletePatient.this, "ID not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}