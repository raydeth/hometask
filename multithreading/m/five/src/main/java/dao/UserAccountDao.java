package dao;

import domain.UserAccount;
import exception.UserAccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import utils.FileService;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class UserAccountDao {
    private FileService fileService = new FileService();
    private final String extension = ".txt";
    private final String directoryPath = "users";

    public Optional<UserAccount> findById(long id) {
        Map<String, UserAccount> accounts;
        synchronized (this) {
            accounts = fileService.loadFilesData(directoryPath);
        }

        Optional<UserAccount> userAccount = accounts.values().stream().filter(a -> a.getId() == id).findFirst();

        UserAccount obj = userAccount.get();
        log.info(String.valueOf(obj));

        return userAccount;
    }

    public UserAccount getById(long id) {
        return findById(id).orElseThrow(UserAccountNotFoundException::new);
    }

    public void save(UserAccount userAccount) {
        synchronized (userAccount) {
            fileService.saveObjectToFile(userAccount, directoryPath, userAccount.getId() + extension);
        }
    }
}
