public void reverse() {
  for (Node current = head, temp = null; current != null; current = current.prev) {
    temp = current.prev;
    current.prev = current.next;
    current.next = temp;
  }
  if (head != null && head.prev != null) {
    head = head.prev;
  }
}

public boolean hasCycle() {
  if (head == null) {
    return false;
  }

  Node current = head;
  int maxSteps = count() * 2;

  for (int steps = 0; steps < maxSteps; steps++) {
    if (current == null) {
      return false;
    }
    current = current.next;
  }

  return true;
}

public void sort() {
  if (head == null || head.next == null) {
    return;
  }

  for (Node current = head; current != null; current = current.next) {
    for (Node index = current.next; index != null; index = index.next) {
      if (current.value > index.value) {
        int temp = current.value;
        current.value = index.value;
        index.value = temp;
      }
    }
  }
}

public static LinkedList2 mergeSortedLists(LinkedList2 list1, LinkedList2 list2) {
  if (list1 == null || list2 == null) {
    throw new IllegalArgumentException("Lists cannot be null");
  }

  list1.sort();
  list2.sort();

  LinkedList2 result = new LinkedList2();

  for (Node current1 = list1.head, current2 = list2.head; current1 != null || current2 != null;) {
    if (current1 != null && (current2 == null || current1.value <= current2.value)) {
      result.addInTail(new Node(current1.value));
      current1 = current1.next;
    } else if (current2 != null) {
      result.addInTail(new Node(current2.value));
      current2 = current2.next;
    }
  }

  return result;
}

class Dummy extends Node {
  public Dummy() {
    super(0);
  }
}

class DummyLinkedList2 extends LinkedList2 {
  private Dummy dummy;

  public DummyLinkedList2() {
    dummy = new Dummy();
    dummy.next = dummy;
    dummy.prev = dummy;
    head = dummy;
    tail = dummy;
  }

  @Override
  public void addInTail(Node node) {
    node.next = dummy;
    node.prev = dummy.prev;
    dummy.prev.next = node;
    dummy.prev = node;
  }

  @Override
  public Node find(int _value) {
    for (Node current = dummy.next; current != dummy; current = current.next) {
      if (!(current instanceof Dummy) && current.value == _value) {
        return current;
      }
    }
    return null;
  }

  @Override
  public ArrayList<Node> findAll(int _value) {
    ArrayList<Node> nodes = new ArrayList<>();
    for (Node current = dummy.next; current != dummy; current = current.next) {
      if (!(current instanceof Dummy) && current.value == _value) {
        nodes.add(current);
      }
    }
    return nodes;
  }

  @Override
  public boolean remove(int _value) {
    for (Node current = dummy.next; current != dummy; current = current.next) {
      if (!(current instanceof Dummy) && current.value == _value) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
        return true;
      }
    }
    return false;
  }

  @Override
  public void removeAll(int _value) {
    for (Node current = dummy.next; current != dummy; current = current.next) {
      if (!(current instanceof Dummy) && current.value == _value) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
      }
    }
  }

  @Override
  public int count() {
    int count = 0;
    for (Node current = dummy.next; current != dummy; current = current.next) {
      if (!(current instanceof Dummy)) {
        count++;
      }
    }
    return count;
  }
}


