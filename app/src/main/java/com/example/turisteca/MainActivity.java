package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    ClaseConexion obj = new ClaseConexion();

    private EditText nombre_u, contraseña;
    private Button ingresar;
    private Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre_u = findViewById(R.id.editTextNombreU);
        contraseña = findViewById(R.id.editTextPassword);
        ingresar = findViewById(R.id.buttonIngresar);


        ingresar.setOnClickListener(v -> {
            Ingresar();
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.moveTaskToBack(true);
            this.finishAffinity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void Ingresar() {
        int id;
        con = obj.conexionBD();
        if (con == null) {
            Toast.makeText(getApplicationContext(), "Verifica tu conexión a internet.", Toast.LENGTH_SHORT).show();
        } else if (nombre_u.getText().toString().equals("") && contraseña.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Los campos están vacios.", Toast.LENGTH_SHORT).show();
        } else if (nombre_u.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Ingrese el nombre de usuario.", Toast.LENGTH_SHORT).show();
        } else if (contraseña.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Ingrese la contraseña.", Toast.LENGTH_SHORT).show();
        } else {

            try {

                String sqConsult = "SELECT id_usuario FROM usuarios WHERE nombrede_usuario = '" + nombre_u.getText().toString() + "' AND contraseña = '" + contraseña.getText().toString() + "'";
                Statement stat = con.createStatement();
                ResultSet res = stat.executeQuery(sqConsult);

                if (res.next()) {
                    id = Integer.parseInt(res.getString(1));
                    Intent menu = new Intent(getApplicationContext(), MenuEstados.class);
                    menu.putExtra("id", String.valueOf(id));
                    startActivity(menu);
                    //finish();

                } else {

                    Toast.makeText(getApplicationContext(), "El usuario o la contraseña son incorrectos.", Toast.LENGTH_SHORT).show();

                }

            } catch (Exception e) {
                Log.e("SQL error", e.getMessage());
            }
        }
    }


    public void irRegistro(View view) {
        Intent registro = new Intent(this, RegistroActivity.class);
        startActivity(registro);
    }

    public void recuperarCont(View view) {
        Intent recuperar = new Intent(this, RecuperarContActivity.class);
        startActivity(recuperar);
    }

    public void irInvitado(View view) {
        Intent invitado = new Intent(this, MenuEstados.class);
        startActivity(invitado);
        finish();
    }

}

