import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Note {
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

