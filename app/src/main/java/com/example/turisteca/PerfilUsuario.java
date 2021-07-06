package com.example.turisteca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class PerfilUsuario extends AppCompatActivity {


    private TextView nombre, app, apm, edad, sexo, c_elec, num_tel, contr, nom_usur;
    private Connection con;
    private Button botonFoto;
    private ImageView foto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

        nombre = findViewById(R.id.IdmostrarNombre);
        app = findViewById(R.id.IdmostrarApellidoP);
        apm = findViewById(R.id.IdmostrarApellidoM);
        edad = findViewById(R.id.IdmostrarEdad);
        sexo = findViewById(R.id.IdmostrarSexo);
        num_tel = findViewById(R.id.IdmostrarTelefono);
        c_elec = findViewById(R.id.IdmostrarCorreo);
        nom_usur = findViewById(R.id.IdmostrarUsuario);
        contr = findViewById(R.id.IdmostrarContraseña);
        //botonFoto = findViewById(R.id.botonCamera);
        foto = findViewById(R.id.imageFoto);

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
                    nombre.setText(res2.getString(2));
                    app.setText(res2.getString(3));
                    apm.setText(res2.getString(4));
                    edad.setText(res2.getString(5));
                    sexo.setText(res2.getString(6));
                    num_tel.setText(res2.getString(7));
                    c_elec.setText(res2.getString(8));
                    nom_usur.setText(res2.getString(9));
                    contr.setText(res2.getString(10));
                    foto.setImageResource(R.drawable.usuario);
                }
            }

        } catch (Exception e) {
            Log.e("SQL error", e.getMessage());
        }
    }

   /* public void mostrarFoto(View view) {
        cargarImagen();
    }

    public void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Seleccione la aplicación"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            foto.setImageURI(path);
            ClaseConexion obj = new ClaseConexion();
            con = obj.conexionBD();
            try {
                String usr = getIntent().getStringExtra("id");
                String sqConsult = "update usuarios set foto_usuario = '" + foto.getImageMatrix() + "' where id_usuario = '" + usr + "'";
                PreparedStatement reg = con.prepareStatement(sqConsult);
                reg.executeUpdate();
                Toast.makeText(getApplicationContext(), "Foto actualizada.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    public void irInicio(View view) {
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, MenuEstados.class);
        menu.putExtra("id", usr);
        startActivity(menu);
    }

    public void actInformacion(View view) {
        String usr = getIntent().getStringExtra("id");
        Intent act = new Intent(this, ActualizarInformacion.class);
        act.putExtra("id", usr);
        startActivity(act);
    }

    public void irSugerencias(View view) {
        String usr = getIntent().getStringExtra("id");
        Intent sug = new Intent(this, EnviarSugerencia.class);
        sug.putExtra("id", usr);
        startActivity(sug);
    }

    public void cerrarSesion(View view) {
        preguntaCerrarSesion();
    }

    public void preguntaCerrarSesion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea cerrar sesión?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent inicio = new Intent(PerfilUsuario.this, MainActivity.class);
                startActivity(inicio);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}