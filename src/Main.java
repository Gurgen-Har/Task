import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        ArrayList<Task> history;
        Epic epic = new Epic("Домашние дела", "Покупка");
        int epicId = inMemoryTaskManager.createEpic(epic);



        Subtask subtask = new Subtask("Покупка","Еда");
        inMemoryTaskManager.createSubtask(subtask, epicId);


        Subtask subtask1 = new Subtask("Распаковка", "Коробки");


        //manager.createTasks(task);
        inMemoryTaskManager.createSubtask(subtask1, epicId);

        Task task = new Task("Убка", "Покупка");
        inMemoryTaskManager.createTasks(task);


        Task task1 = new Task("борка", "Еда");
        inMemoryTaskManager.createTasks(task1);



        //inMemoryTaskManager.getAllTasks();
        inMemoryTaskManager.getTask(3);
        inMemoryTaskManager.getTask(4);

        inMemoryTaskManager.getEpic(0);
        inMemoryTaskManager.getTask(3);

        inMemoryTaskManager.getEpic(0);

        //inMemoryTaskManager.getSubtask(1);
        history = new ArrayList<>(inMemoryTaskManager.history.getHistory());
        for(int i = 0; i < history.size(); i++){
            System.out.println(history.get(i).id);
        }
        String fileName = "Data.csv";
        Path path = Path.of(fileName);
        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(path);
        fileBackedTaskManager.save();
        FileBackedTaskManager.loadFromFile(path);





    }
}