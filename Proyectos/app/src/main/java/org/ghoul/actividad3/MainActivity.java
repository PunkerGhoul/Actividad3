package org.ghoul.Actividad3-1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txvResult;
    EditText edtCode;
    EditText edtName;
    EditText edtLastname;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvResult = findViewById(R.id.textViewResults);
        edtCode = findViewById(R.id.editTextCode);
        edtName = findViewById(R.id.editTextName);
        edtLastname = findViewById(R.id.editTextLastname);
        btSubmit = findViewById(R.id.buttonSubmit);
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