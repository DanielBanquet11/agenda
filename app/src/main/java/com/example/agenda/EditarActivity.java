package com.example.agenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.agenda.db.DbContactos;
import com.example.agenda.entidades.Contactos;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuarda;
    Contactos contacto;

    Toolbar toolbar;
    int id = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Establecer el ícono de navegación y agregar un listener
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoELectronico);
        btnGuarda = findViewById(R.id.btnGuarda);
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }

        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos = new DbContactos(EditarActivity.this);
        contacto = dbContactos.verContactos(id);

        if(contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo_electronico());


        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtCorreo.getText().toString().equals("")){
                    boolean correcto =    dbContactos.editarContacto(id, txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());

                    if (correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO ACTUALIZADO", Toast.LENGTH_SHORT).show();
                        verRegistro();
                    }else {
                        Toast.makeText(EditarActivity.this, "ERROR AL ACTUALIZAR", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this,"SE DEBEN LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void verRegistro(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Procedimiento de redireccionamiento aquí, por ejemplo:
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    }
