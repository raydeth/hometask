package dao;

import dao.factory.CurrencyFactory;
import domain.Currency;
import exception.CurrencyNotFoundException;

import java.util.List;
import java.util.Optional;

public class CurrencyDao {

    private final List<Currency> currencies = CurrencyFactory.getCurrencies();

    public synchronized Optional<Currency> findById(long id) {
        return currencies.stream().filter(c -> c.getId() == id).findFirst();
    }

    public synchronized Currency getById(long id) {
        return findById(id).orElseThrow(CurrencyNotFoundException::new);
    }

    public synchronized Optional<Currency> findByCode(String code) {
        return currencies.stream().filter(c -> c.getCode().equals(code)).findFirst();
    }

    public synchronized Currency getByCode(String code) {
        return findByCode(code).orElseThrow(CurrencyNotFoundException::new);
    }
}
