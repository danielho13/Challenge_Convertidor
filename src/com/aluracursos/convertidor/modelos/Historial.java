package com.aluracursos.convertidor.modelos;

import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<String> registros;

    public Historial() {
        this.registros = new ArrayList<>();
    }

    public void agregarRegistro(String registro) {
        registros.add(registro);
    }

    public void mostrarHistorial() {
        System.out.println("Historial de Conversiones:");
        for (String registro : registros) {
            System.out.println(registro);
        }
        System.out.println("***************************");
    }
}

