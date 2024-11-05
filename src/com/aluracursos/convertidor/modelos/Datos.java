package com.aluracursos.convertidor.modelos;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Datos {
    private String apiKey;

    public Datos(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obtenerTasa(String monedaOrigen, String monedaDestino) {
        String urlStr = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, monedaOrigen);

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) throw new RuntimeException("HttpResponseCode: " + responseCode);

            Scanner scanner = new Scanner(url.openStream());
            StringBuilder jsonStr = new StringBuilder();
            while (scanner.hasNext()) jsonStr.append(scanner.nextLine());
            scanner.close();

            JsonObject jsonObject = JsonParser.parseString(jsonStr.toString()).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            return conversionRates.get(monedaDestino).getAsDouble();
        } catch (IOException e) {
            System.out.println("Error al obtener la tasa de conversión.");
            return -1;
        }
    }
}

