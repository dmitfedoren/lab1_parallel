import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість потоків: ");
        int numberOfThreads = scanner.nextInt();

        System.out.print("Введіть крок послідовності: ");
        int step = scanner.nextInt();

        List<SequenceSumThread> threads = new ArrayList<>();
        long[] sleepTimes = new long[numberOfThreads];

        // Створення обчислювальних потоків
        for (int i = 1; i <= numberOfThreads; i++) {
            SequenceSumThread thread = new SequenceSumThread(i, step);
            threads.add(thread);
            thread.start();

            // Встановлюємо різні часи затримки для кожного потоку (наприклад, 5 секунд)
            sleepTimes[i - 1] = 5000; // 5000 мс = 5 секунд
        }

        // Створення та запуск керуючого потоку
        ControllerThread controller = new ControllerThread(threads, sleepTimes);
        controller.start();

        // Очікування завершення всіх потоків
        try {
            for (SequenceSumThread thread : threads) {
                thread.join();
            }
            controller.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Всі потоки завершено.");
    }
}