package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Comentarios extends AppCompatActivity {
    private Connection con;
    private EditText comentario;
    private Button aceptar;
    private String nombre_usuario;
    private String nombre_lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        comentario = findViewById(R.id.editTextComentario);
        aceptar = findViewById(R.id.buttonEnviarC);

        aceptar.setOnClickListener(V -> {
            mandarComentario();
            finish();
        });

    }

    public void mandarComentario() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        try {

            String usr = getIntent().getStringExtra("id");
            String lugar = getIntent().getStringExtra("nom_lugar");

            String sqConsult = "select * from usuarios where id_usuario = '" + usr + "'";
            String sqConsult2 = "select * from lugares where nombre_lugar = '" + lugar + "'";

            Statement stat = con.createStatement();
            Statement stat2 = con.createStatement();

            ResultSet res = stat.executeQuery(sqConsult);
            ResultSet res2 = stat2.executeQuery(sqConsult2);

            while (res.next() && res2.next()) {

                if (comentario.length() == 0) {
                    String lugar2 = getIntent().getStringExtra("nom_lugar");
                    String usr2 = getIntent().getStringExtra("id");
                    Intent lug = new Intent(this, Info_Lugar.class);
                    lug.putExtra("id", usr2);
                    lug.putExtra("nom_lugar", lugar2);
                    startActivity(lug);

                } else {
                    PreparedStatement reg = con.prepareStatement("insert into comentarios (usuario_comenta, contenido_com, lugar_perteneciente) values (?,?,?)");
                    if (res.getString("id_usuario").equals(usr) && res2.getString("nombre_lugar").equals(lugar)) {
                        reg.setString(1, nombre_usuario = res.getString("nombrede_usuario"));
                        reg.setString(2, comentario.getText().toString());
                        reg.setString(3, nombre_lugar = res2.getString("nombre_lugar"));
                        reg.executeUpdate();
                        Toast.makeText(getApplicationContext(), "Gracias por compartir tu experiencia", Toast.LENGTH_LONG).show();
                    }
                }

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No hay sesion activa", Toast.LENGTH_SHORT).show();
        }
    }

    public void irLugar(View view) {
        Datos_Lugar detail = (Datos_Lugar) getIntent().getExtras().getSerializable("nom_lugar");
        String lugar = detail.getNombrel();
        String usr = getIntent().getStringExtra("id");
        Intent lug = new Intent(this, Info_Lugar.class);
        lug.putExtra("id", usr);
        lug.putExtra("nom_lugar", lugar);
        startActivity(lug);
    }

}