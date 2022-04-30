package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteDoctor extends AppCompatActivity {
    DoctorDatabaseHelper myDb;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_doctor);
        editText = findViewById(R.id.editTextSearchH);
        button = findViewById(R.id.searchBtnH);
        myDb = new DoctorDatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = myDb.deleteData(editText.getText().toString());
                if(deletedRows>0) {
                    Toast.makeText(DeleteDoctor.this, "Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DeleteDoctor.this, MainActivity2.class));
                }
                else
                    Toast.makeText(DeleteDoctor.this, "ID not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}