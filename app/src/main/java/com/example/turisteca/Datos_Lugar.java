package com.example.turisteca;

import java.io.Serializable;

public class Datos_Lugar implements Serializable {

    private String nombrel;
    private int fotol;

    public Datos_Lugar() {

    }

    public Datos_Lugar(String nombrel, int fotol) {
        this.nombrel = nombrel;
        this.fotol = fotol;
    }

    public String getNombrel() {
        return nombrel;
    }

    public void setNombrel(String nombrel) {
        this.nombrel = nombrel;
    }

    public int getFotol() {
        return fotol;
    }

    public void setFotol(int fotol) {
        this.fotol = fotol;
    }
}
