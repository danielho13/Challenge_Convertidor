package com.aluracursos.convertidor.modelos;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Scanner;

public class Usuario {
    public void mostrarMenu() {
        Scanner escaner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("**********************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("**********************************************");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.print("\nElija una opción válida: ");
            opcion = escaner.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.println("Ingrese el monto a convertir: ");
                double monto = escaner.nextDouble();
                realizarConversion(opcion, monto);
            } else if (opcion == 7) {
                System.out.println("¡Gracias por usar el conversor de monedas!");
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void realizarConversion(int opcion, double monto) {
        Datos servicioDatos = new Datos();
        try {
            String tasas = servicioDatos.obtenerTasas();
            JsonObject jsonObject = JsonParser.parseString(tasas).getAsJsonObject();
            JsonObject tasasDeCambio = jsonObject.getAsJsonObject("conversion_rates");

            String monedaOrigen = "";
            String monedaDestino = "";

            // Definir las monedas según la opción elegida
            switch (opcion) {
                case 1 -> { // Dólar => Peso argentino
                    monedaOrigen = "USD";
                    monedaDestino = "ARS";
                }
                case 2 -> { // Peso argentino => Dólar
                    monedaOrigen = "ARS";
                    monedaDestino = "USD";
                }
                case 3 -> { // Dólar => Real brasileño
                    monedaOrigen = "USD";
                    monedaDestino = "BRL";
                }
                case 4 -> { // Real brasileño => Dólar
                    monedaOrigen = "BRL";
                    monedaDestino = "USD";
                }
                case 5 -> { // Dólar => Peso colombiano
                    monedaOrigen = "USD";
                    monedaDestino = "COP";
                }
                case 6 -> { // Peso colombiano => Dólar
                    monedaOrigen = "COP";
                    monedaDestino = "USD";
                }
            }

            // Extraer las tasas de las monedas de origen y destino
            double tasaMonedaOrigen = tasasDeCambio.get(monedaOrigen).getAsDouble();
            double tasaMonedaDestino = tasasDeCambio.get(monedaDestino).getAsDouble();

            // Convertir el monto
            double montoConvertido = (monto / tasaMonedaOrigen) * tasaMonedaDestino;

            // Mostrar el resultado
            System.out.printf("El monto %.2f %s es igual a %.2f %s%n", monto, monedaOrigen, montoConvertido, monedaDestino);

        } catch (Exception e) {
            System.out.println("Error al obtener las tasas: " + e.getMessage());
        }
    }
}
