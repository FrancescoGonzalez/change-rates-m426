package org.gonzalez;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RemoteCurrencyServicePort {
    private final String apiKey;
    private final HttpClient.Builder builder = HttpClient.newBuilder();
    RemoteCurrencyServicePort(String apiKey) {
        this.apiKey = apiKey;
    }

    ConversionServiceResponse getConversionRates(String from, String to){
        try {
            URI actualURI = new URI("http://data.fixer.io/api/latest?access_key=" + apiKey + "&symbols=" + from + "," + to);
            HttpResponse<String> response = call(actualURI);
            return new ConversionServiceResponse(response.body(), from, to);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    ConversionServiceResponse getCurrencies(){
        try {
            URI actualURI = new URI("http://data.fixer.io/api/symbols?access_key=" + apiKey);
            HttpResponse<String> response = call(actualURI);
            return new ConversionServiceResponse(response.body());
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    private HttpResponse<String> call(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(uri) //crea la richiesta
            .GET()
            .build();
        return builder //gestisce e ritorna la risposta
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString());
    }
}