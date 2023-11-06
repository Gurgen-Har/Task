
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum types{
    TASK,
    EPIC,
    SUBTASK
}
public class FileBackedTaskManager extends InMemoryTaskManager {

   // String fileName = "C:\\Users\\Gurgen\\IdeaProjects\\Task\\src\\Data.csv";
    Path path;
    public FileBackedTaskManager(Path path){
        this.path = path;
    }
    @Override
    public void createTasks(Task task){
        super.createTasks(task);
        save();
    }
    //нужно перезаписывать состояние менеджера при каждом вызове
    public void save() {
        List<String> tasks = new ArrayList<>();
        tasks.add("id,type,name,status,description,epic");
        String historyManager = toString(history);
        for (int i = 0; i < id; i++) {
            if (taskContainer.containsKey(i)) {
                tasks.add(toString(taskContainer.get(i)));
            } else if (subtaskContainer.containsKey(i)) {
                tasks.add(toString(subtaskContainer.get(i)));
            } else if (epicContainer.containsKey(i)) {
                tasks.add(toString(epicContainer.get(i)));
            }
        }
        //Добавить запись данных в файл с перезаписью элементов каждый раз при вызове
        try {
            FileWriter writer = new FileWriter(path.toFile(), false);


            writer.write("");
            for (String str : tasks) {
                writer.write(str);
                writer.write("\n");
            }
            writer.write("\n");
            writer.write(historyManager);

            // Закройте FileWriter
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
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
        Subtask subtask;
        Epic epic;
        String[] str = value.split(",");

        if (str[1].equals(types.TASK.toString())) {
            task = new Task(str[2], str[4]);
            task.id = Integer.parseInt(str[0]);
            task.status = Status.valueOf(str[3]);
            taskContainer.put(task.id, task);
            return task;

        } else if (str[1].equals(types.SUBTASK.toString())) {
            subtask = new Subtask(str[2], str[4]);
            subtask.epicId = Integer.parseInt(str[5]);
            subtask.id = Integer.parseInt(str[0]);
            subtask.status = Status.valueOf(str[3]);
            subtaskContainer.put(subtask.id, subtask);
            return subtask;
        } else {
            epic = new Epic(str[2], str[4]);
            epic.id = Integer.parseInt(str[0]);
            epic.status = Status.valueOf(str[3]);
            epicContainer.put(epic.id, epic);
            return epic;
        }



    }

    static String toString(HistoryManager manager){
        StringBuilder value = new StringBuilder();

        List<Integer> list = new ArrayList<>();
        List<Task> history = new ArrayList<>(manager.getHistory());
        for (int i = 0; i < history.size(); i++ ) {
            list.add(history.get(i).id);
            value.append(list.get(i)).append(",");
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
    public static void loadFromFile(Path path) throws IOException {
        String[] str = Files.readString(path).split("\n");

        for (String string : str) {
            fromString(string)
        }

    }


}
