public static LinkedList sumLists(LinkedList list1, LinkedList list2) {
  if (list1.count() != list2.count()) {
    return null;
  }

  LinkedList result = new LinkedList();
  Node node1 = list1.head;
  Node node2 = list2.head;

  while (node1 != null) {
    result.addInTail(new Node(node1.value + node2.value));
    node1 = node1.next;
    node2 = node2.next;
  }

  return result;
}


