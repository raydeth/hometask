package service;

import dao.UserAccountDao;
import domain.Currency;
import domain.UserAccount;
import exception.CurrencyNotFoundException;
import exception.InvalidAmountException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class UserAccountService {

    private final ExchangeRateService exchangeRateService = new ExchangeRateService();

    private final UserAccountDao userAccountDao = new UserAccountDao();


    public Optional<UserAccount> findById(long id) {
        return userAccountDao.findById(id);
    }

    public UserAccount getById(long id) {
        return userAccountDao.getById(id);
    }


    public void save(UserAccount userAccount) {
        userAccountDao.save(userAccount);
    }

    public void exchange(UserAccount userAccount,
                         Currency source,
                         Currency target,
                         BigDecimal amount) {
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(source, target, amount);
        synchronized (userAccount) {
            Map.Entry<Currency, BigDecimal> userAccountSource = getUserAccountCurrency(userAccount, source, amount);
            if (userAccountSource.getValue().compareTo(amount) < 0) {
                throw new InvalidAmountException();
            }

            userAccountSource.setValue(userAccountSource.getValue().subtract(amount));

            Map.Entry<Currency, BigDecimal> userAccountTarget = getUserAccountCurrency(userAccount, target, exchangeRate);
            userAccountTarget.setValue(userAccountTarget.getValue().add(exchangeRate));

            userAccountDao.save(userAccount);

        }
    }

    private Map.Entry<Currency, BigDecimal> getUserAccountCurrency(UserAccount userAccount, Currency source, BigDecimal amount) {
        return userAccount.getWallets()
                .entrySet()
                .stream()
                .filter(e -> e.getKey().equals(source))
                .findFirst()
                .orElseThrow(CurrencyNotFoundException::new);
    }

}
