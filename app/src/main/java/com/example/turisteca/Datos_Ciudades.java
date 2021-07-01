package com.example.turisteca;

import java.io.Serializable;

public class Datos_Ciudades implements Serializable {

    private String nombre;
    private int foto;

    public Datos_Ciudades() {

    }

    public Datos_Ciudades(String nombre, int foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}