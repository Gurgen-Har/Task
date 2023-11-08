import java.util.HashMap;


public class InMemoryTaskManager implements TaskManager {
    int id = 1;
    HashMap<Integer, Task> taskContainer = new HashMap<>();
    HashMap<Integer, Subtask> subtaskContainer = new HashMap<>();
    HashMap<Integer, Epic> epicContainer = new HashMap<>();
    InMemoryHistoryManager history = new InMemoryHistoryManager();

   @Override
   public int getId(){
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

    public void getTask(int id){
       Task task = taskContainer.get(id);

       history.add(task);
       printTask(task);
    }
    public void getSubtask(int id){
       Subtask subtask = subtaskContainer.get(id);

       history.add(subtask);
       printSubtask(subtask);

    }
    public void getEpic(int id){
       Epic epic = epicContainer.get(id);

       history.add(epic);
       printEpic(epic);

    }

    public void createTasks(Task task){
       if(task.id == 0){
           task.id = getId();
       }
       // task.id = getId();
        if (task.status == null) {
            task.status = Status.NEW;
        }
        taskContainer.put(task.id, task);
    }

    public void createEpic(Epic epic){
        if(epic.id == 0){
            epic.id = getId();
        }
        if (epic.status == null) {
            epic.status = Status.NEW;
        }

        epicContainer.put(epic.id, epic);

    }

    public void createSubtask(Subtask subtask, int epicId){
        if(subtask.id == 0){
            subtask.id = getId();
        }
        subtask.epicId = epicId;
        if (subtask.status == null) {
            subtask.status = Status.NEW;
        }
        Epic epic = epicContainer.get(epicId);
        epic.subtaskId.add(subtask.getSubtaskId());

        subtaskContainer.put(subtask.id, subtask);
        updateEpic(epic);
    }


    public void updateEpic(Epic epic){
        int newCount = 0;
        int doneCount = 0;

        if (!epic.subtaskId.isEmpty()) {
            for(int i : epic.subtaskId) {
                if(subtaskContainer.get(i).status.name().equals("NEW")){
                    newCount++;
                } else if (subtaskContainer.get(i).status.name().equals("DONE")) {
                    doneCount++;
                }
            }
        }
        if(newCount == epic.subtaskId.size()){
            epic.status = Status.NEW;
        } else if (doneCount == epic.subtaskId.size()) {
            epic.status = Status.DONE;
        } else {
            epic.status = Status.IN_PROGRESS;
        }
        epicContainer.put(epic.id, epic);


    }
    public void updateTask(int id, Status status){
        Task task =  taskContainer.get(id);
        task.status = status;
        taskContainer.put(task.id, task);
    }
    public void updateSubtask(int id, Status status){
        Subtask subtask =  subtaskContainer.get(id);
        subtask.status = status;
        taskContainer.put(subtask.id, subtask);
    }

    public void deleteFromId(){}

    public void printTask(Task task){
        System.out.println(task.id + ". " + task.taskName + ":");
        System.out.println(task.description);
        System.out.println(task.status);
        System.out.println();

    }
    public void printEpic(Epic epic){
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
    public void printSubtask(Subtask subtask){

        System.out.println(subtask.id + ". "+subtask.taskName + ":");
        System.out.println(subtask.description);
        System.out.println(subtask.status);
        System.out.println();

    }
    static int getEpicId(Subtask subtask){
        return subtask.epicId;
    }

    
}
