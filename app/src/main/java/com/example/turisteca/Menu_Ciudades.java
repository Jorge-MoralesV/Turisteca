package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Menu_Ciudades extends AppCompatActivity implements RecyclerViewAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {

    private SearchView searchView;
    private RecyclerView recyclerViewCiudad;
    private RecyclerViewAdapter adaptadorCiudad;

    private Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ciudades);

        searchView = findViewById(R.id.searchCiudad);

        recyclerViewCiudad = findViewById(R.id.recycler_view_ciudades);
        recyclerViewCiudad.setLayoutManager(new LinearLayoutManager(this));

        adaptadorCiudad = new RecyclerViewAdapter(obtenerDatosBD(), this);
        recyclerViewCiudad.setAdapter(adaptadorCiudad);

        initListener();

    }

    public List<Datos_Ciudades> obtenerDatosBD() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        List<Datos_Ciudades> ciudad = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ciudades");

            while (rs.next()) {
                if(rs.getString("nombre_ciudad").equals("Cd. Mante")){
                    ciudad.add(new Datos_Ciudades(rs.getString("nombre_ciudad"), R.drawable.mante));
                }else if(rs.getString("nombre_ciudad").equals("Tampico")){
                    ciudad.add(new Datos_Ciudades(rs.getString("nombre_ciudad"), R.drawable.tampico));
                }else if(rs.getString("nombre_ciudad").equals("Matamoros")) {
                    ciudad.add(new Datos_Ciudades(rs.getString("nombre_ciudad"), R.drawable.matamoros));
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
        //Toast.makeText(getApplicationContext(), item.getNombre(), Toast.LENGTH_SHORT).show();
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, Info_Ciudad.class);
        menu.putExtra("id", usr);
        startActivity(menu);
    }

  /* public List<DatosCiudad> obtenerDatos() {
        List<DatosCiudad> ciudad = new ArrayList<>();
        ciudad.add(new DatosCiudad("Mante", R.drawable.mante));
        ciudad.add(new DatosCiudad("Tampico", R.drawable.tampico));

        return ciudad;
    }*/

    public void irEstados(View view) {
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, MenuEstados.class);
        menu.putExtra("id", usr);
        startActivity(menu);
    }

}