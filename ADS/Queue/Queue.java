import java.util.LinkedList;

public class Queue<T> {
  private LinkedList<T> list;

  public Queue() {
    this.list = new LinkedList<>();
  }

  public void enqueue(T item) {
    list.addLast(item);
  }

  public T dequeue() {
    return list.isEmpty() ? null : list.removeFirst();
  }

  public int size() {
    return list.size();
  }
}


