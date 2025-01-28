@Test
void testReverseEmptyList() {
  list.reverse();
  assertNull(list.head);
  assertNull(list.tail);
}

@Test
void testReverseSingleElement() {
  list.addInTail(new Node(1));
  list.reverse();
  assertEquals(1, list.head.value);
  assertEquals(1, list.tail.value);
  assertNull(list.head.next);
  assertNull(list.tail.prev);
}

@Test
void testReverseMultipleElementsOdd() {
  list.addInTail(new Node(1));
  list.addInTail(new Node(2));
  list.addInTail(new Node(3));
  list.reverse();
  assertEquals(3, list.head.value);
  assertEquals(1, list.tail.value);
  assertEquals(2, list.head.next.value);
  assertEquals(2, list.tail.prev.value);
}

@Test
void testReverseMultipleElementsEven() {
  list.addInTail(new Node(1));
  list.addInTail(new Node(2));
  list.addInTail(new Node(3));
  list.addInTail(new Node(4));
  list.reverse();
  assertEquals(4, list.head.value);
  assertEquals(1, list.tail.value);
  assertEquals(3, list.head.next.value);
  assertEquals(3, list.tail.prev.value);
}

@Test
void testHasCycleEmptyList() {
  assertFalse(list.hasCycle());
}

@Test
void testHasCycleSingleElement() {
  list.addInTail(new Node(1));
  assertFalse(list.hasCycle());
}

@Test
void testHasCycleNoCycle() {
  list.addInTail(new Node(1));
  list.addInTail(new Node(2));
  list.addInTail(new Node(3));
  assertFalse(list.hasCycle());
}

@Test
void testHasCycleWithCycle() {
  Node n1 = new Node(1);
  Node n2 = new Node(2);
  Node n3 = new Node(3);
  list.addInTail(n1);
  list.addInTail(n2);
  list.addInTail(n3);
  n3.next = n1; // Создаем цикл
  assertTrue(list.hasCycle());
}

@Test
void testSortEmptyList() {
  list.sort();
  assertNull(list.head);
  assertNull(list.tail);
}

@Test
void testSortSingleElement() {
  list.addInTail(new Node(1));
  list.sort();
  assertEquals(1, list.head.value);
  assertEquals(1, list.tail.value);
  assertNull(list.head.next);
  assertNull(list.tail.prev);
}

@Test
void testSortAlreadySorted() {
  list.addInTail(new Node(1));
  list.addInTail(new Node(2));
  list.addInTail(new Node(3));
  list.sort();
  assertEquals(1, list.head.value);
  assertEquals(2, list.head.next.value);
  assertEquals(3, list.tail.value);
}

@Test
void testSortUnsortedList() {
  list.addInTail(new Node(3));
  list.addInTail(new Node(1));
  list.addInTail(new Node(2));
  list.sort();
  assertEquals(1, list.head.value);
  assertEquals(2, list.head.next.value);
  assertEquals(3, list.tail.value);
}

@Test
void testSortWithDuplicates() {
  list.addInTail(new Node(3));
  list.addInTail(new Node(1));
  list.addInTail(new Node(3));
  list.addInTail(new Node(2));
  list.addInTail(new Node(1));
  list.sort();
  assertEquals(1, list.head.value);
  assertEquals(1, list.head.next.value);
  assertEquals(2, list.head.next.next.value);
  assertEquals(3, list.tail.prev.value);
  assertEquals(3, list.tail.value);
}

@Test
void testMergeSortedListsEmptyLists() {
  LinkedList2 list1 = new LinkedList2();
  LinkedList2 list2 = new LinkedList2();
  LinkedList2 mergedList = LinkedList2.mergeSortedLists(list1, list2);
  assertNull(mergedList.head);
  assertNull(mergedList.tail);
}

@Test
void testMergeSortedListsOneEmptyList() {
  LinkedList2 list1 = new LinkedList2();
  list1.addInTail(new Node(1));
  list1.addInTail(new Node(3));
  LinkedList2 list2 = new LinkedList2();
  LinkedList2 mergedList = LinkedList2.mergeSortedLists(list1, list2);
  assertEquals(1, mergedList.head.value);
  assertEquals(3, mergedList.tail.value);
}

@Test
void testMergeSortedListsOverlappingValues() {
  LinkedList2 list1 = new LinkedList2();
  list1.addInTail(new Node(1));
  list1.addInTail(new Node(3));
  list1.addInTail(new Node(5));
  LinkedList2 list2 = new LinkedList2();
  list2.addInTail(new Node(2));
  list2.addInTail(new Node(3));
  list2.addInTail(new Node(6));
  LinkedList2 mergedList = LinkedList2.mergeSortedLists(list1, list2);
  assertEquals(1, mergedList.head.value);
  assertEquals(3, mergedList.head.next.next.value);
  assertEquals(6, mergedList.tail.value);
}

@Test
void testMergeSortedListsWithDuplicates() {
  LinkedList2 list1 = new LinkedList2();
  list1.addInTail(new Node(1));
  list1.addInTail(new Node(3));
  list1.addInTail(new Node(3));
  LinkedList2 list2 = new LinkedList2();
  list2.addInTail(new Node(2));
  list2.addInTail(new Node(3));
  list2.addInTail(new Node(4));
  LinkedList2 mergedList = LinkedList2.mergeSortedLists(list1, list2);
  assertEquals(1, mergedList.head.value);
  assertEquals(3, mergedList.head.next.next.value);
  assertEquals(4, mergedList.tail.value);
}

@Test
void testDummyLinkedList2Empty() {
  DummyLinkedList2 list = new DummyLinkedList2();
  assertNull(list.getHead());
  assertNull(list.getTail());
}

@Test
void testDummyLinkedList2AddElements() {
  DummyLinkedList2 list = new DummyLinkedList2();
  list.addInTail(new Node(1));
  list.addInTail(new Node(2));
  assertEquals(1, list.getHead().value);
  assertEquals(2, list.getTail().value);
  assertNull(list.getHead().prev);
  assertNull(list.getTail().next);
}

@Test
void testDummyLinkedList2MultipleOperations() {
  DummyLinkedList2 list = new DummyLinkedList2();
  list.addInTail(new Node(1));
  list.addInTail(new Node(2));
  list.addInTail(new Node(3));
  assertEquals(1, list.getHead().value);
  assertEquals(3, list.getTail().value);
  assertEquals(2, list.getHead().next.value);
  assertEquals(2, list.getTail().prev.value);
}
