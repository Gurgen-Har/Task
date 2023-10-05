import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        ArrayList<String> description = new ArrayList<>();
        description.add("Покупка");
        description.add("Распаковка");
      //  Task task = new Task("Уборка", description);

        Epic epic = new Epic("Домашние дела", description);
        int epicId = manager.createEpic(epic);
        description.clear();
        description.add("Еда");
        description.add("швабра");
        description.add("кастрюля ");

        Subtask subtask = new Subtask("Покупка",description);
        manager.createSubtask(subtask, epicId);

        description.clear();
        description.add("Коробки");
        description.add("Еда");
        Subtask subtask1 = new Subtask("Распаковка", description);

        //manager.createTasks(task);


        manager.createSubtask(subtask1, epicId);
        manager.getAllTasks();
    }
}