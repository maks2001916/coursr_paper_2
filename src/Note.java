import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Note implements Repeatability {
    private String heading;
    private String description;
    private boolean taskType;
    LocalDateTime taskDateTime;

    private final int id;
    private int tupeRepeatability;
    Random random;
    public Note() {
        random = new Random();
        id = random.nextInt(10000);
    }

    public int getTupeRepeatability() {
        return tupeRepeatability;
    }

    public void setTupeRepeatability(int tupeRepeatability) {
        this.tupeRepeatability = tupeRepeatability;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public boolean grtTaskType() {
        return taskType;
    }

    public void setTaskType(boolean taskType) {
        this.taskType = taskType;
    }

    public boolean getTaskType() {
        return taskType;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void inputTasks(Scanner scanner,Note notes, DailyPlanner dailyPlanner,
                                  ArrayList<Integer> keysContains) {
        System.out.print("Введите название задачи: ");
        dailyPlanner.setNotesContain(notes.getId(), notes);
        keysContains.add(notes.getId());
    }

    @Override
    public Object nextNNote(String date, DailyPlanner dailyPlanner, ArrayList<Integer> keisKontains) {
        for (int i = 0; i < dailyPlanner.notesContain.size(); i++) {
            switch (dailyPlanner.notesContain.get(keisKontains.get(i)).getTupeRepeatability()) {
                case 1:
                    String dates = Integer.toString(dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getDayOfMonth());
                    if (date.equals(dates)) {
                        System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
                    }
                    break;
                case 2:
                    int dateVariableOneDaily = date.charAt(dailyPlanner.notesContain.size()-1) +
                            date.charAt(dailyPlanner.notesContain.size());
                    int dateVariableTwoDaily = date.charAt(dailyPlanner.notesContain.size()-1) +
                            date.charAt(dailyPlanner.notesContain.size());
                    if (dateVariableTwoDaily >= dateVariableOneDaily ) {
                        System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
                    }
                    break;
                case 3:
                    int dateVariableOne = date.charAt(dailyPlanner.notesContain.size()-1) +
                            date.charAt(dailyPlanner.notesContain.size());
                    if ((dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getDayOfYear() - dateVariableOne) % 7 == 0 ) {
                        System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
                    }
                    break;
                case 4:
                    int dateVariableDate = date.charAt(dailyPlanner.notesContain.size()-1) +
                            date.charAt(dailyPlanner.notesContain.size());
                    int dateVariableMonth = date.charAt(dailyPlanner.notesContain.size()-4) +
                            date.charAt(dailyPlanner.notesContain.size()-3) + dateVariableDate;
                    String dateVariablesDate = String.valueOf(dateVariableDate);
                    String dataVariableTwoDate = String.valueOf(dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getDayOfMonth());
                    if (dataVariableTwoDate.equals(dateVariablesDate) || dateVariableMonth == dailyPlanner.
                            notesContain.get(keisKontains.get(i)).taskDateTime.getMonthValue()) {
                        System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
                    }
                    break;
                case 5:
                    int dateVariableAnnually = date.charAt(dailyPlanner.notesContain.size()-4) +
                            date.charAt(dailyPlanner.notesContain.size()-3);
                    int dateVariablesAnnually = date.charAt(dailyPlanner.notesContain.size() - 1) +
                            date.charAt(dailyPlanner.notesContain.size());
                    String dateIntermediate = String.valueOf(dateVariableAnnually) + '-' + String.valueOf(dateVariablesAnnually);
                    String dataVariableTwoAnnually = String.valueOf(dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getMonthValue()) +
                            '-' + String.valueOf(dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getDayOfMonth());
                    if (dateIntermediate.equals(dataVariableTwoAnnually) ) {
                        System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
                    }
                    break;
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note notes = (Note) o;
        return taskType == notes.taskType && id == notes.id && tupeRepeatability == notes.tupeRepeatability &&
                Objects.equals(heading, notes.heading) && Objects.equals(description, notes.description) &&
                Objects.equals(random, notes.random);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, description, taskType, id, tupeRepeatability, random);
    }

    @Override
    public String toString() {
        return "Notes{" +
                "heading='" + heading + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", date='" + taskDateTime.toString() +
                ", id=" + id +
                ", tupeRepeatability=" + tupeRepeatability +
                '}';
    }
}

