# change-rates-m426

- Utilizzare "fixer.io"

- API key:
  24aa603ac83ad85bfcd29cd112e7ee36

- chiamate utili:

  http://data.fixer.io/api/symbols
  ? access_key = 24aa603ac83ad85bfcd29cd112e7ee36



    http://data.fixer.io/api/latest
        ? access_key = 24aa603ac83ad85bfcd29cd112e7ee36


    http://data.fixer.io/api/latest
        ? access_key = 24aa603ac83ad85bfcd29cd112e7ee36
        & symbols = USD,CAD,JPY



- metodi fatti:

  double convertAmount(from, to, amount);

  String getFullName(currency);

  boolean isValid(currency);

  Map<String, Double> convertFiveAmounts(from, a1, a2, a3, a4, a5);

- metodi da fare: