package org.gonzalez;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class ConversionServiceResponse{
    private final Map<String, String> currencyNames = new HashMap<>();
    private final double[] values = new double[2];

    public ConversionServiceResponse(String body, String from, String to) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(body); //trasforma un JSON da string a oggetto

            JSONObject rates = (JSONObject) jsonObject.get("rates"); //entriamo nel campo rates, e prendiamo solo le nostre 2 valute volute
            values[0] = from.equals("EUR")? 1 : (double) rates.get(from);
            values[1] = to.equals("EUR")? 1 : (double) rates.get(to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ConversionServiceResponse(String body) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(body);

            JSONObject symbols = (JSONObject) json.get("symbols");

            for (Object key : symbols.keySet()) {
                String currencyCode = (String) key;
                String currencyName = (String) symbols.get(key);
                currencyNames.put(currencyCode, currencyName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getValueFrom() {
        return values[0];
    }

    public double getValueTo() {
        return values[1];
    }

    public String getFullName(String currency) {
        for (Map.Entry<String, String> entry : currencyNames.entrySet()) {
            if (currency.equals(entry.getKey())) {
                return entry.getValue();
            }
        }

        return null;
    }
}