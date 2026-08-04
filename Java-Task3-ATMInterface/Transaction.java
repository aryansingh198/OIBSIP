import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents one transaction entry (withdraw, deposit, or transfer)
 * for display in the session's Transaction History.
 */
public class Transaction {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final String type;
    private final double amount;
    private final String detail;
    private final LocalDateTime timestamp;

    public Transaction(String type, double amount, String detail) {
        this.type = type;
        this.amount = amount;
        this.detail = detail;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[%s] %-10s ₹%-10.2f %s",
                timestamp.format(FORMAT), type, amount, detail);
    }
}
