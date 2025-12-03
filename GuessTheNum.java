package org.example;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNum {

    public static  Scanner scanner = new Scanner(System.in);
    public static  Random random = new Random();

    public static void main(String[] args) {
        while (true) {
            System.out.println("=== Игра: Угадай число ===");

            int maximum = SelectDifficulty();
            int attempt = askLimit();

            int hiddenNum = random.nextInt(maximum) + 1;
            int attempts = 0;
            boolean guessed = false;

            while (!guessed) {
                attempts++;
                System.out.printf("Попытка %d%s. Введите число: ", attempts, (attempt > 0 ? " из " + attempt : ""));
                int guess = readInt(1, maximum);

                if (guess == hiddenNum) {
                    System.out.printf("Вы угадали! Число было %d. Количество попыток: %d.%n", hiddenNum, attempts);
                    guessed = true;
                } else if (guess < hiddenNum) {
                    System.out.println("Слишком маленькое.");
                } else {
                    System.out.println("Слишком большое.");
                }


                if (attempt > 0 && attempts >= attempt) {
                    System.out.printf("Попытки закончились! Загаданное число было: %d.%n", hiddenNum);
                    break;
                }
            }

            if (!askYesNo("Хотите сыграть снова? (y/n): ")) break;
        }

        System.out.println("Спасибо за игру!");
    }

    static int readInt(int min, int max) {
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

     static int SelectDifficulty() {
        System.out.println("Выберите уровень сложности: 1 - Лёгкий (1-50), 2 - Средний (1-100), 3 - Сложный (1-500), 4 - Свой диапазон.");
        int choice = readInt(1, 4);
        switch (choice) {
            case 1: return 50;
            case 2: return 100;
            case 3: return 500;
            case 4: return readInt(2, Integer.MAX_VALUE);
            default: return 50;
        }
    }

     static int askLimit() {
        System.out.print("Ограничить количество попыток? (y/n): ");
        return askYesNo("y") ? readInt(1, Integer.MAX_VALUE) : -1;
    }

     static boolean askYesNo(String prompt) {
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("y") || answer.equals("да");
    }
}




