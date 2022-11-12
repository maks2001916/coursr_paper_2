import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DailyPlanner {
    Map<Integer, Note> notesContain = new HashMap<>();

    static ArrayList<Integer> keysContains = new ArrayList<>();
    static Note note = new Note();
    public void addTask(String taskName, DailyPlanner dailyPlanner) {
        if (taskName != null || !taskName.isEmpty() || !taskName.isBlank()) {
            note.setHeading(taskName);
            dailyPlanner.setNotesContain(note.getId(), note);
            keysContains.add(note.getId());
        } else {
            System.out.println("Введено не верное значение");
            addTask(taskName, dailyPlanner);
        }
        inputDescriptions(taskName, dailyPlanner);
    }

    private static void inputDescriptions(String descriptionsName, DailyPlanner dailyPlanner) {
        System.out.print("Введите описание задачи: ");
        if (descriptionsName != null || !descriptionsName.isBlank() || !descriptionsName.isEmpty()) {
            dailyPlanner.notesContain.get(note.getId()).setDescription(descriptionsName);
        } else {
            System.out.println("Введено не верное значение");
            inputDescriptions(descriptionsName, dailyPlanner);
        }
        inputType(descriptionsName, dailyPlanner);
    }

    private static void inputType(String tupeName, DailyPlanner dailyPlanner) {
        System.out.print("""
                Выберите тип задачи
                1 - личная
                2 - деловая
                """);
        int t = Integer.parseInt(tupeName);
        if (tupeName != null || !tupeName.isEmpty() || !tupeName.isBlank() || t < 3 ) {
            if (tupeName.equals("1")) {
                dailyPlanner.notesContain.get(note.getId()).setTaskType(true);
            } else if (tupeName.equals("2")) {
                dailyPlanner.notesContain.get(note.getId()).setTaskType(false);
            }
        } else {
            System.out.println("Введено не верное значение");
            inputType(tupeName, dailyPlanner);
        }
        inputRepeatability(tupeName, dailyPlanner);
    }

    private static void inputRepeatability(String repeatabilityName, DailyPlanner dailyPlanner) {
        System.out.print("""
         С какой переодичностью  вы хотите получать напоминание?
         1 - однократно
         2 - ежедневно
         3 - еженедельно
         4 - ежемесячно
         5 - ежегодно
                """);
        if (repeatabilityName != null || !repeatabilityName.isBlank() || !repeatabilityName.isEmpty()) {
            int repeatabilityNames = Integer.parseInt(repeatabilityName);
            dailyPlanner.notesContain.get(note.getId()).setTupeRepeatability(repeatabilityNames);
        } else {
            System.out.println("Введено не верное значение");
            inputRepeatability(repeatabilityName, dailyPlanner);
        }
    }

    public void printNotes(DailyPlanner dailyPlanner) {
        for (int i = 0; i < dailyPlanner.notesContain.size(); i++) {
            System.out.println("Заголовок - " + dailyPlanner.notesContain.get(keysContains.get(i)).getHeading() +
                    " - " + i);
        }
    }

    public void delete(int deleteNames, DailyPlanner dailyPlanner) {
        if (deleteNames > dailyPlanner.notesContain.size()-1) {
            System.out.println("такой заметки нет");
        } else {
            dailyPlanner.notesContain.remove(keysContains.get(deleteNames));
            System.out.println("Заметка удалена");
        }
    }

    public void getDay(String tasDayName, DailyPlanner dailyPlanner) {
        if (tasDayName != null || tasDayName.length() > 10 || !tasDayName.isEmpty() || !tasDayName.isBlank()) {
            dailyPlanner.notesContain.get(note.nextNNote(tasDayName, dailyPlanner, keysContains));
        } else {
            System.out.println("Введено не верное значение");
            getDay(tasDayName, dailyPlanner);
        }
    }

    public void setNotesContain(Integer key, Note notesContain) {
        this.notesContain.put(key, notesContain);
    }
}
