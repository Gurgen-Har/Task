import java.util.ArrayList;

class Epic extends Subtask{
    protected ArrayList<Integer> subtaskId;
    Epic(String taskName, ArrayList<String> description) {
        super(taskName, description);
        //this.description = description;
        subtaskId = new ArrayList<>();

    }
}
