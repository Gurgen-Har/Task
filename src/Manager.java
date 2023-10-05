import java.util.HashMap;

public class Manager {
    int id = 0;
    HashMap<Integer, Task> taskContainer = new HashMap<>();
    HashMap<Integer, Subtask> subtaskContainer = new HashMap<>();
    HashMap<Integer, Epic> epicContainer = new HashMap<>();

    int getId(){
        return id++;
    }
    public void getAllTasks(){
        if (!taskContainer.isEmpty()){
            for (Integer id : taskContainer.keySet()){
                printTask(taskContainer.get(id));
            }
        }
        if (!epicContainer.isEmpty()){
            for (Integer id : epicContainer.keySet()){
                printEpic(epicContainer.get(id));
            }
        }


    }

    public void deleteAllTasks(){}

    public void getTasksFromId(){}

    public void createTasks(Task task){
        task.id = getId();
        task.status = "New";
        taskContainer.put(task.id, task);
    }

    public int createEpic(Epic epic){
        epic.id = getId();
        epic.status = "New";
        epicContainer.put(epic.id, epic);

        return epic.id;
    }

    public void createSubtask(Subtask subtask, int epicId){
        subtask.id = getId();
        subtask.epicId = epicId;
        subtask.status = "New";
        Epic epic = epicContainer.get(epicId);
        epic.subtaskId.add(subtask.id);

        subtaskContainer.put(subtask.id, subtask);
    }


    public void updateEpic(Epic epic){
        epicContainer.put(epic.id, epic);

    }

    public void deleteFromId(){}

    private void printTask(Task task){
        System.out.println(task.taskName + ":");
        System.out.println(task.description);
        System.out.println(task.status);
        System.out.println();

    }
    private void printEpic(Epic epic){
        System.out.println(epic.taskName + ":");
        System.out.println(epic.description);
        System.out.println(epic.status);
        System.out.println();

    }
    private void printSubtask(Subtask subtask){
        System.out.println(subtask.taskName + ":");
        System.out.println(subtask.description);
        System.out.println(subtask.status);
        System.out.println();

    }

    
}
