package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RecuperarContActivity extends AppCompatActivity {

    private Connection con;
    private EditText nom_usuario, correo;
    private Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cont);

        nom_usuario = findViewById(R.id.ingNomUsusario);
        correo = findViewById(R.id.ingCorreo);
        aceptar = findViewById(R.id.botonAceptar);

        aceptar.setOnClickListener(V -> {
            buscarUsuario();
        });

    }

    public void buscarUsuario() {
        ClaseConexion obj = new ClaseConexion();
        int id;
        con = obj.conexionBD();
        if (con == null) {
            Toast.makeText(getApplicationContext(), "Verifica tu conexión a internet.", Toast.LENGTH_SHORT).show();
        } else if (nom_usuario.getText().toString().equals("") && correo.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Los campos están vacios.", Toast.LENGTH_SHORT).show();
        } else {
            try {
                String sqConsult = "SELECT id_usuario FROM usuarios WHERE nombrede_usuario = '" + nom_usuario.getText().toString() + "' AND correo_electr = '" + correo.getText().toString() + "'";
                Statement stat = con.createStatement();
                ResultSet res = stat.executeQuery(sqConsult);

                if (res.next()) {
                    id = Integer.parseInt(res.getString(1));
                    Intent conf = new Intent(getApplicationContext(), ConfirmarCambioContr.class);
                    conf.putExtra("id", String.valueOf(id));
                    startActivity(conf);
                } else {
                    Toast.makeText(getApplicationContext(), "El usuario o el correo son incorrectos.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("SQL error", e.getMessage());
            }
        }
    }

}