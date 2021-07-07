package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Info_Ciudad extends AppCompatActivity {

    Connection con;
    private TextView nombre_ciudad;
    private ImageView img_ciudad;
    private TextView descripcion_ciudad;
    private Button maps;
    private String enlace;
    private Datos_Ciudades detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ciudad);

        nombre_ciudad = findViewById(R.id.mostrarNombreCiudad);
        descripcion_ciudad = findViewById(R.id.mostrarDescripcion);
        img_ciudad = findViewById(R.id.mostrarImagenCiudad);
        maps = findViewById(R.id.buttonMaps);

        /*detail = (Datos_Ciudades) getIntent().getExtras().getSerializable("nom_ciudad");
        Toast.makeText(getApplicationContext(),detail.getNombre(),Toast.LENGTH_SHORT).show();*/

        maps.setOnClickListener(V->{
            Uri ur = Uri.parse(enlace);
            Intent i = new Intent(Intent.ACTION_VIEW, ur);
            startActivity(i);
        });

        mostrarDatosCiudad();

    }

    public void mostrarDatosCiudad(){
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();try {
            detail = (Datos_Ciudades) getIntent().getExtras().getSerializable("nom_ciudad");
            String sqConsult = "select * from ciudades where nombre_ciudad = '" + detail.getNombre() + "'";
            Statement stat2 = con.createStatement();
            ResultSet res2 = stat2.executeQuery(sqConsult);

            while (res2.next()) {
                if (res2.getString("nombre_ciudad").equals(detail.getNombre())) {
                    nombre_ciudad.setText(res2.getString(2));
                    descripcion_ciudad.setText(res2.getString(3));
                    enlace=(res2.getString(4));
                    img_ciudad.setImageResource(R.drawable.ciudadbeta);
                }
            }

        } catch (Exception e) {
            Log.e("SQL error", e.getMessage());
        }
    }

    public void irEstados(View view) {
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, MenuEstados.class);
        menu.putExtra("id", usr);
        startActivity(menu);
    }

}