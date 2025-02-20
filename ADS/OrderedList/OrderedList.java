import java.util.*;

class Node<T> {
  public T value;
  public Node<T> next, prev;
  public Node(T _value) {
    value = _value;
    next = null;
    prev = null;
  }
}

public class OrderedList<T> {
  public Node<T> head, tail;
  private boolean _ascending;

  public OrderedList(boolean asc) {
    head = null;
    tail = null;
    _ascending = asc;
  }

  public int compare(T v1, T v2) {
    if (v1 instanceof Number && v2 instanceof Number) {
      double d1 = ((Number)v1).doubleValue();
      double d2 = ((Number)v2).doubleValue();
      return Double.compare(d1, d2);
    }
    if (v1 instanceof String && v2 instanceof String) {
      String s1 = ((String)v1).trim();
      String s2 = ((String)v2).trim();
      return s1.compareTo(s2);
    }
    if (v1 instanceof Comparable && v2 instanceof Comparable) {
      @SuppressWarnings("unchecked")
      Comparable<T> comp1 = (Comparable<T>) v1;
      return comp1.compareTo(v2);
    }
    throw new RuntimeException("Values are not comparable");
  }

  public void add(T value) {
    Node<T> newNode = new Node<>(value);
    if (head == null) {
      head = tail = newNode;
      return;
    }
    Node<T> current = head;
    while (current != null) {
      int cmp = compare(value, current.value);
      if ((_ascending && cmp <= 0) || (!_ascending && cmp >= 0)) {
        newNode.next = current;
        newNode.prev = current.prev;
        if (current.prev != null)
          current.prev.next = newNode;
        else
          head = newNode;
        current.prev = newNode;
        return;
      }
      current = current.next;
    }
    tail.next = newNode;
    newNode.prev = tail;
    tail = newNode;
  }

  public Node<T> find(T val) {
    Node<T> current = head;
    while (current != null) {
      int cmp = compare(val, current.value);
      if (cmp == 0)
        return current;
      if ((_ascending && cmp < 0) || (!_ascending && cmp > 0))
        return null;
      current = current.next;
    }
    return null;
  }

  public void delete(T val) {
    Node<T> current = head;
    while (current != null) {
      if (compare(val, current.value) == 0) {
        if (current.prev != null)
          current.prev.next = current.next;
        else
          head = current.next;
        if (current.next != null)
          current.next.prev = current.prev;
        else
          tail = current.prev;
        return;
      }
      int cmp = compare(val, current.value);
      if ((_ascending && cmp < 0) || (!_ascending && cmp > 0))
        return;
      current = current.next;
    }
  }

  public void clear(boolean asc) {
    head = null;
    tail = null;
    _ascending = asc;
  }

  public int count() {
    int cnt = 0;
    Node<T> current = head;
    while (current != null) {
      cnt++;
      current = current.next;
    }
    return cnt;
  }

  public ArrayList<Node<T>> getAll() {
    ArrayList<Node<T>> r = new ArrayList<>();
    Node<T> node = head;
    while (node != null) {
      r.add(node);
      node = node.next;
    }
    return r;
  }
}


