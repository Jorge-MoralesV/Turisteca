package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Menu_Ciudades extends AppCompatActivity implements RecyclerViewAdapterCiudades.RecyclerItemClick, SearchView.OnQueryTextListener {

    private SearchView searchView;
    private RecyclerView recyclerViewCiudad;
    private RecyclerViewAdapterCiudades adaptadorCiudad;
    private Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ciudades);
        searchView = findViewById(R.id.searchCiudad);
        recyclerViewCiudad = findViewById(R.id.recycler_view_ciudades);
        recyclerViewCiudad.setLayoutManager(new LinearLayoutManager(this));
        adaptadorCiudad = new RecyclerViewAdapterCiudades(obtenerDatosBD(), this);
        recyclerViewCiudad.setAdapter(adaptadorCiudad);
        initListener();
    }

    public List<Datos_Ciudades> obtenerDatosBD() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        List<Datos_Ciudades> ciudad = new ArrayList<>();
        try {
            String estado = getIntent().getStringExtra("estado");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ciudades where estado_perteneciente = '" + estado + "'");
            while (rs.next()) {
                if (rs.getString("nombre_ciudad").equals("Cd. Mante")) {
                    ciudad.add(new Datos_Ciudades(rs.getString("nombre_ciudad"), R.drawable.mante));
                } else if (rs.getString("nombre_ciudad").equals("Tampico")) {
                    ciudad.add(new Datos_Ciudades(rs.getString("nombre_ciudad"), R.drawable.tampico));
                } else if (rs.getString("nombre_ciudad").equals("Matamoros")) {
                    ciudad.add(new Datos_Ciudades(rs.getString("nombre_ciudad"), R.drawable.matamoros));
                } else if (rs.getString("nombre_ciudad").equals("Tuxpan")) {
                    ciudad.add(new Datos_Ciudades(rs.getString("nombre_ciudad"), R.drawable.tuxpan));
                }
            }
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }
        return ciudad;
    }

    private void initListener() {
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adaptadorCiudad.filter(newText);
        return false;
    }

    @Override
    public void itemClick(Datos_Ciudades item) {
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, Info_Ciudad.class);
        menu.putExtra("id", usr);
        menu.putExtra("nom_ciudad", item);
        startActivity(menu);
    }

    public void irEstados(View view) {
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, MenuEstados.class);
        menu.putExtra("id", usr);
        startActivity(menu);
    }

}