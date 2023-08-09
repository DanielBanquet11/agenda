package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import db.DbHelper;

public class MainActivity extends AppCompatActivity {
    Button btnCrear;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*btnCrear = findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null){
                    Toast.makeText(MainActivity.this,"BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"ERROR AL CREAR LA BASE DE DATOS",Toast.LENGTH_LONG).show();
                }

            }
        });*/

    }
   public boolean onCraeteOptionsMenu(Menu menu){
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.menuNuevo){
            nuevoRegistro();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
       /* switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            case R.id.menuEdita:
                editaRegistro();
                return true;
            default:}*/

    }

    public void nuevoRegistro(){
        Intent intent = new Intent( this, NuevoActivity.class);
        startActivity(intent);
    }
    public void editaRegistro(){

    }

    public void vistaRegistro(View View){
        Intent i = new Intent(this, NuevoActivity.class);
        startActivity(i);
    }
}