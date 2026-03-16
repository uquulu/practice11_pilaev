import java.io.*;
import java.util.*;

public class TextEditor {

    static Scanner scanner = new Scanner(System.in);
    static String fileName = "text.txt";

    public static void main(String[] args) {

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    writeMultipleLines();
                    break;

                case 2:
                    readFile();
                    break;

                case 3:
                    readRange();
                    break;

                case 4:
                    insertLine();
                    break;

                case 5:
                    System.out.println("Вихід...");
                    return;

                default:
                    System.out.println("Невірний вибір!");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\nМеню:");
        System.out.println("1 - Додати кілька рядків");
        System.out.println("2 - Прочитати весь файл");
        System.out.println("3 - Прочитати діапазон рядків");
        System.out.println("4 - Вставити текст у вибраний рядок");
        System.out.println("5 - Вийти");
        System.out.print("Ваш вибір: ");
    }

    public static void writeMultipleLines() {

        System.out.print("Скільки рядків додати: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            for (int i = 1; i <= count; i++) {

                System.out.print("Рядок " + i + ": ");
                String text = scanner.nextLine();

                writer.write(text);
                writer.newLine();
            }

            System.out.println("Рядки записані!");

        } catch (IOException e) {
            System.out.println("Помилка запису!");
        }
    }

    public static void readFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            int lineNumber = 1;

            System.out.println("\nВміст файлу:");

            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }

        } catch (IOException e) {
            System.out.println("Помилка читання!");
        }
    }
    public static void readRange() {

        System.out.print("Початковий рядок: ");
        int start = scanner.nextInt();

        System.out.print("Кінцевий рядок: ");
        int end = scanner.nextInt();
        scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {

                if (lineNumber >= start && lineNumber <= end) {
                    System.out.println(lineNumber + ": " + line);
                }

                lineNumber++;
            }

        } catch (IOException e) {
            System.out.println("Помилка читання!");
        }
    }

    public static void insertLine() {

        System.out.print("В який рядок вставити текст: ");
        int position = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введіть текст: ");
        String newText = scanner.nextLine();

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            System.out.println("Помилка читання!");
            return;
        }

        if (position < 1 || position > lines.size() + 1) {
            System.out.println("Невірний номер рядка!");
            return;
        }

        lines.add(position - 1, newText);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Текст вставлено!");

        } catch (IOException e) {
            System.out.println("Помилка запису!");
        }
    }
}