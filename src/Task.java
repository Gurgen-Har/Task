import java.util.ArrayList;
import java.util.List;

public class Task {
    String taskName;
    List<String> description;
    int id;
    String status;
    Task(String taskName, ArrayList<String> description){
        this.taskName = taskName;
        this.description = new ArrayList<>(description);
        //this.description = description;

    }

}
