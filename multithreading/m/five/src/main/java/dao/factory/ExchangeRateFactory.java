package dao.factory;

import dao.CurrencyDao;
import domain.Currency;
import domain.ExchangeRate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ExchangeRateFactory {

    private static final List<ExchangeRate> exchangeRate;

    private static CurrencyDao currencyDao = new CurrencyDao();

    static {
        Currency kztCurrency = currencyDao.getByCode("KZT");
        Currency ruCurrency = currencyDao.getByCode("RU");
        Currency usdCurrency = currencyDao.getByCode("USD");
        ExchangeRate kztRuExchangeRate = new ExchangeRate(kztCurrency, ruCurrency, BigDecimal.valueOf(0.18D));
        ExchangeRate kztUsdExchangeRate = new ExchangeRate(kztCurrency, usdCurrency, BigDecimal.valueOf(0.0022D));
        ExchangeRate ruUsdExchangeRate = new ExchangeRate(ruCurrency, usdCurrency, BigDecimal.valueOf(0.012D));
        exchangeRate = Arrays.asList(kztRuExchangeRate, kztUsdExchangeRate, ruUsdExchangeRate);
    }

    public static List<ExchangeRate> getExchangeRate() {
        return exchangeRate;
    }
}
