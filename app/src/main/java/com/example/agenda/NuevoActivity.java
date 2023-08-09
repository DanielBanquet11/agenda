package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import db.DbContactos;
import db.DbHelper;

public class NuevoActivity extends AppCompatActivity {
    EditText txtNombre, txtTelefono, txtCorreoElectronico;
    Button btnGuarda;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoELectronico);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                long id = dbContactos.insertarContacto(txtNombre.getText().toString(),txtTelefono.getText().toString(),txtCorreoElectronico.getText().toString());
                if (id > 0){
                    Toast.makeText(NuevoActivity.this, "REGISTRO AGREDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                }else {
                    Toast.makeText(NuevoActivity.this,"REGISTRO AGREGADO",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }
}