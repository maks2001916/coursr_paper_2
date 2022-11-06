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
                            deleteType(scanner);
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
    static ArrayList<Integer> keysContains = new ArrayList<>();
    static Notes notes = new Notes();
    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        if (taskName != null || !taskName.isEmpty() || !taskName.isBlank()) {
            notes.setHeading(taskName);
            dailyPlanner.setNotesContain(notes.getId(), notes);
            keysContains.add(notes.getId());
        } else {
            System.out.println("Введено не верное значение");
            inputTask(scanner);
        }

        inputDdescriptions(scanner);
    }

    private static void inputDdescriptions(Scanner scanner) {
        System.out.print("Введите описание задачи: ");
        String descriptionsName = scanner.next();
        if (descriptionsName != null || !descriptionsName.isBlank() || !descriptionsName.isEmpty()) {
            dailyPlanner.notesContain.get(notes.getId()).setDescription(descriptionsName);
        } else {
            System.out.println("Введено не верное значение");
            inputDdescriptions(scanner);
        }
        inputType(scanner);
    }

    private static void inputType(Scanner scanner) {
        System.out.print("""
                Выберите тип задачи
                1 - личная
                2 - деловая
                """);
        String tupeName = scanner.next();
        int t = Integer.parseInt(tupeName);
        if (tupeName != null || !tupeName.isEmpty() || !tupeName.isBlank() || t < 3 ) {
            if (tupeName.equals("1")) {
                dailyPlanner.notesContain.get(notes.getId()).setTaskType(true);
            } else if (tupeName.equals("2")) {
                dailyPlanner.notesContain.get(notes.getId()).setTaskType(false);
            }
        } else {
            System.out.println("Введено не верное значение");
            inputType(scanner);
        }
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
        String repeatabilityName = scanner.next();
        if (repeatabilityName != null || !repeatabilityName.isBlank() || !repeatabilityName.isEmpty()) {
            int repeatabilityNames = Integer.parseInt(repeatabilityName);
            dailyPlanner.notesContain.get(notes.getId()).setTupeRepeatability(repeatabilityNames);
        } else {
            System.out.println("Введено не верное значение");
            inputRepeatability(scanner);
        }

    }

    private static void deleteType(Scanner scanner) {
        System.out.print("Выберите задачу для удаления :");
        for (int i = 0; i < dailyPlanner.notesContain.size(); i++) {
            System.out.println("Заголовок - " + dailyPlanner.notesContain.get(keysContains.get(i)).getHeading() +
                   " - " + i);
        }
        String deleteName = scanner.next();
        int deleteNames = Integer.parseInt(deleteName);
        if (deleteNames > dailyPlanner.notesContain.size()-1) {
            System.out.println("такой заметки нет");
        } else {
            dailyPlanner.notesContain.remove(keysContains.get(deleteNames));
            System.out.println("Заметка удалена");
        }
    }

    private static void getTasDay(Scanner scanner) {
        System.out.println("Укажите дату для получения заметки в формате гггг-мм-дд");
        String tasDayName = scanner.next();
        if (tasDayName != null || tasDayName.length() > 10 || !tasDayName.isEmpty() || !tasDayName.isBlank()) {
            dailyPlanner.notesContain.get(notes.nextNNote(tasDayName, dailyPlanner, keysContains));
        } else {
            System.out.println("Введено не верное значение");
            getTasDay(scanner);
        }

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