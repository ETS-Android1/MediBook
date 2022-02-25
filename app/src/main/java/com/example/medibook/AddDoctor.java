package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDoctor extends AppCompatActivity {
    Button btnAddData;
    DoctorDatabaseHelper myDb;
    EditText editID, editName, editDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        myDb = new DoctorDatabaseHelper(this);

        btnAddData = findViewById(R.id.addDocData);
        editID = findViewById(R.id.editTextID);
        editDate = findViewById(R.id.editTextJoinDate);
        editName = findViewById(R.id.editTextName);

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(editID.getText().toString(), editName.getText().toString(),editDate.getText().toString());
                if(isInserted) {
                    Toast.makeText(AddDoctor.this, "Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddDoctor.this,MainActivity2.class));
                }
                else
                    Toast.makeText(AddDoctor.this, "Not Inserted", Toast.LENGTH_SHORT).show();

            }
        });
    }
}