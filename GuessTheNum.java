package org.example;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNum {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        while (true) {
            System.out.println("=== Игра: Угадай число ===");

            // Выбираем уровень сложности
            int maximum = SelectDifficulty();
            // Ограничиваем ли попытки
            int attempt = askLimit();

            // Загадали число и начали угадывать
            int hiddenNum = random.nextInt(maximum) + 1;  // Загадали число прямо здесь
            int attempts = 0;
            boolean guessed = false;

            while (!guessed) {
                attempts++;
                System.out.printf("Попытка %d%s. Введите число: ", attempts, (attempt > 0 ? " из " + attempt : ""));
                int guess = readInt(1, maximum);  // Получаем число от игрока

                if (guess == hiddenNum) {
                    System.out.printf("Вы угадали! Число было %d. Количество попыток: %d.%n", hiddenNum, attempts);
                    guessed = true;  // Угадываем число и завершаем игру
                } else if (guess < hiddenNum) {
                    System.out.println("Слишком маленькое.");
                } else {
                    System.out.println("Слишком большое.");
                }

                // Проверка лимита попыток
                if (attempt > 0 && attempts >= attempt) {
                    System.out.printf("Попытки закончились! Загаданное число было: %d.%n", hiddenNum);
                    break;  // Завершаем игру, если попытки закончились
                }
            }

            // Запрос на повтор игры
            if (!askYesNo("Хотите сыграть снова? (y/n): ")) break;
        }

        System.out.println("Спасибо за игру!");
    }

    // Метод для чтения целых чисел от игрока
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

    // Метод для выбора уровня сложности
    private static int SelectDifficulty() {
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

    // Метод для запроса ограничения по количеству попыток
    private static int askLimit() {
        System.out.print("Ограничить количество попыток? (y/n): ");
        return askYesNo("y") ? readInt(1, Integer.MAX_VALUE) : -1;
    }

    // Метод для ответа "да"/"нет"
    private static boolean askYesNo(String prompt) {
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("y") || answer.equals("да");
    }
}


