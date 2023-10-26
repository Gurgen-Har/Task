import java.util.ArrayList;
import java.util.List;
enum Status{
    NEW,
    IN_PROGRESS,
    DONE

}
public class Task {
    String taskName;
    String description;
    int id;
    Status status;
    Task(String taskName, String description){
        this.taskName = taskName;
        this.description = description;

        //this.description = description;

    }

}
