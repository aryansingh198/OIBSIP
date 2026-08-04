import java.util.HashMap;
import java.util.Map;

/**
 * Represents the bank: a collection of accounts, with lookup and
 * authentication support. In a real system this would be backed by a database.
 */
public class Bank {

    private final Map<String, Account> accounts = new HashMap<>();

    public Bank() {
        seedDemoAccounts();
    }

    /** Pre-loaded demo accounts so the ATM is usable out of the box. */
    private void seedDemoAccounts() {
        accounts.put("1001", new Account("1001", "1234", "Aryan Singh", 25000.00));
        accounts.put("1002", new Account("1002", "4321", "Riya Sharma", 12500.50));
    }

    public Account authenticate(String userId, String pin) {
        Account account = accounts.get(userId);
        if (account != null && account.verifyPin(pin)) {
            return account;
        }
        return null;
    }

    public Account getAccount(String userId) {
        return accounts.get(userId);
    }

    public boolean accountExists(String userId) {
        return accounts.containsKey(userId);
    }
}
