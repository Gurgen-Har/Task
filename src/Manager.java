import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        int input = 0;
        printMenu();
        while (command != 0){
            command = scanner.nextInt();
            if (command == 1){
                printTaskMenu();
                input = scanner.nextInt();
                if (input == 1){
                    addTask();

                } else if (input == 2) {
                    System.out.println("Количество подзадач:");
                    input = scanner.nextInt();

                    addEpicTask(input);
                }


            } else if (command == 2){

            } else if (command == 3) {

            } else if (command == 4) {

            }
        }


    }

    public static void printMenu(){
        System.out.println("0.Выход");
        System.out.println("1.Добавить задачу");
        System.out.println("2.Распечатать задачи");
        System.out.println("3.Удалить задачу");
        System.out.println("4.Удалить все задачи");
        System.out.println();
    }


    public static void printTaskMenu(){
        System.out.println("1.Добавить Задачу");
        System.out.println("2.Добавить Эпик");
    }

    static int id = 1;
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer,HashMap<Integer, Subtask>> epic = new HashMap<>();
    static HashMap<Integer,Task> task = new HashMap<>();
    public void getAllTasks(){}

    public void deleteAllTasks(){}

    public void getTasksFromId(){}

    public static void addTask(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Название задачи:");
        String name = sc.toString();
        System.out.println("Описание задачи");
        ArrayList<String> description = new ArrayList<>();
        while (sc.nextInt() != 0) {
            description.add(sc.toString());
        }
        Task task1 = new Task(name, description);
        task.put(id,task1);
    }

    public static void addEpicTask(int count){
        System.out.println("Название Эпика:");
        System.out.println();

    }

    public void refresh(){}

    public void deleteFromId(){}

    public  void setId(){}
}