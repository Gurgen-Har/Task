import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    ArrayList<Task> history = new ArrayList<>(10);
    @Override
    public void add(Task task) {

    }

    @Override
    public ArrayList<Task> getHistory() {
        return history;
    }


}
