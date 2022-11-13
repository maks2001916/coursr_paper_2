import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DailyPlanner {
    Map<Integer, Note> notesContain = new HashMap<>();

    static ArrayList<Integer> keysContains = new ArrayList<>();
    static Note note = new Note();
    public void addTask(String taskName) {
        if (taskName != null || !taskName.isEmpty() || !taskName.isBlank()) {
            note.setHeading(taskName);
            this.setNotesContain(note.getId(), note);
            keysContains.add(note.getId());
        } else {
            System.out.println("Введено не верное значение");
            addTask(taskName);
        }
        inputDescriptions(taskName);
    }

    private void inputDescriptions(String descriptionsName) {
        System.out.print("Введите описание задачи: ");
        if (descriptionsName != null || !descriptionsName.isBlank() || !descriptionsName.isEmpty()) {
            this.notesContain.get(note.getId()).setDescription(descriptionsName);
        } else {
            System.out.println("Введено не верное значение");
            inputDescriptions(descriptionsName);
        }
        inputType(descriptionsName);
    }

    private void inputType(String tupeName) {
        System.out.print("""
                Выберите тип задачи
                1 - личная
                2 - деловая
                """);
        int t = Integer.parseInt(tupeName);
        if (tupeName != null || !tupeName.isEmpty() || !tupeName.isBlank() || t < 3 ) {
            if (tupeName.equals("1")) {
                this.notesContain.get(note.getId()).setTaskType(true);
            } else if (tupeName.equals("2")) {
                this.notesContain.get(note.getId()).setTaskType(false);
            }
        } else {
            System.out.println("Введено не верное значение");
            inputType(tupeName);
        }
        inputRepeatability(tupeName);
    }

    private void inputRepeatability(String repeatabilityName) {
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
            this.notesContain.get(note.getId()).setTupeRepeatability(repeatabilityNames);
        } else {
            System.out.println("Введено не верное значение");
            inputRepeatability(repeatabilityName);
        }
    }

    public void printNotes() {
        for (int i = 0; i < this.notesContain.size(); i++) {
            System.out.println("Заголовок - " + this.notesContain.get(keysContains.get(i)).getHeading() +
                    " - " + i);
        }
    }

    public void delete(int deleteNames) {
        if (deleteNames > this.notesContain.size()-1) {
            System.out.println("такой заметки нет");
        } else {
            this.notesContain.remove(keysContains.get(deleteNames));
            System.out.println("Заметка удалена");
        }
    }

    public void getDay(String tasDayName) {
        if (tasDayName != null || tasDayName.length() > 10 || !tasDayName.isEmpty() || !tasDayName.isBlank()) {
            this.notesContain.get(note.nextNNote(tasDayName, this, keysContains));
        } else {
            System.out.println("Введено не верное значение");
            getDay(tasDayName);
        }
    }

    public void setNotesContain(Integer key, Note notesContain) {
        this.notesContain.put(key, notesContain);
    }
}
