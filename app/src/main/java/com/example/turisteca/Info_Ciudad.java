package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Info_Ciudad extends AppCompatActivity {

   /* private TextView nombre_ciudad;
    private ImageView img_ciudad;
    private TextView descripcion_ciudad;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ciudad);
    }

    public void irEstados(View view) {
        String usr = getIntent().getStringExtra("id");
        Intent menu = new Intent(this, MenuEstados.class);
        menu.putExtra("id", usr);
        startActivity(menu);
    }

}