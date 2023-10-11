
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    List<Task> history = new ArrayList<>(10);
    @Override
    public void add(Task task) {
        history.add(task);

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Task> getHistory() {
        return history;
    }


}
