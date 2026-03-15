import java.io.*;
import java.util.Scanner;

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
                    writeToFile();
                    break;

                case 2:
                    readFile();
                    break;

                case 3:
                    System.out.println("Вихід з редактора...");
                    return;

                default:
                    System.out.println("Невірний вибір!");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\nМеню:");
        System.out.println("1 - Записати до файлу");
        System.out.println("2 - Прочитати файл");
        System.out.println("3 - Вийти");
        System.out.print("Ваш вибір: ");
    }

    public static void writeToFile() {
        try {
            FileWriter writer = new FileWriter(fileName, true);

            System.out.print("Введіть рядок: ");
            String text = scanner.nextLine();

            writer.write(text + "\n");
            writer.close();

            System.out.println("Рядок записано!");

        } catch (IOException e) {
            System.out.println("Помилка запису у файл!");
        }
    }

    public static void readFile() {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            System.out.println("\nВміст файлу:");

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Помилка читання файлу!");
        }
    }
}