package com.aluracursos.convertidor.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Usuario {
    private Datos datos;
    private Historial historial;
    private Scanner scanner;

    public Usuario(Datos datos, Historial historial) {
        this.datos = datos;
        this.historial = historial;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Mostrar Historial");
            System.out.println("8) Salir");
            System.out.print("Elija una opción válida: ");
            System.out.println("************************************");

            int opcion = scanner.nextInt();

            if (opcion == 8) break;

            String monedaOrigen = "", monedaDestino = "";
            if (opcion == 1) { monedaOrigen = "USD"; monedaDestino = "ARS"; }
            else if (opcion == 2) { monedaOrigen = "ARS"; monedaDestino = "USD"; }
            else if (opcion == 3) { monedaOrigen = "USD"; monedaDestino = "BRL"; }
            else if (opcion == 4) { monedaOrigen = "BRL"; monedaDestino = "USD"; }
            else if (opcion == 5) { monedaOrigen = "USD"; monedaDestino = "COP"; }
            else if (opcion == 6) { monedaOrigen = "COP"; monedaDestino = "USD"; }
            else if (opcion == 7) {
                historial.mostrarHistorial();
                continue;
            } else {
                System.out.println("Opción no válida.");
                continue;
            }

            System.out.print("Ingrese el monto a convertir: ");
            double monto = scanner.nextDouble();

            double tasa = datos.obtenerTasa(monedaOrigen, monedaDestino);
            double resultado = monto * tasa;

            System.out.printf("Resultado: %.2f %s\n", resultado, monedaDestino);

            String registro = String.format("%s: %.2f %s a %.2f %s",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    monto, monedaOrigen, resultado, monedaDestino);
            historial.agregarRegistro(registro);
        }
    }
}
