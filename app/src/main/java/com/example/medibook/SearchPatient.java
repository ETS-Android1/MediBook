package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchPatient extends AppCompatActivity {
    PatientDatabaseHelper myDb;
    Button button;
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_patient);

        myDb = new PatientDatabaseHelper(this);
        button = findViewById(R.id.searchBtnH);
        editText = findViewById(R.id.editTextSearchH);
        textView = findViewById(R.id.textViewHistory);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder buffer = new StringBuilder();
                Cursor res = myDb.getAllData();
                boolean exists=false;

                if(res.getCount()!=0){
                    while(res.moveToNext()){
                        if(res.getString(0).equals(editText.getText().toString())){
                            buffer.append("PATIENT DETAILS: \n\n");
                            buffer.append("ID: ").append(res.getString(0)).append("\n");
                            buffer.append("Name: ").append(res.getString(1)).append("\n");
                            buffer.append("Mobile: ").append(res.getString(2)).append("\n");
                            buffer.append("Addess: ").append(res.getString(3)).append("\n");
                            buffer.append("Age: ").append(res.getString(4)).append("\n");
                            buffer.append("Bloodgroup: ").append(res.getString(5)).append("\n");
                            buffer.append("Sex: ").append(res.getString(6)).append("\n");
                            exists=true;
                            break;
                        }
                    }
                }

                if(exists) textView.setText(buffer.toString());
                else textView.setText("Patient with ID: "+editText.getText().toString()+" does not exists!");
            }
        });

    }
}