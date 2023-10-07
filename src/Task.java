import java.util.ArrayList;
import java.util.List;
enum Status{
    NEW,
    IN_PROGRESS,
    DONE

}
public class Task {
    String taskName;
    List<String> description;
    int id;
    Status status;
    Task(String taskName, ArrayList<String> description){
        this.taskName = taskName;
        this.description = new ArrayList<>(description);
        //this.description = description;

    }

}
