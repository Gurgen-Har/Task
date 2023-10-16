
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    public Node<Task> head;
    public Node<Task> tail;
    private int size = 0;

    private HashMap<Integer, Node<Task>> counter = new HashMap<>();
    private  List<Task> history = new ArrayList<>(10);
    @Override
    public void add(Task task) {
        if (counter.containsKey(task.id)){
            removeNode(counter.get(task.id));
        } else if (counter) {

        }


    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
    //добавление в конец
    public void linkLast(Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        size++;
    }
    //из св.списка  список
    public void getTasks() {
        Node<Task> tmp;
        tmp = head;
        for (int i = 0; i < size; i++){
            history.add(tmp.data);
            tmp = head.next;
        }
    }
    //удаление узла
    public void removeNode(Node<Task> node) {
        if (node.prev.data != null && node.next.data == null) {
            node.prev.next = null;
            tail = node.prev;
            node = null;


        } else if (node.prev.data == null && node.next.data != null ) {
            node.next.prev = null;
            head = node.next;
            node = null;

        } else if (node.prev.data != null && node.next.data != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node = null;

        }

    }




}
class Node <T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    public Node(Node<T> prev, T data, Node<T> next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
