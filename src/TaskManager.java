import java.util.ArrayList;

interface TaskManager {
    int getId();
    void deleteAllTasks();
    void deleteFromId();
    void getTask(int id);
    void getSubtask(int id);
    void getEpic(int id);

    void createTasks(Task task);
    int createEpic(Epic epic);
    void createSubtask(Subtask subtask, int epicId);
    void updateEpic(Epic epic);
    void updateTask(int id, Status status);
    void updateSubtask(int id, Status status);

    void printTask(Task task);
    void printEpic(Epic epic);
    void printSubtask(Subtask subtask);
}
