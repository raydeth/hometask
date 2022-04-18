package client;

import dao.CurrencyDao;
import dao.UserAccountDao;
import dao.factory.CurrencyFactory;
import dao.factory.UserAccountFactory;
import domain.Currency;
import domain.UserAccount;
import lombok.extern.slf4j.Slf4j;
import service.UserAccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Client {

    private final UserAccountService userAccountService = new UserAccountService();

    private final List<Currency> currencies = CurrencyFactory.getCurrencies();
    private final CurrencyDao currencyDao = new CurrencyDao();

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    private void run() {
        createUserAccounts();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(startExchange(), 0, 100, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(startExchange(), 0, 100, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(startExchange(), 0, 100, TimeUnit.MILLISECONDS);
    }

    private void createUserAccounts() {
        UserAccountFactory.getUserAccounts().forEach(userAccountService::save);
    }

    private Runnable startExchange() {
        return () -> {
            try {
                int userAccountIndex = ThreadLocalRandom.current().nextInt(1, 4);
                UserAccount userAccount = userAccountService.getById(userAccountIndex);

                int sourceCurrencyIndex = ThreadLocalRandom.current().nextInt(1, currencies.size() + 1);
                int targetCurrencyIndex;
                while ((targetCurrencyIndex = ThreadLocalRandom.current().nextInt(1, currencies.size())) == sourceCurrencyIndex) ;

                Currency sourceCurrency = currencyDao.getById(sourceCurrencyIndex);
                Currency targetCurrency = currencyDao.getById(targetCurrencyIndex);

                BigDecimal amount = BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(1, 100));
                log.info(String.format("User account with id: %s \t source currency: %s \t target currency: %s \t amount: %s", userAccount.getId(), sourceCurrency.getCode(), targetCurrency.getCode(), amount));

                userAccountService.exchange(userAccount, sourceCurrency, targetCurrency, amount);
            } catch (Throwable e) {
                log.info(String.valueOf(e));
            }
        };
    }
}
