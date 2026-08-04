import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Drives the ATM session for one logged-in account: menu display,
 * withdraw / deposit / transfer / transaction history / quit.
 */
public class ATM {

    private final Bank bank;
    private final Account account;
    private final Scanner scanner;
    private final List<Transaction> history = new ArrayList<>();

    public ATM(Bank bank, Account account, Scanner scanner) {
        this.bank = bank;
        this.account = account;
        this.scanner = scanner;
    }

    /** Runs the main menu loop until the user chooses to Quit. */
    public void start() {
        System.out.println("\nWelcome, " + account.getHolderName() + "!");
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": showTransactionHistory(); break;
                case "2": withdraw(); break;
                case "3": deposit(); break;
                case "4": transfer(); break;
                case "5":
                    System.out.println("\nThank you for banking with us, " + account.getHolderName() + ". Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1–5.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n----- MAIN MENU -----");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.printf("Current Balance: ₹%.2f%n", account.getBalance());
        System.out.print("Choose an option: ");
    }

    private void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        if (history.isEmpty()) {
            System.out.println("No transactions yet this session.");
            return;
        }
        for (Transaction t : history) {
            System.out.println(t);
        }
    }

    private void withdraw() {
        double amount = readAmount("Enter amount to withdraw: ");
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (!account.hasSufficientFunds(amount)) {
            System.out.println("Insufficient Funds.");
            return;
        }
        account.debit(amount);
        history.add(new Transaction("WITHDRAW", amount, "Balance: ₹" + String.format("%.2f", account.getBalance())));
        System.out.printf("Withdrawal successful. New balance: ₹%.2f%n", account.getBalance());
    }

    private void deposit() {
        double amount = readAmount("Enter amount to deposit: ");
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        account.credit(amount);
        history.add(new Transaction("DEPOSIT", amount, "Balance: ₹" + String.format("%.2f", account.getBalance())));
        System.out.printf("Deposit successful. New balance: ₹%.2f%n", account.getBalance());
    }

    private void transfer() {
        System.out.print("Enter recipient User ID: ");
        String recipientId = scanner.nextLine().trim();

        if (recipientId.equals(account.getUserId())) {
            System.out.println("You cannot transfer to your own account.");
            return;
        }
        Account recipient = bank.getAccount(recipientId);
        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        double amount = readAmount("Enter amount to transfer: ");
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (!account.hasSufficientFunds(amount)) {
            System.out.println("Insufficient Funds.");
            return;
        }

        account.debit(amount);
        recipient.credit(amount);
        history.add(new Transaction("TRANSFER", amount, "To: " + recipientId));
        System.out.printf("Transfer successful. New balance: ₹%.2f%n", account.getBalance());
    }

    private double readAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount — please enter a valid number.");
            }
        }
    }
}
