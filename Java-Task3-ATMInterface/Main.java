import java.util.Scanner;

/**
 * OIBSIP - Java Development Track
 * Task 3: ATM Interface
 *
 * Console-based ATM simulation with PIN authentication and standard
 * banking transactions (withdraw, deposit, transfer, transaction history).
 *
 * Classes: ATM, Account, Transaction, Bank, Main
 *
 * Author: Aryan Singh
 */
public class Main {

    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.println("=========================================");
        System.out.println("           WELCOME TO JAVA ATM           ");
        System.out.println("=========================================");
        System.out.println("(Demo accounts — User ID: 1001 / PIN: 1234)");

        Account account = login(bank, scanner);

        if (account == null) {
            System.out.println("\nToo many incorrect attempts. Access denied. Card retained.");
        } else {
            ATM atm = new ATM(bank, account, scanner);
            atm.start();
        }

        scanner.close();
    }

    /** Handles login, allowing up to MAX_LOGIN_ATTEMPTS tries before denying access. */
    private static Account login(Bank bank, Scanner scanner) {
        for (int attempt = 1; attempt <= MAX_LOGIN_ATTEMPTS; attempt++) {
            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine().trim();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine().trim();

            Account account = bank.authenticate(userId, pin);
            if (account != null) {
                return account;
            }

            int remaining = MAX_LOGIN_ATTEMPTS - attempt;
            if (remaining > 0) {
                System.out.println("Incorrect User ID or PIN. Attempts remaining: " + remaining);
            }
        }
        return null;
    }
}
