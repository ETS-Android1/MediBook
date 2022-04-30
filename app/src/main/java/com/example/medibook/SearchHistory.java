package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchHistory extends AppCompatActivity {

    PatientHistoryDatabaseHelper myDb;
    EditText id;
    Button search;
    TextView history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        myDb = new PatientHistoryDatabaseHelper(this);
        id = findViewById(R.id.editTextIDH);
        search = findViewById(R.id.searchBtnH);
        history = findViewById(R.id.textViewHistory);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder buffer = new StringBuilder();
                Cursor res = myDb.getAllData();
                int idd=1;
                if(res.getCount()!=0){
                    while(res.moveToNext()){
                        if(idd==1)
                            buffer.append("ID: ").append(res.getString(0)).append("\n\n");
                        buffer.append("Date: ").append(res.getString(1)).append("\n");
                        buffer.append("Remarks: ").append(res.getString(2)).append("\n");
                        buffer.append("Medication: ").append(res.getString(3)).append("\n\n");
                    }
                }

                history.setText(buffer.toString());
            }
        });


    }
}