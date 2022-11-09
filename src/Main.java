import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTask(scanner);
                            break;
                        case 3:
                            getTasDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }
    static DailyPlanner dailyPlanner = new DailyPlanner();

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        dailyPlanner.addTask(scanner, dailyPlanner);
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Выберите задачу для удаления :");
        dailyPlanner.printNotes(dailyPlanner);
        String deleteName = scanner.next();
        int deleteNames = Integer.parseInt(deleteName);
        dailyPlanner.delete(deleteNames, dailyPlanner);

    }

    private static void getTasDay(Scanner scanner) {
        System.out.println("Укажите дату для получения заметки в формате гггг-мм-дд");
        String tasDayName = scanner.next();
        dailyPlanner.getDay(tasDayName, dailyPlanner);


    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                            """
        );

    }
}