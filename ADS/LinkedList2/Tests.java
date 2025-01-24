import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LinkedList2Test {
  LinkedList2 list;

  @BeforeEach
  void setUp() {
    list = new LinkedList2();
  }

  @Test
  void testAddInTail() {
    assertNull(list.head);
    assertNull(list.tail);

    Node n1 = new Node(1);
    list.addInTail(n1);
    assertEquals(n1, list.head);
    assertEquals(n1, list.tail);
    assertNull(n1.prev);
    assertNull(n1.next);

    Node n2 = new Node(2);
    Node n3 = new Node(3);
    list.addInTail(n2);
    list.addInTail(n3);

    assertEquals(n1, list.head);
    assertEquals(n3, list.tail);
    assertEquals(n2, n1.next);
    assertEquals(n3, n2.next);
    assertEquals(n2, n3.prev);
    assertEquals(n1, n2.prev);
  }

  @Test
  void testFind() {
    assertNull(list.find(1));

    Node n1 = new Node(1);
    list.addInTail(n1);
    assertEquals(n1, list.find(1));
    assertNull(list.find(2));

    Node n2 = new Node(2);
    Node n3 = new Node(3);
    list.addInTail(n2);
    list.addInTail(n3);
    assertEquals(n2, list.find(2));
    assertEquals(n3, list.find(3));
  }

  @Test
  void testFindAll() {
    assertTrue(list.findAll(1).isEmpty());

    Node n1 = new Node(1);
    list.addInTail(n1);
    var result = list.findAll(1);
    assertEquals(1, result.size());
    assertTrue(result.contains(n1));

    Node n2 = new Node(2);
    Node n3 = new Node(1);
    list.addInTail(n2);
    list.addInTail(n3);
    result = list.findAll(1);
    assertEquals(2, result.size());
    assertTrue(result.contains(n1));
    assertTrue(result.contains(n3));
  }

  @Test
  void testRemove() {
    assertFalse(list.remove(1));

    Node n1 = new Node(1);
    list.addInTail(n1);
    assertTrue(list.remove(1));
    assertNull(list.head);
    assertNull(list.tail);

    Node n2 = new Node(2);
    Node n3 = new Node(3);
    list.addInTail(n2);
    list.addInTail(n3);
    assertTrue(list.remove(2));
    assertEquals(n3, list.head);
    assertNull(n3.prev);
    assertFalse(list.remove(4));
  }

  @Test
  void testRemoveAll() {
    list.removeAll(1);
    assertNull(list.head);
    assertNull(list.tail);

    Node n1 = new Node(1);
    list.addInTail(n1);
    list.removeAll(1);
    assertNull(list.head);
    assertNull(list.tail);

    Node n2 = new Node(2);
    Node n3 = new Node(1);
    Node n4 = new Node(1);
    list.addInTail(n2);
    list.addInTail(n3);
    list.addInTail(n4);
    list.removeAll(1);
    assertEquals(n2, list.head);
    assertEquals(n2, list.tail);
  }

  @Test
  void testClear() {
    list.addInTail(new Node(1));
    list.addInTail(new Node(2));
    list.clear();
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void testCount() {
    assertEquals(0, list.count());

    list.addInTail(new Node(1));
    assertEquals(1, list.count());

    list.addInTail(new Node(2));
    list.addInTail(new Node(3));
    assertEquals(3, list.count());
  }

  @Test
  void testInsertAfter() {
    Node n1 = new Node(1);
    list.insertAfter(null, n1);
    assertEquals(n1, list.head);
    assertEquals(n1, list.tail);

    Node n2 = new Node(2);
    list.insertAfter(n1, n2);
    assertEquals(n2, list.tail);
    assertEquals(n2, n1.next);
    assertEquals(n1, n2.prev);

    Node n3 = new Node(3);
    list.insertAfter(n1, n3);
    assertEquals(n3, n1.next);
    assertEquals(n2, n3.next);
    assertEquals(n3, n2.prev);
  }

  @Test
  void testReverse() {
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
  void testHasCycle() {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    list.addInTail(n1);
    list.addInTail(n2);
    list.addInTail(n3);

    assertFalse(list.hasCycle());

    n3.next = n1;
    assertTrue(list.hasCycle());
  }

  @Test
  void testSort() {
    list.addInTail(new Node(3));
    list.addInTail(new Node(1));
    list.addInTail(new Node(2));

    list.sort();

    assertEquals(1, list.head.value);
    assertEquals(2, list.head.next.value);
    assertEquals(3, list.tail.value);
  }

  @Test
  void testMergeSortedLists() {
    LinkedList2 list1 = new LinkedList2();
    LinkedList2 list2 = new LinkedList2();

    list1.addInTail(new Node(1));
    list1.addInTail(new Node(3));
    list2.addInTail(new Node(2));
    list2.addInTail(new Node(4));

    LinkedList2 mergedList = LinkedList2.mergeSortedLists(list1, list2);

    assertEquals(1, mergedList.head.value);
    assertEquals(2, mergedList.head.next.value);
    assertEquals(4, mergedList.tail.value);
  }

  @Test
  void testAddDummyNodes() {
    list.addInTail(new Node(1));
    list.addInTail(new Node(2));

    list.addDummyNodes();

    assertEquals(0, list.head.value);
    assertEquals(0, list.tail.value);
    assertEquals(1, list.head.next.value);
    assertEquals(2, list.tail.prev.value);
  }
}


