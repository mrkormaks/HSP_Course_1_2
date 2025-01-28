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

  int length = 0;
  for (Node current = head; current != null; current = current.next) {
    length++;
  }

  Node current = head;
  for (int i = 0; i < length; i++) {
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

public class DummyLinkedList2 {
  private Node dummyHead;
  private Node dummyTail;

  public DummyLinkedList2() {
    dummyHead = new Node(0);
    dummyTail = new Node(0);
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
  }

  public void addInTail(Node node) {
    Node lastRealNode = dummyTail.prev;
    lastRealNode.next = node;
    node.prev = lastRealNode;
    node.next = dummyTail;
    dummyTail.prev = node;
  }

  public Node getHead() {
    return dummyHead.next == dummyTail ? null : dummyHead.next;
  }

  public Node getTail() {
    return dummyTail.prev == dummyHead ? null : dummyTail.prev;
  }
}


