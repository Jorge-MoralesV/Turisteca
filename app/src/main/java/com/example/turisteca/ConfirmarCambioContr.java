package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConfirmarCambioContr extends AppCompatActivity {

    private Connection con;

    private EditText contra, repcontra;
    private Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_cambio_contr);

        contra = findViewById(R.id.ingContra);
        repcontra = findViewById(R.id.ingRepContra);
        aceptar = findViewById(R.id.botonAceptar);

        aceptar.setOnClickListener(v -> {
            if (contra.getText().toString().equals("") || repcontra.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Por favor rellene todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                if (contra.getText().toString().equals(repcontra.getText().toString())) {
                    actualizarContra();
                    irLogin(v);
                } else if (!contra.getText().toString().equals(repcontra.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void actualizarContra() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        try {
            String usr = getIntent().getStringExtra("id");
            String sqConsult = "update usuarios set contraseña = '" + contra.getText().toString() + "'"
                    + "where id_usuario = '" + usr + "'";
            PreparedStatement reg = con.prepareStatement(sqConsult);
            reg.executeUpdate();
            Toast.makeText(getApplicationContext(), "Contraseña actualizada.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void irLogin(View view) {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
        finish();
    }


}