package com.example.turisteca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class RegistroActivity extends AppCompatActivity {
    ClaseConexion obj = new ClaseConexion();
    Connection con;
    EditText nombre_u, apellido_p, apellido_m, edad, sexo, n_telefono, c_electronico, n_usuario, contraseña, c_contraseña;
    int id = 0;
    Image foto;
    Button aceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre_u = findViewById(R.id.editTextNombre);
        apellido_p = findViewById(R.id.editTextApellidoP);
        apellido_m = findViewById(R.id.editTextApellidoM);
        edad = findViewById(R.id.editTextEdad);
        sexo = findViewById(R.id.editTextSexo);
        n_telefono = findViewById(R.id.editTextTelefono);
        c_electronico = findViewById(R.id.editTextCorreoE);
        n_usuario = findViewById(R.id.editTextNombreU);
        contraseña = findViewById(R.id.editTextContraseña);
        c_contraseña = findViewById(R.id.editTextConfirmarCont);
        aceptar = findViewById(R.id.buttonAceptar);

        aceptar.setOnClickListener(v -> {
            if (nombre_u.getText().toString().equals("") || apellido_p.getText().toString().equals("") || apellido_m.getText().toString().equals("") || edad.getText().toString().equals("") || sexo.getText().toString().equals("") || n_telefono.getText().toString().equals("") || c_electronico.getText().toString().equals("") || n_usuario.getText().toString().equals("") || contraseña.getText().toString().equals("") || c_contraseña.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Por favor rellene todos los campos.", Toast.LENGTH_SHORT).show();
            } else if (contraseña.getText().toString().equals(c_contraseña.getText().toString())) {
                agrearUsuario();
                irLogin(v);
            } else if (!contraseña.getText().toString().equals(c_contraseña.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void agrearUsuario() {
        ClaseConexion obj = new ClaseConexion();
        try {
            PreparedStatement reg = obj.conexionBD().prepareStatement("insert into usuarios values (?,?,?,?,?,?,?,?,?,null)");
            reg.setString(1, nombre_u.getText().toString());
            reg.setString(2, apellido_p.getText().toString());
            reg.setString(3, apellido_m.getText().toString());
            reg.setString(4, edad.getText().toString());
            reg.setString(5, sexo.getText().toString());
            reg.setString(6, n_telefono.getText().toString());
            reg.setString(7, c_electronico.getText().toString());
            reg.setString(8, n_usuario.getText().toString());
            reg.setString(9, contraseña.getText().toString());
            //reg.setByte(10, (byte) R.drawable.usuario);
            reg.executeUpdate();
            Toast.makeText(getApplicationContext(), "Registrado exitosamente.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /*public void validarUsuario() {
        con = obj.conexionBD();
        try {
            String sqConsult = "select nombrede_usuario from usuarios";
            Statement stat2 = con.createStatement();
            ResultSet res2 = stat2.executeQuery(sqConsult);
            while (res2.next()) {
                if (res2.getString("nombrede_usuario").equals(n_usuario.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "El nombre de usuario esta en uso.", Toast.LENGTH_SHORT).show();
                } else if (!res2.getString("nombrede_usuario").equals(n_usuario.getText().toString())) {
                    agrearUsuario();
                }
            }
        } catch (Exception e) {
            Log.e("SQL error", e.getMessage());
        }
    }*/

    public void irLogin(View view) {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
        finish();
    }

}
