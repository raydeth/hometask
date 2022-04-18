package service;

import dao.ExchangeRateDao;
import domain.Currency;
import domain.ExchangeRate;
import exception.CurrencyNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeRateService {

    private final ExchangeRateDao exchangeRateDao = new ExchangeRateDao();

    public BigDecimal getExchangeRate(Currency source, Currency target, BigDecimal value) {
        ExchangeRate exchangeRate = exchangeRateDao
                .getExchangeRates()
                .stream()
                .filter(er -> (er.getSource().equals(source) || er.getTarget().equals(source)) && (er.getTarget().equals(target) || er.getSource().equals(target)))
                .findFirst()
                .orElseThrow(CurrencyNotFoundException::new);
        return exchangeRate.getSource().equals(source) ? value.multiply(exchangeRate.getRate()) : value.divide(exchangeRate.getRate(), RoundingMode.HALF_DOWN);
    }
}
