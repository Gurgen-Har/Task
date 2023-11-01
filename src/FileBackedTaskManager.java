import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    Paths path;
    public FileBackedTaskManager(Paths path){
        this.path = path;
    }
    @Override
    public void createTasks(Task task){
        super.createTasks(task);
        save();
    }
    //нужно перезаписывать состояние менеджера при каждом вызове
    public void save() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < id; i++) {
            if (taskContainer.containsKey(i)) {
                list.add(toString(taskContainer.get(i)));
            } else if (subtaskContainer.containsKey(i)) {
                list.add(toString(subtaskContainer.get(i)));
            } else if (epicContainer.containsKey(i)) {
                list.add(toString(epicContainer.get(i)));
            }
        }




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

        List<Integer> list = new ArrayList<>();
        String[] str = value.split(",");
        for (String part : str) {
            list.add(Integer.parseInt(part));
        }
        return list;
    }


}
