package com.example.turisteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecuperarContActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cont);
    }

    public void irLogin(View view){
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }

}