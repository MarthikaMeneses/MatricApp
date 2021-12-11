package com.dlatinos.appcolegio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfesorActivity extends AppCompatActivity {
private Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);
        btnRegresar = (Button) findViewById(R.id.btnRegresarProfesor);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnRegresar = new Intent(ProfesorActivity.this,MainActivity.class);
                startActivity(btnRegresar);
            }
        });
    }
}