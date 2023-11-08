
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
        super();
        this.path = path;

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
            writer.write(getId() - 1);

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
        // Переписать

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

            return task;

        } else if (str[1].equals(types.SUBTASK.toString())) {
            subtask = new Subtask(str[2], str[4],Integer.parseInt(str[5]));
            subtask.id = Integer.parseInt(str[0]);
            subtask.status = Status.valueOf(str[3]);

            return subtask;
        } else {
            epic = new Epic(str[2], str[4]);
            epic.id = Integer.parseInt(str[0]);
            epic.status = Status.valueOf(str[3]);

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
    public static FileBackedTaskManager loadFromFile(Path path) throws IOException {
        String[] str = Files.readString(path).split("\n");
        str[0] = "ex";
        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(path);
        Task task = null;
        for (String tmp : str) {
            if (tmp.equals("ex")) {
                continue;
            }
            if(!tmp.equals("")) {
                task = fileBackedTaskManager.fromString(tmp);

                switch (types.valueOf(task.getClass().getSimpleName().toUpperCase())) {
                    case EPIC:
                        //fileBackedTaskManager.epicContainer.put(task.id, (Epic) task);
                        fileBackedTaskManager.createEpic((Epic) task);
                        break;
                    case TASK:
                        //fileBackedTaskManager.taskContainer.put(task.id, task);
                        fileBackedTaskManager.createTasks(task);
                        break;
                    case SUBTASK:
                       // fileBackedTaskManager.subtaskContainer.put(task.id, (Subtask) task);
                        fileBackedTaskManager.createSubtask((Subtask) task,getEpicId((Subtask) task));
                        break;
                }
            } else {
                break;
            }
        }
        fileBackedTaskManager.id = Integer.parseInt(str[str.length - 2]);

        List<Integer> historyId = FromString(str[str.length - 1]);
        for (int i : historyId) {
            if (fileBackedTaskManager.taskContainer.containsKey(i)) {
                fileBackedTaskManager.history.add(fileBackedTaskManager.taskContainer.get(i));
            } else if (fileBackedTaskManager.subtaskContainer.containsKey(i)) {
                fileBackedTaskManager.history.add(fileBackedTaskManager.subtaskContainer.get(i));
            } else if (fileBackedTaskManager.epicContainer.containsKey(i)) {
                fileBackedTaskManager.history.add(fileBackedTaskManager.epicContainer.get(i));
            }

        }

        return fileBackedTaskManager;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "Data.csv";
        Path path = Path.of(fileName);

        FileBackedTaskManager fileBackedTaskManager = loadFromFile(path);

    }


    @Override
    public void createTasks(Task task){
        super.createTasks(task);
        save();
    }

    @Override
    public void createEpic(Epic epic){
        super.createEpic(epic);
        save();
    }
    @Override
    public void createSubtask(Subtask subtask, int id){
        super.createSubtask(subtask, id);
        save();
    }
    @Override
    public void updateTask(int id, Status status){
        super.updateTask(id,status);

    }
    @Override
    public void updateSubtask(int id, Status status){
        super.updateSubtask(id,status);
    }
    @Override
    public void updateEpic(Epic epic){
        super.updateEpic(epic);
        save();
    }




}


/*
* Переопределить все методы изменения задач и проверить их работу
*
*
*
*  */
