public class LinkedList {
  public Node head;
  public Node tail;

  public LinkedList() {
    this.head = null;
    this.tail = null;
  }

  public void addInTail(Node item) {
    if (this.head == null)
      this.head = item;
    else
      this.tail.next = item;
    this.tail = item;
  }

  public Node find(int value) {
    Node node = this.head;
    while (node != null) {
      if (node.value == value)
        return node;
      node = node.next;
    }
    return null;
  }

  public ArrayList<Node> findAll(int _value) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    Node current = this.head;
    while (current != null) {
      if (current.value == _value) {
        nodes.add(current);
      }
      current = current.next;
    }
    return nodes;
  }

  public boolean remove(int _value) {
    if (this.head == null)
      return false;

    if (this.head.value == _value) {
      this.head = this.head.next;
      if (this.head == null) 
        this.tail = null;
      return true;
    }

    Node current = this.head;
    while (current.next != null) {
      if (current.next.value == _value) {
        current.next = current.next.next;
        if (current.next == null)
          this.tail = current;
        return true;
      }
      current = current.next;
    }
    return false;
  }

  public void removeAll(int _value) {
    while (this.head != null && this.head.value == _value) {
      this.head = this.head.next;
    }
    if (this.head == null) {
      this.tail = null;
      return;
    }

    Node current = this.head;
    while (current.next != null) {
      if (current.next.value == _value) {
        current.next = current.next.next;
      } else {
        current = current.next;
      }
    }
    this.tail = current;
  }

  public void clear() {
    this.head = null;
    this.tail = null;
  }

  public int count() {
    int count = 0;
    Node current = this.head;
    while (current != null) {
      count++;
      current = current.next;
    }
    return count;
  }

  public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
    if (_nodeAfter == null) {
      _nodeToInsert.next = this.head;
      this.head = _nodeToInsert;
      if (this.tail == null)
        this.tail = _nodeToInsert;
      return;
    }

    _nodeToInsert.next = _nodeAfter.next;
    _nodeAfter.next = _nodeToInsert;

    if (_nodeAfter == this.tail) {
      this.tail = _nodeToInsert;
    }
  }

  public static LinkedList sumLists(LinkedList list1, LinkedList list2) {
    if (list1.count() != list2.count()) {
      return null;
    }

    LinkedList result = new LinkedList();
    Node current1 = list1.head;
    Node current2 = list2.head;

    while (current1 != null) {
      result.addInTail(new Node(current1.value + current2.value));
      current1 = current1.next;
      current2 = current2.next;
    }

    return result;
  }

  public void testRemove() {
    LinkedList list = new LinkedList();
    assert !list.remove(1);
    assert list.head == null;
    assert list.tail == null;

    list.addInTail(new Node(1));
    assert list.remove(1);
    assert list.head == null;
    assert list.tail == null;

    list.addInTail(new Node(1));
    list.addInTail(new Node(2));
    list.addInTail(new Node(3));
    assert list.remove(2);
    assert list.count() == 2;
  }

  public void testRemoveAll() {
    LinkedList list = new LinkedList();
    list.removeAll(1);
    assert list.head == null;
    assert list.tail == null;

    list.addInTail(new Node(1));
    list.addInTail(new Node(1));
    list.addInTail(new Node(2));
    list.removeAll(1);
    assert list.count() == 1;
    assert list.head.value == 2;
    assert list.tail.value == 2;
  }

  public void testInsertAfter() {
    LinkedList list = new LinkedList();
    Node node1 = new Node(1);
    list.insertAfter(null, node1);
    assert list.head == node1;
    assert list.tail == node1;

    Node node2 = new Node(2);
    list.insertAfter(node1, node2);
    assert list.head.next == node2;
    assert list.tail == node2;
  }

  public void testSumLists() {
    LinkedList list1 = new LinkedList();
    LinkedList list2 = new LinkedList();

    list1.addInTail(new Node(1));
    list1.addInTail(new Node(2));
    list2.addInTail(new Node(3));
    list2.addInTail(new Node(4));

    LinkedList result = sumLists(list1, list2);
    assert result.count() == 2;
    assert result.head.value == 4;
    assert result.tail.value == 6;
  }
}

class Node {
  public int value;
  public Node next;

  public Node(int _value) {
    this.value = _value;
    this.next = null;
  }
}


