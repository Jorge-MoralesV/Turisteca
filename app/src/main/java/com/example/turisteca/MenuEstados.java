package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class MenuEstados extends AppCompatActivity {
    private Connection con;
    Button botonPerfil;
    Button boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9, boton10, boton11, boton12, boton13, boton14, boton15, boton16, boton17, boton18, boton19, boton20, boton21, boton22, boton23, boton24, boton25, boton26, boton27, boton28, boton29, boton30, boton31;
    TextView nomb_usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_estados);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        botonPerfil = findViewById(R.id.botonUsuario);
        nomb_usr = findViewById(R.id.nombre_usr);

        boton1 = findViewById(R.id.buttonEstado1);
        boton2 = findViewById(R.id.buttonEstado2);
        boton3 = findViewById(R.id.buttonEstado3);
        boton4 = findViewById(R.id.buttonEstado4);
        boton5 = findViewById(R.id.buttonEstado5);
        boton6 = findViewById(R.id.buttonEstado6);
        boton7 = findViewById(R.id.buttonEstado7);
        boton8 = findViewById(R.id.buttonEstado8);
        boton9 = findViewById(R.id.buttonEstado9);
        boton10 = findViewById(R.id.buttonEstado10);
        boton11 = findViewById(R.id.buttonEstado11);
        boton12 = findViewById(R.id.buttonEstado12);
        boton13 = findViewById(R.id.buttonEstado13);
        boton14 = findViewById(R.id.buttonEstado14);
        boton15 = findViewById(R.id.buttonEstado15);
        boton16 = findViewById(R.id.buttonEstado16);
        boton17 = findViewById(R.id.buttonEstado17);
        boton18 = findViewById(R.id.buttonEstado18);
        boton19 = findViewById(R.id.buttonEstado19);
        boton20 = findViewById(R.id.buttonEstado20);
        boton21 = findViewById(R.id.buttonEstado21);
        boton22 = findViewById(R.id.buttonEstado22);
        boton23 = findViewById(R.id.buttonEstado23);
        boton24 = findViewById(R.id.buttonEstado24);
        boton25 = findViewById(R.id.buttonEstado25);
        boton26 = findViewById(R.id.buttonEstado26);
        boton27 = findViewById(R.id.buttonEstado27);
        boton28 = findViewById(R.id.buttonEstado28);
        boton29 = findViewById(R.id.buttonEstado29);
        boton30 = findViewById(R.id.buttonEstado30);
        boton31 = findViewById(R.id.buttonEstado31);

        boton1.setOnClickListener(View -> {
            String estadoname = "Aguascalientes";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton2.setOnClickListener(View -> {
            String estadoname = "Baja California Norte";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton3.setOnClickListener(View -> {
            String estadoname = "Baja California Sur";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton4.setOnClickListener(View -> {
            String estadoname = "Campeche";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton5.setOnClickListener(View -> {
            String estadoname = "Chiapas";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton6.setOnClickListener(View -> {
            String estadoname = "Chihuahua";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton7.setOnClickListener(View -> {
            String estadoname = "Coahuila";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton8.setOnClickListener(View -> {
            String estadoname = "Colima";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton9.setOnClickListener(View -> {
            String estadoname = "Durango";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton10.setOnClickListener(View -> {
            String estadoname = "Estado de México";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton11.setOnClickListener(View -> {
            String estadoname = "Guanajuato";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton12.setOnClickListener(View -> {
            String estadoname = "Guerrero";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton13.setOnClickListener(View -> {
            String estadoname = "Hidalgo";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton14.setOnClickListener(View -> {
            String estadoname = "Jalisco";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton15.setOnClickListener(View -> {
            String estadoname = "Michoacán";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton16.setOnClickListener(View -> {
            String estadoname = "Morelos";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton17.setOnClickListener(View -> {
            String estadoname = "Nayarit";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton18.setOnClickListener(View -> {
            String estadoname = "Nuevo León";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton19.setOnClickListener(View -> {
            String estadoname = "Oaxaca";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton20.setOnClickListener(View -> {
            String estadoname = "Puebla";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton21.setOnClickListener(View -> {
            String estadoname = "Querétaro";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton22.setOnClickListener(View -> {
            String estadoname = "Quintana Roo";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton23.setOnClickListener(View -> {
            String estadoname = "San Luis Potosí";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton24.setOnClickListener(View -> {
            String estadoname = "Sinaloa";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton25.setOnClickListener(View -> {
            String estadoname = "Sonora";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton26.setOnClickListener(View -> {
            String estadoname = "Tabasco";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton27.setOnClickListener(View -> {
            String estadoname = "Tamaulipas";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton28.setOnClickListener(View -> {
            String estadoname = "Tlaxcala";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton29.setOnClickListener(View -> {
            String estadoname = "Veracruz";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton30.setOnClickListener(View -> {
            String estadoname = "Yucatán";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        boton31.setOnClickListener(View -> {
            String estadoname = "Zacatecas";
            String usuario = getIntent().getStringExtra("id");
            Intent menu = new Intent(this, Menu_Ciudades.class);
            menu.putExtra("id", usuario);
            menu.putExtra("estado", estadoname);
            startActivity(menu);
        });

        botonPerfil.setOnClickListener(View -> {
            String usuario = getIntent().getStringExtra("id");
            Intent perfil = new Intent(this, PerfilUsuario.class);
            perfil.putExtra("id", usuario);
            startActivity(perfil);
        });

        mostrarDatos();

    }

    public void mostrarDatos() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        try {
            String usr = getIntent().getStringExtra("id");
            String sqConsult = "select * from usuarios where id_usuario = '" + usr + "'";
            Statement stat2 = con.createStatement();
            ResultSet res2 = stat2.executeQuery(sqConsult);
            while (res2.next()) {
                if (res2.getString(1).equals(usr)) {
                    nomb_usr.setText(res2.getString(9));
                }
            }
        } catch (Exception e) {
            Log.e("SQL error", e.getMessage());
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}