import java.util.*;

public class Stack<T> {
  private ArrayList<T> storage;

  public Stack() {
    storage = new ArrayList<>();
  }

  public int size() {
    return storage.size();
  }

  public T pop() {
    if (storage.isEmpty()) {
      return null;
    }
    return storage.remove(storage.size() - 1);
  }

  public void push(T val) {
    storage.add(val);
  }

  public T peek() {
    if (storage.isEmpty()) {
      return null;
    }
    return storage.get(storage.size() - 1);
  }
}



