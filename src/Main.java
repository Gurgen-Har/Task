import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        ArrayList<String> description = new ArrayList<>();
        ArrayList<Task> history;
        description.add("Покупка");
        description.add("Распаковка");
      //  Task task = new Task("Уборка", description);

        Epic epic = new Epic("Домашние дела", description);
        int epicId = inMemoryTaskManager.createEpic(epic);
        description.clear();
        description.add("Еда");
        description.add("швабра");
        description.add("кастрюля ");

        Subtask subtask = new Subtask("Покупка",description);
        inMemoryTaskManager.createSubtask(subtask, epicId);

        description.clear();
        description.add("Коробки");
        description.add("Еда");
        Subtask subtask1 = new Subtask("Распаковка", description);

        //manager.createTasks(task);


        inMemoryTaskManager.createSubtask(subtask1, epicId);
        //inMemoryTaskManager.getAllTasks();
        inMemoryTaskManager.getEpic(0);
        inMemoryTaskManager.getSubtask(1);


    }
}