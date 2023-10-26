
enum types{
    TASK,
    EPIC,
    SUBTASK
}
public class FileBackedTaskManager extends InMemoryTaskManager {

    String fileName = "C:\\Users\\Gurgen\\IdeaProjects\\Task\\src\\Data.csv";

    public FileBackedTaskManager(){

    }
    @Override
    public void createTasks(Task task){
        super.createTasks(task);
        save();
    }

    public void save(){

    }

    public String toString(Task task){
        StringBuilder str = new StringBuilder();
        str.append("");
        for (int i = 0; i < 6; i++){}

        return null;
    }


}
