package org.example;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNum {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static int bestScore = Integer.MAX_VALUE;

    public static void main(String[] args) {
        while (true) {
            System.out.println("=== Игра: Угадай число ===");
            int maxNumber = chooseDifficulty();
            int attemptLimit = askLimit();

            playRound(maxNumber, attemptLimit);

            if (bestScore != Integer.MAX_VALUE) {
                System.out.println("Рекорд: " + bestScore + " попыток.");
            } else {
                System.out.println("Рекорд отсутствует.");
            }

            if (!askYesNo("Хотите сыграть снова? (y/n): ")) break;
        }
        System.out.println("Спасибо за игру!");
    }

    private static void playRound(int maxNumber, int attemptLimit) {
        int secret = random.nextInt(maxNumber) + 1;
        int attempts = 0;
        boolean guessed = false;

        while (!guessed) {
            attempts++;
            System.out.printf("Попытка %d%s. Введите число: ", attempts, (attemptLimit > 0 ? " из " + attemptLimit : ""));
            int guess = readInt(1, maxNumber);

            if (guess == secret) {
                System.out.printf("Вы угадали! Число было %d. Количество попыток: %d.%n", secret, attempts);
                if (attempts < bestScore) {
                    bestScore = attempts;
                    System.out.println("Новый рекорд!");
                }
                guessed = true;
            } else if (guess < secret) {
                System.out.println("Слишком маленькое.");
            } else {
                System.out.println("Слишком большое.");
            }

            if (attemptLimit > 0 && attempts >= attemptLimit) {
                System.out.printf("Попытки закончились! Загаданное число было: %d.%n", secret);
                break;
            }
        }
    }

    private static int readInt(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input < min || input > max) {
                    System.out.printf("Введите число от %d до %d.%n", min, max);
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число.");
            }
        }
    }

    private static int chooseDifficulty() {
        System.out.println("Выберите уровень сложности: 1 - Лёгкий (1-50), 2 - Средний (1-100), 3 - Сложный (1-500), 4 - Свой диапазон.");
        int choice = readInt(1, 4);
        switch (choice) {
            case 1: return 50;
            case 2: return 100;
            case 3: return 500;
            case 4: return readInt(2, Integer.MAX_VALUE);
            default: return 50; // по умолчанию лёгкий уровень
        }
    }

    private static int askLimit() {
        System.out.print("Ограничить количество попыток? (y/n): ");
        return askYesNo("y") ? readInt(1, Integer.MAX_VALUE) : -1;
    }

    private static boolean askYesNo(String prompt) {
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("y") || answer.equals("да");
    }
}
