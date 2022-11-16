import java.time.LocalDate;
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
        dailyPlanner.addTask(taskName);
        inputDescriptions(scanner);
    }

    private static void inputDescriptions(Scanner scanner) {
        System.out.print("Введите описание задачи: ");
        String descriptionsName = scanner.next();
        dailyPlanner.inputDescriptionsLogic(descriptionsName);
        inputType(scanner);
    }

    private static void inputType(Scanner scanner) {
        System.out.print("""
                Выберите тип задачи
                1 - личная
                2 - деловая
                """);
        String inputTypeName = scanner.next();
        dailyPlanner.inputTypeLogic(inputTypeName);
        inputRepeatability(scanner);
    }

    private static void inputRepeatability(Scanner scanner) {
        System.out.print("""
         С какой переодичностью  вы хотите получать напоминание?
         1 - однократно
         2 - ежедневно
         3 - еженедельно
         4 - ежемесячно
         5 - ежегодно
                """);
        String inputRepeatabilityName = scanner.next();
        dailyPlanner.inputRepeatabilityLogic(inputRepeatabilityName);
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Выберите задачу для удаления :");
        dailyPlanner.printNotes();
        String deleteName = scanner.next();
        int deleteNames = Integer.parseInt(deleteName);
        dailyPlanner.delete(deleteNames);

    }

    private static void getTasDay(Scanner scanner) {
        System.out.println("Укажите дату для получения заметки в формате гггг-мм-дд");
        String tasDayName = scanner.next();
        LocalDate receivedDate = LocalDate.parse(tasDayName);
        dailyPlanner.getDay(receivedDate);


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