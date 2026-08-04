/**
 * Represents a single bank account: identity, PIN, and balance.
 * Encapsulation: fields are private with controlled access via methods.
 */
public class Account {

    private final String userId;
    private final String pin;
    private final String holderName;
    private double balance;

    public Account(String userId, String pin, String holderName, double openingBalance) {
        this.userId = userId;
        this.pin = pin;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    public String getUserId() {
        return userId;
    }

    public String getHolderName() {
        return holderName;
    }

    public boolean verifyPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public boolean hasSufficientFunds(double amount) {
        return balance >= amount;
    }

    public void credit(double amount) {
        balance += amount;
    }

    /** Returns false if funds are insufficient; otherwise debits and returns true. */
    public boolean debit(double amount) {
        if (!hasSufficientFunds(amount)) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
