import java.util.Objects;
import java.util.Random;

public class Notes implements Repeatability {
    private String heading;
    private String description;
    private boolean taskType;
    private String date;
    private String time;
    private int id;
    String LocalDateTime = new String();
    String LocalDate = new String();
    Random random;
    public Notes(String heading) {
        this.heading = heading;
        this.date = LocalDate;
        random = new Random();
        id = random.nextInt(10000);
        this.time = LocalDateTime;

    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public boolean isTaskType() {
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

    @Override
    public void nextNNote(String string) {
        switch (string) {
            case "1":
                repeatStatusOne();
            case "2":
        }
    }

    public static void repeatStatusOne() {
        boolean u = true;
        if (u == true) {

        }
        u = false;
    }

    public void gettingTaskSpecifiedDay() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return taskType == notes.taskType && Objects.equals(heading, notes.heading) && Objects.equals(description, notes.description) && Objects.equals(date, notes.date) && Objects.equals(time, notes.time) && Objects.equals(id, notes.id) && Objects.equals(LocalDateTime, notes.LocalDateTime) && Objects.equals(LocalDate, notes.LocalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, description, taskType, date, time, id, LocalDateTime, LocalDate);
    }

    @Override
    public String toString() {
        return "Notes{" +
                "heading='" + heading + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", id='" + id + '\'' +
                ", LocalDateTime='" + LocalDateTime + '\'' +
                ", LocalDate='" + LocalDate + '\'' +
                '}';
    }



}

