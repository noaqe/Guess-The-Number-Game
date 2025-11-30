package org.example;
import java.util.Random;

import java.util.Scanner;

public class GuessTheNum {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private int bestScore = Integer.MAX_VALUE;

    public static void main(String[] args) {
        GuessTheNum game = new GuessTheNum();
        game.startGame();
    }

    public void startGame() {
        System.out.println("=== Игра: Угадай число ===");

        boolean keepPlaying = true;
        while (keepPlaying) {

            int maxNumber = chooseDifficulty();

            boolean useAttemptLimit = askYesNo("Хотите ограничить количество попыток? (y/n): ");
            int attemptLimit = 0;
            if (useAttemptLimit) {
                attemptLimit = readInt("Введите максимальное количество попыток (целое > 0): ", 1, Integer.MAX_VALUE);
            }

            playRound(maxNumber, useAttemptLimit ? attemptLimit : -1);

            if (bestScore != Integer.MAX_VALUE) {
                System.out.println("Текущий рекорд (лучший результат за запуск): " + bestScore + " попыток.");
            } else {
                System.out.println("Рекорд пока отсутствует.");
            }

            keepPlaying = askYesNo("Хотите сыграть ещё раз? (y/n): ");
        }

        System.out.println("Спасибо за игру! Пока.");
    }

    public void playRound(int maxNumber, int attemptLimit) {
        int secret = random.nextInt(maxNumber) + 1;
        System.out.println("\nЯ загадал число от 1 до " + maxNumber + ". Попробуйте угадать!");

        int attempts = 0;
        boolean guessed = false;

        while (!guessed) {
            if (attemptLimit > 0) {
                System.out.println("Попытка " + (attempts + 1) + " из " + attemptLimit + ".");
            } else {
                System.out.println("Попытка " + (attempts + 1) + ".");
            }

            int guess = readInt("Введите число: ", 1, maxNumber);
            attempts++;

            if (guess > secret) {
                System.out.println("Слишком большое.");
            } else if (guess < secret) {
                System.out.println("Слишком маленькое.");
            } else {
                System.out.println("Вы угадали! Число было: " + secret + ".");
                System.out.println("Количество попыток: " + attempts + ".");
                guessed = true;

                if (attempts < bestScore) {
                    bestScore = attempts;
                    System.out.println("Новый рекорд! Поздравляем!");
                }
                break;
            }

            if (attemptLimit > 0 && attempts >= attemptLimit) {
                System.out.println("Попытки закончились! Вы проиграли. Загаданное число было: " + secret + ".");
                break;
            }
        }
    }


    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line == null) line = "";
            line = line.trim();
            if (line.isEmpty()) {
                System.out.println("Пустой ввод — введите целое число.");
                continue;
            }
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод — пожалуйста, введите целое число.");
            }
        }
    }


    public int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value < min || value > max) {
                System.out.println("Число должно быть в диапазоне от " + min + " до " + max + ".");
                continue;
            }
            return value;
        }
    }


    public boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line == null) line = "";
            line = line.trim().toLowerCase();
            if (line.equals("y") || line.equals("yes") || line.equals("д") || line.equals("да")) {
                return true;
            } else if (line.equals("n") || line.equals("no") || line.equals("н") || line.equals("нет")) {
                return false;
            } else {
                System.out.println("Пожалуйста, введите 'y' (да) или 'n' (нет).");
            }
        }
    }


    public int chooseDifficulty() {
        System.out.println("\nВыберите уровень сложности:");
        System.out.println("1 — Лёгкий (1–50)");
        System.out.println("2 — Средний (1–100)");
        System.out.println("3 — Сложный (1–500)");
        System.out.println("4 — Ввести своё N");

        while (true) {
            int choice = readInt("Введите номер уровня (1-4): ");
            switch (choice) {
                case 1:
                    System.out.println("Выбран лёгкий уровень (1–50).");
                    return 50;
                case 2:
                    System.out.println("Выбран средний уровень (1–100).");
                    return 100;
                case 3:
                    System.out.println("Выбран сложный уровень (1–500).");
                    return 500;
                case 4:
                    int custom = readInt("Введите верхнюю границу N (минимум 2): ", 2, Integer.MAX_VALUE);
                    System.out.println("Выбран диапазон 1–" + custom + ".");
                    return custom;
                default:
                    System.out.println("Неверный выбор. Введите 1, 2, 3 или 4.");
            }
        }
    }
}
