package com.example.turisteca;

import java.io.Serializable;

public class Datos_Comentario implements Serializable {

    private String nombrec, comentarioc;
    private int fotoc;

    public Datos_Comentario() {

    }

    public Datos_Comentario(String nombrec, String comentarioc, int fotoc) {
        this.comentarioc = comentarioc;
        this.nombrec = nombrec;
        this.fotoc = fotoc;
    }

    public String getNombrec() {
        return nombrec;
    }

    public String getComentarioc() {
        return comentarioc;
    }

    public int getFotoc() {
        return fotoc;
    }

}
