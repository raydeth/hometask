package dao.factory;

import domain.Currency;

import java.util.Arrays;
import java.util.List;

public class CurrencyFactory {
    private static final List<Currency> currencies;

    static {
        Currency kztCurrency = new Currency(1L, "KZT");
        Currency ruCurrency = new Currency(2L, "RU");
        Currency usdCurrency = new Currency(3L, "USD");
        currencies = Arrays.asList(kztCurrency, ruCurrency, usdCurrency);
    }

    public static List<Currency> getCurrencies() {
        return currencies;
    }
}
