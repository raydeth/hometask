package dao.factory;

import dao.CurrencyDao;
import domain.Currency;
import domain.UserAccount;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserAccountFactory {

    private static final List<UserAccount> userAccounts;

    private static CurrencyDao currencyDao = new CurrencyDao();

    static {
        UserAccount userAccount1 = new UserAccount(1L, createWallet());
        UserAccount userAccount2 = new UserAccount(2L, createWallet());
        UserAccount userAccount3 = new UserAccount(3L, createWallet());
        userAccounts = Arrays.asList(userAccount1, userAccount2, userAccount3);
    }

    private static Map<Currency, BigDecimal> createWallet() {
        Map<Currency, BigDecimal> wallet = new ConcurrentHashMap<>();
        wallet.put(currencyDao.getByCode("KZT"), BigDecimal.valueOf(10000));
        wallet.put(currencyDao.getByCode("RU"), BigDecimal.valueOf(10000));
        wallet.put(currencyDao.getByCode("USD"), BigDecimal.valueOf(10000));
        return wallet;
    }

    public static List<UserAccount> getUserAccounts() {
        return userAccounts;
    }
}
