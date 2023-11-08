import java.util.ArrayList;

class Subtask extends Task{
    protected int epicId;
    Subtask(String taskName, String description, int epicId) {
        super(taskName, description);
        this.epicId = epicId;
    }
    int getSubtaskId(){
        return id;
    }





}
