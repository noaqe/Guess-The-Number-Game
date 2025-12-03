package org.example;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNum {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {

            System.out.println("=== Игра: Угадай число ===");

            System.out.println("Выберите уровень сложности:");
            System.out.println("1 - Лёгкий (1-50)");
            System.out.println("2 - Средний (1-100)");
            System.out.println("3 - Сложный (1-500)");
            System.out.println("4 - Свой диапазон");

            int maxNumber;

            int difficulty = scanner.nextInt();
            if (difficulty == 1) {
                maxNumber = 50;
            } else if (difficulty == 2) {
                maxNumber = 100;
            } else if (difficulty == 3) {
                maxNumber = 500;
            } else {
                System.out.println("Введите максимальное число:");
                maxNumber = scanner.nextInt();
            }

            System.out.println("Хотите ограничить количество попыток? (y/n)");
            String limitAnswer = scanner.next();

            int attemptLimit = -1;
            if (limitAnswer.equalsIgnoreCase("y")) {
                System.out.println("Введите количество попыток:");
                attemptLimit = scanner.nextInt();
            }

            int hiddenNumber = random.nextInt(maxNumber) + 1;

            int attempts = 0;
            boolean guessed = false;

            while (!guessed) {
                attempts++;

                if (attemptLimit > 0) {
                    System.out.println("Попытка " + attempts + " из " + attemptLimit);
                } else {
                    System.out.println("Попытка " + attempts);
                }

                System.out.println("Введите число:");
                int guess = scanner.nextInt();

                if (guess == hiddenNumber) {
                    System.out.println("Вы угадали! Число было " + hiddenNumber);
                    guessed = true;
                } else if (guess < hiddenNumber) {
                    System.out.println("Слишком маленькое!");
                } else {
                    System.out.println("Слишком большое!");
                }

                if (attemptLimit > 0 && attempts >= attemptLimit && !guessed) {
                    System.out.println("Попытки закончились! Число было: " + hiddenNumber);
                    break;
                }
            }

            System.out.println("Хотите сыграть снова? (y/n)");
            String again = scanner.next();

            if (!again.equalsIgnoreCase("y")) {
                break;
            }
        }

        System.out.println("Спасибо за игру!");
    }
}
