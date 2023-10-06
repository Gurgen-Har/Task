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
        task.status = "NEW";
        taskContainer.put(task.id, task);
    }

    public int createEpic(Epic epic){
        epic.id = getId();
        epic.status = "NEW";
        epicContainer.put(epic.id, epic);

        return epic.id;
    }

    public void createSubtask(Subtask subtask, int epicId){
        subtask.id = getId();
        subtask.epicId = epicId;
        subtask.status = "NEW";
        Epic epic = epicContainer.get(epicId);
        epic.subtaskId.add(subtask.id);

        subtaskContainer.put(subtask.id, subtask);
        updateEpic(epic);
    }


    public void updateEpic(Epic epic){
        int newCount = 0;
        int doneCount = 0;

        if (!epic.subtaskId.isEmpty()) {
            for(int i : epic.subtaskId) {
                if(subtaskContainer.get(i).status.equals("NEW")){
                    newCount++;
                } else if (subtaskContainer.get(i).status.equals("DONE")) {
                    doneCount++;
                }
            }
        }
        if(newCount == epic.subtaskId.size()){
            epic.status = "NEW";
        } else if (doneCount == epic.subtaskId.size()) {
            epic.status = "DONE";
        } else {
            epic.status = "IN PROGRESS";
        }
        epicContainer.put(epic.id, epic);


    }
    public void updateTask(int id, String status){
        Task task =  taskContainer.get(id);
        task.status = status;
        taskContainer.put(task.id, task);
    }
    public void updateSubtask(int id, String status){
        Subtask subtask =  subtaskContainer.get(id);
        subtask.status = status;
        taskContainer.put(subtask.id, subtask);
    }

    public void deleteFromId(){}

    private void printTask(Task task){
        System.out.println(task.id + ". " + task.taskName + ":");
        System.out.println(task.description);
        System.out.println(task.status);
        System.out.println();

    }
    private void printEpic(Epic epic){
        System.out.println(epic.id + ". " + epic.taskName + ":");
        System.out.println(epic.description);
        System.out.println(epic.status);
        System.out.println();

        if (!epic.subtaskId.isEmpty()) {

            for(int i : epic.subtaskId){
                printSubtask(subtaskContainer.get(i));
            }

        }

    }
    private void printSubtask(Subtask subtask){

        System.out.println(subtask.id + ". "+subtask.taskName + ":");
        System.out.println(subtask.description);
        System.out.println(subtask.status);
        System.out.println();

    }

    
}
