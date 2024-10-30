package com.aluracursos.convertidor.modelos;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class Datos {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/ad2f2ff5ceadfe9c89430bc4/latest/USD";

    public String obtenerTasas() throws Exception {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        return respuesta.body();
    }
}
