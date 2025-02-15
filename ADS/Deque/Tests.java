import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DequeTest {

  @Test
  public void testAddFront() {
    Deque<Integer> deque = new Deque<>();
    deque.addFront(10);
    assertEquals(1, deque.size());
    deque.addFront(20);
    assertEquals(2, deque.size());
    assertEquals(20, deque.removeFront());
  }

  @Test
  public void testAddTail() {
    Deque<Integer> deque = new Deque<>();
    deque.addTail(30);
    assertEquals(1, deque.size());
    deque.addTail(40);
    assertEquals(2, deque.size());
    assertEquals(30, deque.removeFront());
  }

  @Test
  public void testRemoveFront() {
    Deque<Integer> deque = new Deque<>();
    deque.addFront(50);
    deque.addFront(60);
    assertEquals(60, deque.removeFront());
    assertEquals(50, deque.removeFront());
    assertNull(deque.removeFront());
  }

  @Test
  public void testRemoveTail() {
    Deque<Integer> deque = new Deque<>();
    deque.addTail(70);
    deque.addTail(80);
    assertEquals(80, deque.removeTail());
    assertEquals(70, deque.removeTail());
    assertNull(deque.removeTail());
  }

  @Test
  public void testSize() {
    Deque<Integer> deque = new Deque<>();
    assertEquals(0, deque.size());
    deque.addFront(90);
    assertEquals(1, deque.size());
    deque.addTail(100);
    assertEquals(2, deque.size());
    deque.removeFront();
    assertEquals(1, deque.size());
    deque.removeTail();
    assertEquals(0, deque.size());
  }
}



