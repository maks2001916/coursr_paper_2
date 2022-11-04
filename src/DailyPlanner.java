import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DailyPlanner {
    Map<Integer, Notes> notesContain = new HashMap<>();

    public void setNotesContain(Integer key, Notes notesContain) {
        this.notesContain.put(key, notesContain);
    }
}
