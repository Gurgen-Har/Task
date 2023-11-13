import java.util.ArrayList;

class Epic extends Task{
    protected ArrayList<Integer> subtaskId;
    Epic(String taskName, String description) {
        super(taskName, description);
        //this.description = description;
        subtaskId = new ArrayList<>();
    }

    Epic(String taskName, String description, int id, Status status){
        super(taskName, description,id,status);
        this.id = id;
        this.status = status;
        subtaskId = new ArrayList<>();

    }
    int getEpicId(){
        return id;
    }
    @Override
    Status getStatus(){
        return super.getStatus();
    }
}
