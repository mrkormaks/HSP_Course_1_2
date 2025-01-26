public class LinkedList2 {
  public Node head;
  public Node tail;

  public LinkedList2() {
    head = null;
    tail = null;
  }

  public void addInTail(Node _item) {
    if (head == null) {
      this.head = _item;
      this.head.next = null;
      this.head.prev = null;
    } else {
      this.tail.next = _item;
      _item.prev = tail;
    }
    this.tail = _item;
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
        return true;
      }
    }
    return false;
  }

  public void removeAll(int _value) {
    for (Node current = head; current != null; current = current.next) {
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
      }
    }
  }

  public void clear() {
    head = null;
    tail = null;
  }

  public int count() {
    int count = 0;
    for (Node current = head; current != null; current = current.next) {
      count++;
    }
    return count;
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
  }
}

class Node
{
  public int value;
  public Node next;
  public Node prev;
  
  public Node(int _value) 
  { 
    value = _value; 
    next = null;
    prev = null;
  }
}


