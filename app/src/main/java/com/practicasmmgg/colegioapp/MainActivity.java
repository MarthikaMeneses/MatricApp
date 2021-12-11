package com.practicasmmgg.colegioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.practicasmmgg.colegioapp.modelo.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    //Declaración de las variables de entrada de datos
    private EditText nomP;
    private EditText apeP;
    private EditText correoP;
    private EditText passwordP;
    private ListView listv_personas;
    //Declaracion de las variabes para la lista
    private List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;
    Persona personaSelected;

    //Variables para manejar la Base de Datos
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomP = (EditText) findViewById(R.id.txtnombrePersona);
        apeP = (EditText) findViewById(R.id.txtapellidoPersona);
        correoP = (EditText) findViewById(R.id.txtcorreoPersona);
        passwordP = (EditText) findViewById(R.id.txtpassPersona);
        listv_personas = (ListView) findViewById(R.id.lvdatosPersonales);
        inicializarfirebase();
       listarDatos();
       listv_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               personaSelected = (Persona) parent.getItemAtPosition(position);
               nomP.setText(personaSelected.getNombre());
               apeP.setText(personaSelected.getApellido());
               correoP.setText(personaSelected.getCorreo());
               passwordP.setText(personaSelected.getClave());
           }
       });
    }

    private void listarDatos() {
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : datasnapshot.getChildren()) {
                    Persona p = objSnaptshot.getValue(Persona.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1, listPerson);
                    listv_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String nombre = nomP.getText().toString();
        String apellido = apeP.getText().toString();
        String correo = correoP.getText().toString();
        String pass = passwordP.getText().toString();

        switch (item.getItemId()) {
            case R.id.icon_add:
                if (nombre.isEmpty() || (apellido.isEmpty()) || (correo.isEmpty()) || (pass.isEmpty())) {
                    validacion();
                } else {
                    Persona p = new Persona();
                    p.setuId(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(apellido);
                    p.setCorreo(correo);
                    p.setClave(pass);
                    databaseReference.child("Persona").child(p.getuId()).setValue(p);
                    Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                    break;
                }
            case R.id.icon_save:
                Persona p = new Persona();
                p.setuId(personaSelected.getuId());
                p.setNombre(nomP.getText().toString().trim());
                p.setApellido(apeP.getText().toString().trim());
                p.setCorreo(correoP.getText().toString().trim());
                p.setClave(passwordP.getText().toString().trim());
                databaseReference.child("Persona").child(p.getuId()).setValue(p);
                Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;

            case R.id.icon_del:
                p = new Persona();
                p.setuId(personaSelected.getuId());
                databaseReference.child("Persona").child(p.getuId()).removeValue();
                Toast.makeText(this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            default:
                break;
        }
        return true;
        // return super.onOptionsItemSelected(item);
    }

    private void limpiarCajas() {
        nomP.setText("");
        apeP.setText("");
        correoP.setText("");
        passwordP.setText("");
    }

    private void validacion() {
        String nombre = nomP.getText().toString();
        String apellido = apeP.getText().toString();
        String correo = correoP.getText().toString();
        String pass = passwordP.getText().toString();
        if (nombre.isEmpty()) {
            nomP.setError("Requerido");
        } else if (apellido.isEmpty()) {
            apeP.setError("Requerido");
        } else if (correo.isEmpty()) {
            correoP.setError("Requerido");
        } else if (pass.isEmpty()) {
            passwordP.setError("Requerido");
        }
    }
}