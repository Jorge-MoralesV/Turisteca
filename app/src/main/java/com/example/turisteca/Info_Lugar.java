package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Info_Lugar extends AppCompatActivity {

    private Connection con;
    private TextView nombre_lugar;
    private ImageView img_lugar;
    private TextView descripcion_lugar;
    private Button maps;
    private String enlace;

    private RecyclerView recyclerViewComentarios;
    private RecyclerViewAdapterLugares adaptadorComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_lugar);

        nombre_lugar = findViewById(R.id.mostrarNombreLugar);
        descripcion_lugar = findViewById(R.id.mostrarDescripcionLugar);
        img_lugar = findViewById(R.id.mostrarImagenLugar);
        maps = findViewById(R.id.buttonMapsLugar);

        mostrarDatosLugar();
        maps.setOnClickListener(V -> {
            Uri ur = Uri.parse(enlace);
            Intent i = new Intent(Intent.ACTION_VIEW, ur);
            startActivity(i);
        });
    }

    public void mostrarDatosLugar() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        try {
            Datos_Lugar detail = (Datos_Lugar) getIntent().getExtras().getSerializable("nom_lugar");
            String lugar = detail.getNombrel();
            Toast.makeText(getApplicationContext(), lugar, Toast.LENGTH_SHORT).show();
            String sqConsult = "select * from lugares where nombre_lugar = '" + lugar + "'";
            Statement stat2 = con.createStatement();
            ResultSet res2 = stat2.executeQuery(sqConsult);
            while (res2.next()) {
                if (res2.getString("nombre_lugar").equals(lugar)) {
                    nombre_lugar.setText(res2.getString(2));
                    descripcion_lugar.setText(res2.getString(3));
                    enlace = (res2.getString(4));
                    if (res2.getString("nombre_lugar").equals("Plaza Principal")) {
                        img_lugar.setImageResource(R.drawable.plazamante);
                    } else if (res2.getString("nombre_lugar").equals("Estadio Zaragoza")) {
                        img_lugar.setImageResource(R.drawable.zaragoza);
                    } else if (res2.getString("nombre_lugar").equals("La Difusora")) {
                        img_lugar.setImageResource(R.drawable.balneariomante);
                    } else if (res2.getString("nombre_lugar").equals("Parque Reforma")) {
                        img_lugar.setImageResource(R.drawable.parque_reforma);
                    }
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