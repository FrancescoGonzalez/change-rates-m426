package org.gonzalez;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RemoteCurrentServicePort {
    private final String apiKey;
    private final HttpClient.Builder builder = HttpClient.newBuilder();
    public RemoteCurrentServicePort(String apiKey) {
        this.apiKey = apiKey;
    }

    ConversionServiceResponse getConversionRates(String from, String to){
        try {
            URI actualURI = new URI("http://data.fixer.io/api/latest?access_key=" + apiKey + "&symbols=" + from + "," + to);

            HttpRequest request = HttpRequest.newBuilder(actualURI) //crea la richiesta
                .GET()
                .build();
            HttpResponse<String> response = builder //gestisce e ritorna la risposta
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

            return new ConversionServiceResponse(response.body(), from, to);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    ConversionServiceResponse getCurrencies(){
        try {
            URI actualURI = new URI("http://data.fixer.io/api/symbols?access_key=" + apiKey);
            HttpRequest request = HttpRequest.newBuilder(actualURI) //crea la richiesta
                .GET()
                .build();
            HttpResponse<String> response = builder //gestisce e ritorna la risposta
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
            return new ConversionServiceResponse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}