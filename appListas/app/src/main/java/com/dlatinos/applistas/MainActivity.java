package com.dlatinos.applistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private ArrayList <String> ciclos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista= (ListView) findViewById(R.id.lista);
        ciclos= new ArrayList<String>();
        ciclos.add("ciclo1");
        ciclos.add("ciclo2");
        ciclos.add("ciclo3");
        ciclos.add("ciclo4 Web");
        ciclos.add("ciclo4 Móviles");
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ciclos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Haz pulsado: "+ciclos.get(position),Toast.LENGTH_LONG).show();
            }
        });
    }
}