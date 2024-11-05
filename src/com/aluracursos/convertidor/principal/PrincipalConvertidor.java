package com.aluracursos.convertidor.principal;

import com.aluracursos.convertidor.modelos.Datos;
import com.aluracursos.convertidor.modelos.Usuario;
import com.aluracursos.convertidor.modelos.Historial;

public class PrincipalConvertidor {
    public static void main(String[] args) {
        Datos datos = new Datos("ad2f2ff5ceadfe9c89430bc4");
        Historial historial = new Historial();
        Usuario usuario = new Usuario(datos, historial);

        usuario.iniciar();
    }
}

