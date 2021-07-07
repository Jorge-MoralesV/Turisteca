package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Info_Ciudad extends AppCompatActivity implements RecyclerViewAdapterLugares.RecyclerItemClick, SearchView.OnQueryTextListener {

    private Connection con;
    private TextView nombre_ciudad;
    private ImageView img_ciudad;
    private TextView descripcion_ciudad;
    private Button maps;
    private String enlace;

    private SearchView searchViewl;
    private RecyclerView recyclerViewLugar;
    private RecyclerViewAdapterLugares adaptadorLugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ciudad);

        nombre_ciudad = findViewById(R.id.mostrarNombreLugar);
        descripcion_ciudad = findViewById(R.id.mostrarDescripcion);
        img_ciudad = findViewById(R.id.mostrarImagenCiudad);
        maps = findViewById(R.id.buttonMaps);
        searchViewl = findViewById(R.id.searchLugar);
        recyclerViewLugar = findViewById(R.id.recycler_view_lugares);

        recyclerViewLugar.setLayoutManager(new LinearLayoutManager(this));
        adaptadorLugar = new RecyclerViewAdapterLugares(obtenerDatosBDL(), this);
        recyclerViewLugar.setAdapter(adaptadorLugar);

        initListener();

        maps.setOnClickListener(V -> {
            Uri ur = Uri.parse(enlace);
            Intent i = new Intent(Intent.ACTION_VIEW, ur);
            startActivity(i);
        });

        mostrarDatosCiudad();
    }

    public void mostrarDatosCiudad() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        try {
            Datos_Ciudades detail = (Datos_Ciudades) getIntent().getExtras().getSerializable("nom_ciudad");
            String ciudad = detail.getNombre();
            String sqConsult = "select * from ciudades where nombre_ciudad = '" + ciudad + "'";
            Statement stat2 = con.createStatement();
            ResultSet res2 = stat2.executeQuery(sqConsult);
            while (res2.next()) {
                if (res2.getString("nombre_ciudad").equals(ciudad)) {
                    nombre_ciudad.setText(res2.getString(2));
                    descripcion_ciudad.setText(res2.getString(3));
                    enlace = (res2.getString(4));
                    if (res2.getString("nombre_ciudad").equals("Cd. Mante")) {
                        img_ciudad.setImageResource(R.drawable.ciudad_mante);
                    } else if (res2.getString("nombre_ciudad").equals("Tampico")) {
                        img_ciudad.setImageResource(R.drawable.ciudadtampico);
                    } else if (res2.getString("nombre_ciudad").equals("Matamoros")) {
                        img_ciudad.setImageResource(R.drawable.ciudad_matamoros);
                    } else if (res2.getString("nombre_ciudad").equals("Tuxpan")) {
                        img_ciudad.setImageResource(R.drawable.ciudad_tuxpan);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("SQL error", e.getMessage());
        }
    }

    public List<Datos_Lugar> obtenerDatosBDL() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        List<Datos_Lugar> lugar = new ArrayList<>();
        try {
            Datos_Ciudades detail2 = (Datos_Ciudades) getIntent().getExtras().getSerializable("nom_ciudad");
            String ciudad2 = detail2.getNombre();
            String sqConsult2 = "select  * from lugares where ciudad_perteneciente = '" + ciudad2 + "'";
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(sqConsult2);
            Toast.makeText(getApplicationContext(), ciudad2, Toast.LENGTH_SHORT).show();
            while (rs2.next()) {
                if (rs2.getString("ciudad_perteneciente").equals("")) {
                    Toast.makeText(getApplicationContext(), "No hay datos", Toast.LENGTH_SHORT).show();
                } else if (rs2.getString("ciudad_perteneciente").equals("Cd. Mante")) {
                    if (rs2.getString("nombre_lugar").equals("Plaza Principal")) {
                        lugar.add(new Datos_Lugar(rs2.getString("nombre_lugar"), R.drawable.icon_plazamante));
                    } else if (rs2.getString("nombre_lugar").equals("Estadio Zaragoza")) {
                        lugar.add(new Datos_Lugar(rs2.getString("nombre_lugar"), R.drawable.icon_zaragoza));
                    } else if (rs2.getString("nombre_lugar").equals("La Difusora")) {
                        lugar.add(new Datos_Lugar(rs2.getString("nombre_lugar"), R.drawable.icon_difusora));
                    }
                } else if (rs2.getString("ciudad_perteneciente").equals("Tuxpan")) {
                    if (rs2.getString("nombre_lugar").equals("Parque Reforma")) {
                        lugar.add(new Datos_Lugar(rs2.getString("nombre_lugar"), R.drawable.icon_parque_reforma));
                    }
                }
            }
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }
        return lugar;
    }

    private void initListener() {
        searchViewl.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adaptadorLugar.filter(newText);
        return false;
    }

    @Override
    public void itemClick(Datos_Lugar item) {
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, Info_Lugar.class);
        menu.putExtra("id", usr);
        menu.putExtra("nom_lugar", item);
        startActivity(menu);
    }

    public void irEstados(View view) {
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, MenuEstados.class);
        menu.putExtra("id", usr);
        startActivity(menu);
    }

}