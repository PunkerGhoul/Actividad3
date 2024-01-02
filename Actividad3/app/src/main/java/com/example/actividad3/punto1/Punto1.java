package com.example.actividad3.punto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.actividad3.MainActivity;
import com.example.actividad3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Punto1 extends AppCompatActivity {

    FloatingActionButton btnHome;
    TextView txvResult;
    EditText edtCode;
    EditText edtName;
    EditText edtLastname;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.punto1);
        btnHome = findViewById(R.id.floatingActionButtonHomeAct1);
        txvResult = findViewById(R.id.textViewResults);
        edtCode = findViewById(R.id.editTextCode);
        edtName = findViewById(R.id.editTextName);
        edtLastname = findViewById(R.id.editTextLastname);
        btSubmit = findViewById(R.id.buttonSubmit);
        btnHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Punto1.this, MainActivity.class));
            }
        });
        btSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                txvResult.setText(getString(
                                R.string.result,
                                edtCode.getText(),
                                edtName.getText(),
                                edtLastname.getText()
                        )
                );
            }
        });
    }
}