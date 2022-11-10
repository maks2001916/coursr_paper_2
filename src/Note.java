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
                    repeatStatusOne(date, dailyPlanner, keisKontains);
                    break;
                case 2:
                    repeatStatusDaily(dailyPlanner,keisKontains);
                    break;
                case 3:
                    repeatStatusWeekly(date, dailyPlanner, keisKontains);
                    break;
                case 4:
                    repeatStatusMonthly(date, dailyPlanner, keisKontains);
                    break;
                case 5:
                    repeatStatusAnnually(date, dailyPlanner, keisKontains);
                    break;
            }
        }

        return null;
    }

    public static void repeatStatusOne(String date, DailyPlanner dailyPlanner, ArrayList<Integer> keisKontains) {
        for (int i = 0; i < dailyPlanner.notesContain.size(); i++) {
            String dates = Integer.toString(dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getDayOfMonth());
            if (date.equals(dates)) {
                System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
            }
        }

    }

    public static void repeatStatusDaily(DailyPlanner dailyPlanner, ArrayList<Integer> keisKontains) {
        for (int i = 0; i < dailyPlanner.notesContain.size(); i++) {
            System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
        }
    }

    public static void repeatStatusWeekly(String date, DailyPlanner dailyPlanner, ArrayList<Integer> keisKontains) {
        for (int i = 0; i < dailyPlanner.notesContain.size(); i++) {
            int dateVariable = date.charAt(dailyPlanner.notesContain.size()-1) +
                    date.charAt(dailyPlanner.notesContain.size());
            if ((dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getDayOfYear() - dateVariable) % 7 == 0 ) {
                System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
            }
        }
    }

    public static void repeatStatusMonthly(String date, DailyPlanner dailyPlanner, ArrayList<Integer> keisKontains) {
        for (int i = 0; i < dailyPlanner.notesContain.size(); i++) {
            int dateVariable = date.charAt(dailyPlanner.notesContain.size()-1) +
                    date.charAt(dailyPlanner.notesContain.size());
            String dateVariables = String.valueOf(dateVariable);
            String dataVariableTwo = String.valueOf(dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getDayOfMonth());
            if (dataVariableTwo.equals(dateVariables)) {
                System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
            }
        }
    }

    public static void repeatStatusAnnually(String date, DailyPlanner dailyPlanner, ArrayList<Integer> keisKontains) {
        for (int i = 0; i < dailyPlanner.notesContain.size(); i++) {
            int dateVariable = date.charAt(dailyPlanner.notesContain.size()-4) +
                    date.charAt(dailyPlanner.notesContain.size()-3);
            int dateVariables = date.charAt(dailyPlanner.notesContain.size() - 1) +
                    date.charAt(dailyPlanner.notesContain.size());
            String dateIntermediate = String.valueOf(dateVariable) + '-' + String.valueOf(dateVariables);
            String dataVariableTwo = String.valueOf(dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getMonthValue()) +
                    '-' + String.valueOf(dailyPlanner.notesContain.get(keisKontains.get(i)).taskDateTime.getDayOfMonth());
            if (dateIntermediate.equals(dataVariableTwo) ) {
                System.out.println(dailyPlanner.notesContain.get(keisKontains.get(i)).toString());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note notes = (Note) o;
        return taskType == notes.taskType && id == notes.id && tupeRepeatability == notes.tupeRepeatability && Objects.equals(heading, notes.heading) && Objects.equals(description, notes.description) && Objects.equals(random, notes.random);
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

