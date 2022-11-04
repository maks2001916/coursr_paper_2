import java.util.ArrayList;
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
                            // todo: обрабатываем пункт меню 3
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
    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        Notes notes = new Notes(taskName);
        dailyPlanner.setNotesContain(notes.getId(), notes);
        keysContains.add(notes.getId());
        inputDdescriptions(scanner, notes);
        // todo
    }

    private static void inputDdescriptions(Scanner scanner, Notes notes) {
        System.out.print("Введите описание задачи: ");
        String descriptionsName = scanner.next();
        dailyPlanner.notesContain.get(notes.getId()).setDescription(descriptionsName);
        inputType(scanner, notes);
    }

    private static void inputType(Scanner scanner, Notes notes) {
        System.out.print("Выберите тип задачи" + "\n"
                +  "1 - личная" + "\n" + "2 - деловая" + "\n");
        String tupeName = scanner.next();
        if (tupeName == "1") {
            dailyPlanner.notesContain.get(notes.getId()).setTaskType(true);
        } else {
            dailyPlanner.notesContain.get(notes.getId()).setTaskType(false);
        }
        choiceRepeatability(scanner, notes);
    }

    private static void choiceRepeatability(Scanner scanner, Notes notes) {
        System.out.println("Хотите указать повторяемость ?" + "\n"
        + "1 - да" + "\n" + "2 - нет");
        String repeatabilityName = scanner.next();
        if (repeatabilityName == "1") {
            inputRepeatability(scanner, notes);
        }
    }

    private static void inputRepeatability(Scanner scanner, Notes notes) {
        System.out.println("С какой переодичностью  вы хотите получать напоминание?" + "\n"
        + "1 - однократно" + "\n" + "2 - ежедневно" + "\n" +  "3 - еженедельно" + "\n" +
                "4 - ежемесячно" + "\n" + "5 - ежегодно");
        String repeatabilityName = scanner.next();
        dailyPlanner.notesContain.get(notes.getId()).nextNNote(repeatabilityName);
    }

    private static void deleteType(Scanner scanner) {
        int t = 1;
        System.out.print("Выберите задачу для удаления :");
        for (int i = 0; i < dailyPlanner.notesContain.size(); i++) {
            System.out.println("Заголовок - " + dailyPlanner.notesContain.get(keysContains.get(i)).getHeading() +
                   " - " + t);
            t++;
        }
        String deleteName = scanner.next();
        int deleteNames = Integer.parseInt(deleteName);
        dailyPlanner.notesContain.remove(keysContains.get(deleteNames));
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