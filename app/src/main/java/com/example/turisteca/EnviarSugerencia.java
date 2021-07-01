package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EnviarSugerencia extends AppCompatActivity {
    private Connection con;
    private EditText nombre_lugar, descripcion;
    private String nombre_usuario;
    private Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_sugerencia);

        nombre_lugar = findViewById(R.id.editTextNomLugar);
        descripcion = findViewById(R.id.editTextDescripcion);
        aceptar = findViewById(R.id.buttonEnviar);

        aceptar.setOnClickListener(V -> {
            mandarSugerencia();
            finish();
        });

    }

    public void mandarSugerencia() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        try {
            String usr = getIntent().getStringExtra("id");
            String sqConsult = "select * from usuarios where id_usuario = '" + usr + "'";
            Statement stat2 = con.createStatement();
            ResultSet res2 = stat2.executeQuery(sqConsult);
            while (res2.next()) {
                if(nombre_lugar.length()==0 || descripcion.length()==0){
                    String usuario = getIntent().getStringExtra("id");
                    Intent perfil = new Intent(this, PerfilUsuario.class);
                    perfil.putExtra("id", usuario);
                    startActivity(perfil);
                }else {
                    PreparedStatement reg = con.prepareStatement("insert into peticiones values (?,?,?)");
                    if (res2.getString("id_usuario").equals(usr)) {
                        reg.setString(1, nombre_usuario = res2.getString(9));
                        reg.setString(2, nombre_lugar.getText().toString());
                        reg.setString(3, descripcion.getText().toString());
                        reg.executeUpdate();
                        Toast.makeText(getApplicationContext(), "Gracias, se tomará en cuenta.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void irPerfil(View view) {
        String usuario = getIntent().getStringExtra("id");
        Intent perfil = new Intent(this, PerfilUsuario.class);
        perfil.putExtra("id", usuario);
        startActivity(perfil);
    }

}