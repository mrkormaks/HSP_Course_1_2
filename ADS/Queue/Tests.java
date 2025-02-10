import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

  @Test
  void testEnqueueAndSize() {
    Queue<Integer> queue = new Queue<>();
    assertEquals(0, queue.size(), "Очередь должна быть пустой при создании");

    queue.enqueue(10);
    assertEquals(1, queue.size(), "Размер очереди должен увеличиться до 1");

    queue.enqueue(20);
    queue.enqueue(30);
    assertEquals(3, queue.size(), "Размер очереди должен быть 3 после добавления 3 элементов");
  }

  @Test
  void testDequeue() {
    Queue<String> queue = new Queue<>();
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assertEquals("A", queue.dequeue(), "Первый удалённый элемент должен быть 'A'");
    assertEquals("B", queue.dequeue(), "Второй удалённый элемент должен быть 'B'");
    assertEquals("C", queue.dequeue(), "Третий удалённый элемент должен быть 'C'");

    assertEquals(0, queue.size(), "После удаления всех элементов очередь должна быть пустой");
  }

  @Test
  void testDequeueFromEmptyQueue() {
    Queue<Integer> queue = new Queue<>();
    assertNull(queue.dequeue(), "Удаление из пустой очереди должно вернуть null");
  }

  @Test
  void testEnqueueNullElement() {
    Queue<String> queue = new Queue<>();
    queue.enqueue(null);

    assertEquals(1, queue.size(), "Размер очереди должен быть 1 после добавления null");
    assertNull(queue.dequeue(), "Удалённый элемент должен быть null");
  }

  @Test
  void testSizeAfterOperations() {
    Queue<Double> queue = new Queue<>();
    queue.enqueue(1.1);
    queue.enqueue(2.2);
    queue.enqueue(3.3);
    queue.dequeue();
    queue.dequeue();

    assertEquals(1, queue.size(), "После двух добавлений и двух удалений в очереди должен остаться один элемент");

    queue.dequeue();
    assertEquals(0, queue.size(), "После удаления всех элементов размер очереди должен быть 0");
  }

  @Test
  void testSequentialOperations() {
    Queue<Integer> queue = new Queue<>();

    queue.enqueue(5);
    assertEquals(5, queue.dequeue(), "Первый добавленный элемент должен быть первым удалённым");

    queue.enqueue(10);
    queue.enqueue(15);
    assertEquals(10, queue.dequeue(), "Должны получить 10 при удалении");
    assertEquals(15, queue.dequeue(), "Должны получить 15 при удалении");

    assertNull(queue.dequeue(), "Очередь теперь пуста, удаление должно вернуть null");
  }
}


