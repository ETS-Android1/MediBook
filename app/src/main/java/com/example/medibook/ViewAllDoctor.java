package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class ViewAllDoctor extends AppCompatActivity {
    DoctorDatabaseHelper myDb;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_doctor);
        textView = findViewById(R.id.docDetails);
        myDb = new DoctorDatabaseHelper(this);
        Cursor res = myDb.getAllData();
        if(res.getCount()==0){
            Toast.makeText(ViewAllDoctor.this, "0", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder buffer = new StringBuilder();

        while (res.moveToNext()){
            buffer.append("ID: ").append(res.getString(0)).append("\n");
            buffer.append("Name: ").append(res.getString(1)).append("\n");
            buffer.append("Joining Date: ").append(res.getString(2)).append("\n\n");
        }

        textView.setText(buffer.toString());

    }

}