import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class OrderedListTest {
  @Test
  public void testAddAscending() {
    OrderedList<Integer> list = new OrderedList<>(true);
    list.add(5);
    list.add(3);
    list.add(7);
    list.add(1);
    ArrayList<Node<Integer>> nodes = list.getAll();
    assertEquals(4, nodes.size());
    assertEquals(Integer.valueOf(1), nodes.get(0).value);
    assertEquals(Integer.valueOf(3), nodes.get(1).value);
    assertEquals(Integer.valueOf(5), nodes.get(2).value);
    assertEquals(Integer.valueOf(7), nodes.get(3).value);
  }

  @Test
  public void testAddDescending() {
    OrderedList<Integer> list = new OrderedList<>(false);
    list.add(5);
    list.add(3);
    list.add(7);
    list.add(1);
    ArrayList<Node<Integer>> nodes = list.getAll();
    assertEquals(4, nodes.size());
    assertEquals(Integer.valueOf(7), nodes.get(0).value);
    assertEquals(Integer.valueOf(5), nodes.get(1).value);
    assertEquals(Integer.valueOf(3), nodes.get(2).value);
    assertEquals(Integer.valueOf(1), nodes.get(3).value);
  }

  @Test
  public void testFind() {
    OrderedList<Integer> list = new OrderedList<>(true);
    list.add(10);
    list.add(20);
    list.add(30);
    list.add(40);
    Node<Integer> node = list.find(30);
    assertNotNull(node);
    assertEquals(Integer.valueOf(30), node.value);
    node = list.find(25);
    assertNull(node);
  }

  @Test
  public void testDelete() {
    OrderedList<Integer> list = new OrderedList<>(true);
    list.add(10);
    list.add(20);
    list.add(30);
    list.add(20);
    list.delete(20);
    ArrayList<Node<Integer>> nodes = list.getAll();
    assertEquals(3, nodes.size());
    assertEquals(Integer.valueOf(10), nodes.get(0).value);
    assertEquals(Integer.valueOf(20), nodes.get(1).value);
    assertEquals(Integer.valueOf(30), nodes.get(2).value);
    list.delete(50);
    nodes = list.getAll();
    assertEquals(3, nodes.size());
  }

  @Test
  public void testCountAndClear() {
    OrderedList<Integer> list = new OrderedList<>(true);
    assertEquals(0, list.count());
    list.add(5);
    list.add(15);
    list.add(25);
    assertEquals(3, list.count());
    list.clear(false);
    assertEquals(0, list.count());
    list.add(5);
    list.add(15);
    list.add(25);
    ArrayList<Node<Integer>> nodes = list.getAll();
    assertEquals(Integer.valueOf(25), nodes.get(0).value);
    assertEquals(Integer.valueOf(15), nodes.get(1).value);
    assertEquals(Integer.valueOf(5), nodes.get(2).value);
  }
}


