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

    /*public void getStringToBitmap() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        try {
            String usr = getIntent().getStringExtra("id");
            String sqConsult = "select * from usuarios where id_usuario = '" + usr + "'";
            Statement stat2 = con.createStatement();
            ResultSet res2 = stat2.executeQuery(sqConsult);
            if (res2.getString(1).equals(usr)) {
                if (res2.getString(11) != null) {
                    byte[] byteCode = Base64.decode(res2.getString(11), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(byteCode, 0, byteCode.length);
                    foto.setImageBitmap(bitmap);
                } else {
                    foto.setImageResource(R.drawable.usuario);
                }
            }
        } catch (Exception e) {
            Log.e("SQL error", e.getMessage());
        }
    }*/

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void irCiudades(View view) {
        String estadoname = getIntent().getStringExtra("estado");
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, Menu_Ciudades.class);
        menu.putExtra("id", usr);
        menu.putExtra("estado", estadoname);
        startActivity(menu);
    }

}