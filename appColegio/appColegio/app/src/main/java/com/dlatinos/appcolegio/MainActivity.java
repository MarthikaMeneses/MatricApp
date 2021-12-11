package com.dlatinos.appcolegio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnEstudiante;
    private Button btnProfesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEstudiante= (Button) findViewById(R.id.btnEstudiante);
        btnEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnEstudiante = new Intent(MainActivity.this,EstudianteActivity.class);
                startActivity(btnEstudiante);
            }
        });
        btnProfesor= (Button) findViewById(R.id.btnProfesor);
        btnProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnProfesor= new Intent(MainActivity.this,ProfesorActivity.class);
                startActivity(btnProfesor);

            }
        });
    }
}