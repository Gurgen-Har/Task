import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        ArrayList<String> description = new ArrayList<>();
        ArrayList<Task> history;
        description.add("Покупка");
        description.add("Распаковка");


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

        description.clear();
        description.add("Покуп");
        description.add("Рас");
        //manager.createTasks(task);
        inMemoryTaskManager.createSubtask(subtask1, epicId);

        Task task = new Task("Убка", description);
        inMemoryTaskManager.createTasks(task);

        description.clear();
        description.add("Покупra");
        description.add("Расtor");
        Task task1 = new Task("борка", description);
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



    }
}