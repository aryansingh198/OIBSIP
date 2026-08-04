import java.util.Random;
import java.util.Scanner;

/**
 * OIBSIP - Java Development Track
 * Task 2: Number Guessing Game
 *
 * The system generates a random number and the user tries to guess it,
 * receiving "Too High!" / "Too Low!" hints until correct or out of attempts.
 * Supports multiple rounds and three difficulty levels (Easy / Medium / Hard).
 *
 * Author: Aryan Singh
 */
public class NumberGuessingGame {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private int totalRounds = 0;
    private int roundsWon = 0;

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.run();
    }

    public void run() {
        System.out.println("=======================================");
        System.out.println("      WELCOME TO NUMBER GUESSING GAME  ");
        System.out.println("=======================================");

        boolean playAgain = true;
        while (playAgain) {
            playRound();
            playAgain = askPlayAgain();
        }

        printFinalSummary();
        System.out.println("\nThanks for playing! Goodbye.");
        scanner.close();
    }

    /** Runs a single round: pick difficulty, generate number, take guesses. */
    private void playRound() {
        totalRounds++;

        Difficulty difficulty = chooseDifficulty();
        int secretNumber = random.nextInt(difficulty.max - difficulty.min + 1) + difficulty.min;

        System.out.printf("%nRound %d — Guess a number between %d and %d.%n",
                totalRounds, difficulty.min, difficulty.max);
        System.out.println("You have " + difficulty.maxAttempts + " attempts. Good luck!");

        int attempts = 0;
        boolean won = false;

        while (attempts < difficulty.maxAttempts) {
            attempts++;
            int guess = readValidGuess(difficulty.min, difficulty.max, attempts, difficulty.maxAttempts);

            if (guess == secretNumber) {
                System.out.println("Correct! 🎉 You guessed it in " + attempts + " attempt(s).");
                won = true;
                roundsWon++;
                System.out.printf("Round %d — guessed in %d attempts%n", totalRounds, attempts);
                break;
            } else if (guess < secretNumber) {
                System.out.println("Too Low!");
            } else {
                System.out.println("Too High!");
            }

            int remaining = difficulty.maxAttempts - attempts;
            if (remaining > 0) {
                System.out.println("Attempts remaining: " + remaining);
            }
        }

        if (!won) {
            System.out.println("You Lost! 😢 The correct number was: " + secretNumber);
            System.out.printf("Round %d — not guessed (used all %d attempts)%n", totalRounds, difficulty.maxAttempts);
        }
    }

    /** Reads a guess from the user, validating it is a number within range. */
    private int readValidGuess(int min, int max, int attemptNo, int maxAttempts) {
        while (true) {
            System.out.printf("Attempt %d/%d — Enter your guess: ", attemptNo, maxAttempts);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input — please enter a valid whole number.");
            }
        }
    }

    /** Lets the user choose the difficulty level for the round. */
    private Difficulty chooseDifficulty() {
        System.out.println("\nChoose difficulty:");
        System.out.println("  1. Easy   (1–50,  10 attempts)");
        System.out.println("  2. Medium (1–100, 7 attempts)");
        System.out.println("  3. Hard   (1–200, 5 attempts)");

        while (true) {
            System.out.print("Enter choice (1/2/3): ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": return new Difficulty(1, 50, 10);
                case "2": return new Difficulty(1, 100, 7);
                case "3": return new Difficulty(1, 200, 5);
                default: System.out.println("Invalid choice — please enter 1, 2, or 3.");
            }
        }
    }

    private boolean askPlayAgain() {
        while (true) {
            System.out.print("\nPlay again? (y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) return true;
            if (answer.equals("n") || answer.equals("no")) return false;
            System.out.println("Please answer y or n.");
        }
    }

    private void printFinalSummary() {
        System.out.println("\n=======================================");
        System.out.println("               GAME SUMMARY            ");
        System.out.println("=======================================");
        System.out.println("Total rounds played : " + totalRounds);
        System.out.println("Rounds won           : " + roundsWon);
        System.out.println("Rounds lost          : " + (totalRounds - roundsWon));
    }

    /** Simple holder for difficulty-level configuration. */
    private static class Difficulty {
        final int min;
        final int max;
        final int maxAttempts;

        Difficulty(int min, int max, int maxAttempts) {
            this.min = min;
            this.max = max;
            this.maxAttempts = maxAttempts;
        }
    }
    }
                                          
