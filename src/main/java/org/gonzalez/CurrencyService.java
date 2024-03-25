package org.gonzalez;

import java.util.HashMap;
import java.util.Map;

public class CurrencyService {
    private final RemoteCurrencyServicePort remoteService;

    private final ConversionServiceResponse currencyList;

    public CurrencyService(RemoteCurrencyServicePort remoteService) {
        this.remoteService = remoteService;
        currencyList = remoteService.getCurrencies();
    }

    public double convertAmount(String from, String to, double amount){
        isValid(from, to);

        ConversionServiceResponse c = remoteService.getConversionRates(from.toUpperCase(), to.toUpperCase());

        return (amount / c.getValueFrom()) * c.getValueTo();
    }

    public String getFullName(String currency) {
        isValid(currency);
        return currencyList.getFullName(currency);

    }

    public void checkIfExists(String... c) {
        for (String currency : c) {
            if (currencyList.getFullName(currency.toUpperCase()) == null) {
                throw new InvalidCurrencyException("the currency \"" + currency + "\" doesn't exist");
            }
        }
    }

    public Map<String, Double> convertMoreAmounts(String from, String... amounts) { // calls convertAmounts(); for every amount, and add the result to an array
        Map<String, Double> result = new HashMap<>();
        isValid(from);

        for (String amount : amounts) {
            isValid(amount);
            result.put(amount, convertAmount(from, amount, 1));
        }

        return result;

    }

    public void isValid(String... c) { // if is NOT valid it will throw an exception
        for (String currency : c) {
            if (currency.length() != 3) {
                throw new InvalidCurrencyException("The currency " + currency + " needs to be of 3 letters to be valid");
            }
        }
        checkIfExists(c);

    }
}
