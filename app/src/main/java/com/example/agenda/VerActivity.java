package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.agenda.db.DbContactos;
import com.example.agenda.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuarda;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
    Contactos contacto;
    int id = 0;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoELectronico);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Establecer el ícono de navegación y agregar un listener
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

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

        DbContactos dbContactos = new DbContactos(VerActivity.this);
         contacto = dbContactos.verContactos(id);

         if(contacto != null){
             txtNombre.setText(contacto.getNombre());
             txtTelefono.setText(contacto.getTelefono());
             txtCorreo.setText(contacto.getCorreo_electronico());
             btnGuarda.setVisibility(View.INVISIBLE);
             txtNombre.setInputType(InputType.TYPE_NULL);
             txtTelefono.setInputType(InputType.TYPE_NULL);
             txtCorreo.setInputType(InputType.TYPE_NULL);

         }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (dbContactos.eliminarContacto(id)){
                            lista();
                        }
                    }
                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Procedimiento de redireccionamiento aquí, por ejemplo:
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}