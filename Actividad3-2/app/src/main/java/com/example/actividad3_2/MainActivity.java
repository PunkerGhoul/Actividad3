package com.example.actividad3_2;

import static java.lang.Integer.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText numIn;
    Button btnCalc;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numIn = findViewById(R.id.editTextNumber);
        btnCalc = findViewById(R.id.buttonCalcNumber);
        resultView = findViewById(R.id.textViewResult);

        btnCalc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(String.valueOf(numIn.getText()));
                String res;
                if (num % 2 == 0) {
                    res = "par";
                } else {
                    res = "impar";
                    if (esPrimo(num)) {
                        res += " y también es un número primo";
                    }
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