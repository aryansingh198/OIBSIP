# Task 2 · Number Guessing Game

**Track:** Java Development (OIBSIP)
**Author:** Aryan Singh

## Objective
A console-based game where the system generates a random number and the
user tries to guess it, receiving "Too High!" / "Too Low!" hints until they
guess correctly or run out of attempts.

## Tech Stack
Java (console application) — no external libraries required.

## Features
- Random number generated fresh each round using `java.util.Random`
- Guess input via `Scanner`, with input validation (rejects non-numeric input)
- Instant feedback: `Too High!`, `Too Low!`, or `Correct!`
- Live attempt counter shown after every guess
- Maximum attempts limit — game ends with `You Lost!` and reveals the number
- "Play Again" prompt after every round
- Score tracking across multiple rounds (`Round X — guessed in Y attempts`)
- **Bonus:** three difficulty levels
  - Easy: 1–50, 10 attempts
  - Medium: 1–100, 7 attempts
  - Hard: 1–200, 5 attempts
- End-of-session summary: total rounds, rounds won, rounds lost

## How to Run
```bash
javac NumberGuessingGame.java
java NumberGuessingGame
```

## Sample Run
```
=======================================
      WELCOME TO NUMBER GUESSING GAME
=======================================

Choose difficulty:
  1. Easy   (1–50,  10 attempts)
  2. Medium (1–100, 7 attempts)
  3. Hard   (1–200, 5 attempts)
Enter choice (1/2/3): 2

Round 1 — Guess a number between 1 and 100.
You have 7 attempts. Good luck!
Attempt 1/7 — Enter your guess: 50
Too High!
Attempts remaining: 6
Attempt 2/7 — Enter your guess: 25
Too Low!
Attempts remaining: 5
Attempt 3/7 — Enter your guess: 38
Correct! 🎉 You guessed it in 3 attempt(s).
Round 1 — guessed in 3 attempts

Play again? (y/n): n

=======================================
               GAME SUMMARY
=======================================
Total rounds played : 1
Rounds won           : 1
Rounds lost          : 0

Thanks for playing! Goodbye.
```

## Core Concepts Used
`java.util.Random`, `Scanner`, `while` loops, `if-else` / `switch`, input
validation, method decomposition, a private static helper class for
difficulty configuration.
