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
  for (Node slow = head, fast = head; fast != null && fast.next != null; slow = slow.next, fast = fast.next.next) {
    if (slow == fast) {
      return true;
    }
  }
  return false;
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

public void addDummyNodes() {
  Node dummyHead = new Node(0);
  Node dummyTail = new Node(0);

  dummyHead.next = head;
  if (head != null) {
    head.prev = dummyHead;
  }
  dummyTail.prev = tail;
  if (tail != null) {
    tail.next = dummyTail;
  }

  head = dummyHead;
  tail = dummyTail;
}


