package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ActualizarInformacion extends AppCompatActivity {
    private Connection con;
    EditText act_nombre_u, act_apellido_p, act_apellido_m, act_edad, act_sexo, act_n_telefono, act_c_electronico, act_n_usuario, act_contraseña, act_c_contraseña;
    Button aplicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actulizar_informacion);

        act_nombre_u = findViewById(R.id.editTextActNombre);
        act_apellido_p = findViewById(R.id.editTextActApellidoP);
        act_apellido_m = findViewById(R.id.editTextActApellidoM);
        act_edad = findViewById(R.id.editTextActEdad);
        act_sexo = findViewById(R.id.editTextActSexo);
        act_n_telefono = findViewById(R.id.editTextActTelefono);
        act_c_electronico = findViewById(R.id.editTextActCorreoE);
        act_n_usuario = findViewById(R.id.editTextActNombreU);
        act_contraseña = findViewById(R.id.editTextActContraseña);
        act_c_contraseña = findViewById(R.id.editTextActConfirmarCont);
        aplicar = findViewById(R.id.buttonAceptarCamb);

        aplicar.setOnClickListener(v -> {
            if (act_nombre_u.getText().toString().equals("") || act_apellido_p.getText().toString().equals("") || act_apellido_m.getText().toString().equals("") || act_edad.getText().toString().equals("") || act_sexo.getText().toString().equals("") || act_n_telefono.getText().toString().equals("") || act_c_electronico.getText().toString().equals("") || act_n_usuario.getText().toString().equals("") || act_contraseña.getText().toString().equals("") || act_c_contraseña.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Por favor rellene todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                if (act_contraseña.getText().toString().equals(act_c_contraseña.getText().toString())) {
                    actualizarUsuario();
                    irLogin(v);
                } else if (!act_contraseña.getText().toString().equals(act_c_contraseña.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                }
            }
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
                    act_nombre_u.setText(res2.getString(2));
                    act_apellido_p.setText(res2.getString(3));
                    act_apellido_m.setText(res2.getString(4));
                    act_edad.setText(res2.getString(5));
                    act_sexo.setText(res2.getString(6));
                    act_n_telefono.setText(res2.getString(7));
                    act_c_electronico.setText(res2.getString(8));
                    act_n_usuario.setText(res2.getString(9));
                    act_contraseña.setText(res2.getString(10));
                }
            }

        } catch (Exception e) {
            Log.e("SQL error", e.getMessage());
        }
    }

    public void actualizarUsuario() {
        ClaseConexion obj = new ClaseConexion();
        con = obj.conexionBD();
        try {
            String usr = getIntent().getStringExtra("id");
            String sqConsult = "update usuarios set nombre_usuario = '" + act_nombre_u.getText().toString() + "',"
                    + "apellido_p = '" + act_apellido_p.getText().toString() + "',"
                    + "apellido_m = '" + act_apellido_m.getText().toString() + "',"
                    + "edad_usuario = '" + act_edad.getText().toString() + "',"
                    + "sexo_usuario = '" + act_sexo.getText().toString() + "',"
                    + "n_telefono = '" + act_n_telefono.getText().toString() + "',"
                    + "correo_electr = '" + act_c_electronico.getText().toString() + "',"
                    + "nombrede_usuario = '" + act_n_usuario.getText().toString() + "',"
                    + "contraseña = '" + act_contraseña.getText().toString() + "'"
                    + "where id_usuario = '" + usr + "'";
            PreparedStatement reg = con.prepareStatement(sqConsult);
            reg.executeUpdate();
            Toast.makeText(getApplicationContext(), "Información actualizada.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void irLogin(View view) {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
        finish();
    }


}