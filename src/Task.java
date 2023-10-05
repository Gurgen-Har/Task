import java.util.ArrayList;

public class Task {
    String name;
    ArrayList<String> description;

    String status;
    public Task(String name, ArrayList<String> description){
        this.name = name;
        this.description = description;
        this.status = "New";
    }
}
