import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

enum types{
    TASK,
    EPIC,
    SUBTASK
}
public class FileBackedTaskManager extends InMemoryTaskManager {

    String fileName = "C:\\Users\\Gurgen\\IdeaProjects\\Task\\src\\Data.csv";

    public FileBackedTaskManager(){

    }
    @Override
    public void createTasks(Task task){
        super.createTasks(task);
        save();
    }

    public void save(){

    }

    public String toString(Task task){
        StringBuilder value = new StringBuilder();
        value.append(task.id).append(",");
        value.append(types.valueOf(task.getClass().getSimpleName().toUpperCase())).append(",");
        value.append(task.taskName).append(",");
        value.append(task.status).append(",");
        value.append("Description ").append(task.description).append(",");
        if (task.getClass().getSimpleName().equalsIgnoreCase("SUBTASK")) {
            value.append(getEpicId((Subtask) task));
        }


        return value.toString();
    }

    public Task fromString(String value){
        Task task;
        String[] str = value.split(",");
        if (str[1].equals(types.TASK.toString())) {
            task = new Task(str[2], str[4]);
        } else if (str[1].equals(types.SUBTASK.toString())) {
            task = new Subtask(str[2], str[4]);
            ((Subtask) task).epicId = Integer.parseInt(str[5]);
        } else {
            task = new Epic(str[2], str[4]);
        }

        task.id = Integer.parseInt(str[0]);
        task.status = Status.valueOf(str[3]);
        return task ;
    }

    static String toString(HistoryManager manager){
        StringBuilder value = new StringBuilder();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < manager.getHistory().size(); i++ ) {
            list = Collections.singletonList(manager.getHistory().get(i).id);
            value.append(list.get(i));
        }

        return value.toString();
    }
    
    public static  List<Integer> FromString(String value){


        return null;
    }


}
