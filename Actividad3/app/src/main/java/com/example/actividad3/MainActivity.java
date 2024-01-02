package com.example.actividad3;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.actividad3.punto1.Punto1;
import com.example.actividad3.punto2.Punto2;
import com.example.actividad3.punto3.Punto3;

public class MainActivity extends AppCompatActivity {

    Button btnAct1;
    Button btnAct2;
    Button btnAct3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAct1 = findViewById(R.id.buttonNavAct1);
        btnAct2 = findViewById(R.id.buttonNavAct2);
        btnAct3 = findViewById(R.id.buttonNavAct3);
        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Punto1.class));
            }
        });
        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Punto2.class));
            }
        });
        btnAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Punto3.class));
            }
        });
        //startActivity(i);
    }
}