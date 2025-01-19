import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class LinkedListTest {
  private LinkedList list;

  @Before
  public void setUp() {
    list = new LinkedList();
  }

  @Test
  public void testAddInTailToEmptyList() {
    Node node = new Node(1);
    list.addInTail(node);
    assertEquals(node, list.head);
    assertEquals(node, list.tail);
    assertNull(list.head.next);
  }

  @Test
  public void testAddInTailToNonEmptyList() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    list.addInTail(node1);
    list.addInTail(node2);
    assertEquals(node1, list.head);
    assertEquals(node2, list.tail);
    assertEquals(node2, node1.next);
    assertNull(node2.next);
  }

  @Test
  public void testFindInEmptyList() {
    assertNull(list.find(1));
  }

  @Test
  public void testFindExistingElement() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    list.addInTail(node1);
    list.addInTail(node2);
    assertEquals(node1, list.find(1));
    assertEquals(node2, list.find(2));
  }

  @Test
  public void testFindNonExistingElement() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    list.addInTail(node1);
    list.addInTail(node2);
    assertNull(list.find(3));
  }

  @Test
  public void testFindAllSingleElementMatch() {
    Node node1 = new Node(1);
    list.addInTail(node1);
    ArrayList<Node> result = list.findAll(1);
    assertEquals(1, result.size());
    assertEquals(node1, result.get(0));
  }

  @Test
  public void testFindAllSingleElementNoMatch() {
    Node node1 = new Node(1);
    list.addInTail(node1);
    ArrayList<Node> result = list.findAll(2);
    assertEquals(0, result.size());
  }

  @Test
  public void testFindAllMultipleMatches() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(1);
    list.addInTail(node1);
    list.addInTail(node2);
    list.addInTail(node3);
    ArrayList<Node> result = list.findAll(1);
    assertEquals(2, result.size());
    assertEquals(node1, result.get(0));
    assertEquals(node3, result.get(1));
  }

  @Test
  public void testRemoveMiddleElement() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    list.addInTail(node1);
    list.addInTail(node2);
    list.addInTail(node3);
    assertTrue(list.remove(2));
    assertEquals(node1, list.head);
    assertEquals(node3, list.head.next);
    assertEquals(node3, list.tail);
  }

  @Test
  public void testRemoveNonExistingElement() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    list.addInTail(node1);
    list.addInTail(node2);
    assertFalse(list.remove(3));
    assertEquals(node1, list.head);
    assertEquals(node2, list.tail);
  }

  @Test
  public void testRemoveAllOnlyOneElementMatches() {
    Node node1 = new Node(1);
    list.addInTail(node1);
    list.removeAll(1);
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  public void testRemoveAllAllElementsMatch() {
    Node node1 = new Node(1);
    Node node2 = new Node(1);
    list.addInTail(node1);
    list.addInTail(node2);
    list.removeAll(1);
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  public void testInsertAfterSingleElement() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    list.addInTail(node1);
    list.insertAfter(node1, node2);
    assertEquals(node1, list.head);
    assertEquals(node2, list.tail);
    assertEquals(node2, node1.next);
  }

  @Test
  public void testInsertAfterTail() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    list.addInTail(node1);
    list.addInTail(node2);
    list.insertAfter(node2, node3);
    assertEquals(node1, list.head);
    assertEquals(node3, list.tail);
    assertEquals(node3, node2.next);
  }

  @Test
  public void testInsertAfterMiddle() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    list.addInTail(node1);
    list.addInTail(node3);
    list.insertAfter(node1, node2);
    assertEquals(node1, list.head);
    assertEquals(node3, list.tail);
    assertEquals(node2, node1.next);
    assertEquals(node3, node2.next);
  }

  @Test
  public void testSumListsEmptyLists() {
    LinkedList list1 = new LinkedList();
    LinkedList list2 = new LinkedList();
    LinkedList result = LinkedList.sumLists(list1, list2);
    assertNull(result.head);
    assertNull(result.tail);
  }

  @Test
  public void testSumListsOneEmptyList() {
    LinkedList list1 = new LinkedList();
    LinkedList list2 = new LinkedList();
    list2.addInTail(new Node(1));
    LinkedList result = LinkedList.sumLists(list1, list2);
    assertNull(result);
  }

  @Test
  public void testSumListsMatchingLength() {
    LinkedList list1 = new LinkedList();
    LinkedList list2 = new LinkedList();
    list1.addInTail(new Node(1));
    list1.addInTail(new Node(2));
    list2.addInTail(new Node(3));
    list2.addInTail(new Node(4));
    LinkedList result = LinkedList.sumLists(list1, list2);
    assertEquals(4, result.head.value);
    assertEquals(6, result.head.next.value);
    assertNull(result.head.next.next);
  }

  @Test
  public void testSumListsNonMatchingLength() {
    LinkedList list1 = new LinkedList();
    LinkedList list2 = new LinkedList();
    list1.addInTail(new Node(1));
    list2.addInTail(new Node(2));
    list2.addInTail(new Node(3));
    LinkedList result = LinkedList.sumLists(list1, list2);
    assertNull(result);
  }
}


