import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DailyPlanner implements Repeatability {
    Map<Integer, Note> notesContain = new HashMap<>();


    static Note note = new Note();
    public void addTask(String taskName) {
        if (taskName != null || !taskName.isEmpty() || !taskName.isBlank()) {
            note.setHeading(taskName);
            this.setNotesContain(note.getId(), note);
        } else {
            System.out.println("Введено не верное значение");
            addTask(taskName);
        }
    }

    public void inputDescriptionsLogic(String descriptionsName) {

        if (descriptionsName != null || !descriptionsName.isBlank() || !descriptionsName.isEmpty()) {
            this.notesContain.get(note.getId()).setDescription(descriptionsName);
        } else {
            System.out.println("Введено не верное значение");
            inputDescriptionsLogic(descriptionsName);
        }
    }

    public void inputTypeLogic(String tupeName) {
        int t = Integer.parseInt(tupeName);
        if (tupeName != null || !tupeName.isEmpty() || !tupeName.isBlank() || t < 3 ) {
            if (tupeName.equals("1")) {
                this.notesContain.get(note.getId()).setTaskType(true);
            } else if (tupeName.equals("2")) {
                this.notesContain.get(note.getId()).setTaskType(false);
            }
        } else {
            System.out.println("Введено не верное значение");
            inputTypeLogic(tupeName);
        }
    }

    public void inputRepeatabilityLogic(String repeatabilityName) {
        if (repeatabilityName != null || !repeatabilityName.isBlank() || !repeatabilityName.isEmpty()) {
            int repeatabilityNames = Integer.parseInt(repeatabilityName);
            this.notesContain.get(note.getId()).setTupeRepeatability(repeatabilityNames);
        } else {
            System.out.println("Введено не верное значение");
            inputRepeatabilityLogic(repeatabilityName);
        }
    }

    public void printNotes() {
        for (int i = 0; i < this.notesContain.size(); i++) {
            System.out.println("Заголовок - " + this.notesContain.values());
        }
    }

    public void delete(int deleteNames) {
            this.notesContain.remove(deleteNames);
            System.out.println("Заметка удалена");
    }

    public void getDay(LocalDate tasDayName) {
        if (tasDayName != null || tasDayName.getDayOfMonth() > 10 ) {
            this.notesContain.get(nextNNote(tasDayName));
        } else {
            System.out.println("Введено не верное значение");
            getDay(tasDayName);
        }
    }
    @Override
    public Object nextNNote(LocalDate date) {
        for (Note note: this.notesContain.values()) {
            switch (note.getTupeRepeatability()) {
                case 1:
                    if (note.getTaskDateTime().toLocalDate().equals(date)) {
                        System.out.println(note);
                    }
                    break;
                case 2:
                    if (note.getTaskDateTime().toLocalDate().equals(date) ||
                            note.getTaskDateTime().toLocalDate().isBefore(date)) {
                        System.out.println(note);
                    }
                    break;
                case 3:
                    if (note.getTaskDateTime().toLocalDate().equals(date) ||
                            (note.getTaskDateTime().toLocalDate().isBefore(date) &&
                            note.getTaskDateTime().getDayOfWeek().equals(date.getDayOfWeek()))) {
                        System.out.println(note);
                    }
                    break;
                case 4:
                    if (note.getTaskDateTime().toLocalDate().equals(date) ||
                            (note.getTaskDateTime().toLocalDate().isBefore(date) &&
                                    note.getTaskDateTime().getDayOfMonth() == date.getDayOfMonth())) {
                        System.out.println(note);
                    }
                    break;
                case 5:
                    if (note.getTaskDateTime().toLocalDate().equals(date) ||
                            (note.getTaskDateTime().toLocalDate().isBefore(date) &&
                                    note.getTaskDateTime().getDayOfYear() == date.getDayOfYear())) {
                        System.out.println(note);
                    }
                    break;
            }
        }

        return null;
    }
    public void setNotesContain(Integer key, Note notesContain) {
        this.notesContain.put(key, notesContain);
    }
}
