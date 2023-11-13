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
    int id ;
    Status status;
    Task(String taskName, String description){
        this.taskName = taskName;
        this.description = description;
        id = 0;
        //this.description = description;

    }

    Task(String taskName, String description, int id, Status status){
        this.taskName = taskName;
        this.description = description;
        this.id = id;
        this.status = status;
        //this.description = description;

    }
    Status getStatus(){
        return status;
    }

}
