package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAllPatient extends AppCompatActivity {
    PatientDatabaseHelper myDbPat;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_patient);
        textView= findViewById(R.id.patDetails);
        myDbPat = new PatientDatabaseHelper(this);
        Cursor res = myDbPat.getAllData();
        if(res.getCount()==0){
            Toast.makeText(ViewAllPatient.this, "0", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder buffer = new StringBuilder();

        while (res.moveToNext()){
            buffer.append("ID: ").append(res.getString(0)).append("\n");
            buffer.append("Name: ").append(res.getString(1)).append("\n");
            buffer.append("Mobile: ").append(res.getString(2)).append("\n");
            buffer.append("Addess: ").append(res.getString(3)).append("\n");
            buffer.append("Age: ").append(res.getString(4)).append("\n");
            buffer.append("Bloodgroup: ").append(res.getString(5)).append("\n");
            buffer.append("Sex: ").append(res.getString(6)).append("\n\n");
        }

        textView.setText(buffer.toString());
    }
}































