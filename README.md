# change-rates-m426

Come API di conversione ho scelto **fixer.io**, che è facile e conveniente.

- La mia API key: 13e4351b9d6e76b6f390c211b57315d4

- chiamate utili:


    http://data.fixer.io/api/symbols
        ? access_key = APIKEY



    http://data.fixer.io/api/latest
        ? access_key = APIKEY


    http://data.fixer.io/api/latest
        ? access_key = APIKEY
        & symbols = USD,CAD,JPY



- metodi fatti:

double convertAmount(from, to, amount);

  String getFullName(currency);

  boolean isValid(currency);

  Map<String, Double> convertFiveAmounts(from, a1, a2, a3, a4, a5);

- metodi da fare: