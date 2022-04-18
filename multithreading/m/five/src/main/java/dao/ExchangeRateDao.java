package dao;

import dao.factory.ExchangeRateFactory;
import domain.Currency;
import domain.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;

public class ExchangeRateDao {

    private final List<ExchangeRate> exchangeRates = ExchangeRateFactory.getExchangeRate();

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }
}
