import java.util.ArrayList;

class Epic extends Task{
    protected ArrayList<Integer> subtaskId;
    Epic(String taskName, String description) {
        super(taskName, description);
        //this.description = description;
        subtaskId = new ArrayList<>();
    }
    int getEpicId(){
        return id;
    }
}
