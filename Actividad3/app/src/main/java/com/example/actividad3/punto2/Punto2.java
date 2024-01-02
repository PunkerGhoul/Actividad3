package com.example.actividad3.punto2;

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
import com.example.actividad3.punto1.Punto1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Punto2 extends AppCompatActivity {

    FloatingActionButton btnHome;
    EditText numIn;
    Button btnCalc;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.punto2);
        btnHome = findViewById(R.id.floatingActionButtonHomeAct2);
        numIn = findViewById(R.id.editTextNumber);
        btnCalc = findViewById(R.id.buttonCalcNumber);
        resultView = findViewById(R.id.textViewResult);

        btnHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Punto2.this, MainActivity.class));
            }
        });

        btnCalc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(String.valueOf(numIn.getText()));
                String res = num % 2 == 0 ? "par" : "impar";
                if (esPrimo(num)) {
                    res += " y también es un número primo";
                }
                resultView.setText(String.format(getString(R.string.result_par_impar),
                        numIn.getText(),
                        res));
            }
        });
    }

    private boolean esPrimo(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


}