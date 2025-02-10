import java.util.ArrayList;

public class LinkedList2 {
  public Node head;
  public Node tail;
  private int size;

  public LinkedList2() {
    head = null;
    tail = null;
    size = 0;
  }

  public void addInTail(Node _item) {
    if (head == null) {
      head = tail = _item;
    } else {
      tail.next = _item;
      _item.prev = tail;
      tail = _item;
    }
    size++;
  }

  public Node find(int _value) {
    for (Node current = head; current != null; current = current.next) {
      if (current.value == _value) {
        return current;
      }
    }
    return null;
  }

  public ArrayList<Node> findAll(int _value) {
    ArrayList<Node> nodes = new ArrayList<>();
    for (Node current = head; current != null; current = current.next) {
      if (current.value == _value) {
        nodes.add(current);
      }
    }
    return nodes;
  }

  public boolean remove(int _value) {
    for (Node current = head; current != null; current = current.next) {
      if (current.value == _value) {
        if (current.prev != null) {
          current.prev.next = current.next;
        } else {
          head = current.next;
        }

        if (current.next != null) {
          current.next.prev = current.prev;
        } else {
          tail = current.prev;
        }

        size--;
        return true;
      }
    }
    return false;
  }

  public void removeAll(int _value) {
    Node current = head;
    while (current != null) {
      Node next = current.next;
      if (current.value == _value) {
        if (current.prev != null) {
          current.prev.next = current.next;
        } else {
          head = current.next;
        }

        if (current.next != null) {
          current.next.prev = current.prev;
        } else {
          tail = current.prev;
        }

        size--;
      }
      current = next;
    }
  }

  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  public int count() {
    return size;
  }

  public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
    if (_nodeAfter == null) {
      _nodeToInsert.next = head;
      if (head != null) {
        head.prev = _nodeToInsert;
      }
      head = _nodeToInsert;
      if (tail == null) {
        tail = _nodeToInsert;
      }
    } else {
      _nodeToInsert.next = _nodeAfter.next;
      _nodeToInsert.prev = _nodeAfter;
      if (_nodeAfter.next != null) {
        _nodeAfter.next.prev = _nodeToInsert;
      }
      _nodeAfter.next = _nodeToInsert;
      if (_nodeAfter == tail) {
        tail = _nodeToInsert;
      }
    }
    size++;
  }
}

class Node {
  public int value;
  public Node next;
  public Node prev;

  public Node(int _value) {
    value = _value;
    next = null;
    prev = null;
  }
}


