import java.util.ArrayList;

class Subtask extends Task{
    protected int epicId;
    Subtask(String taskName, String description, int epicId) {
        super(taskName, description);
        this.epicId = epicId;
    }

    Subtask(String taskName, String description,int id ,int epicId, Status status) {
        super(taskName, description,id,status);
        this.epicId = epicId;
        this.id = id;
        this.status = status;
    }
    int getSubtaskId(){
        return id;
    }





}
