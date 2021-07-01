package com.example.turisteca;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class ClaseConexion {

    public Connection conexionBD() {
        Connection conexion = null;
        String cadena = "jdbc:jtds:sqlserver://sql5097.site4now.net;databaseName=db_a750db_turisteca;user=db_a750db_turisteca_admin;password=turispass99;integratedSecurity=true;";
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(cadena);
        } catch (Exception e) {
           e.getMessage();
        }
        return conexion;
    }
}
