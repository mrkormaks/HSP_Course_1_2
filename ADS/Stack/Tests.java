import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {
  private Stack<Integer> stack;

  @Before
  public void setUp() {
    stack = new Stack<>();
  }

  @Test
  public void testInitialSize() {
    assertEquals(0, stack.size());
  }

  @Test
  public void testPush() {
    stack.push(1);
    assertEquals(1, stack.size());
    assertEquals(Integer.valueOf(1), stack.peek());
  }

  @Test
  public void testPeek() {
    assertNull(stack.peek());
    stack.push(1);
    assertEquals(Integer.valueOf(1), stack.peek());
    assertEquals(1, stack.size());
  }

  @Test
  public void testPop() {
    assertNull(stack.pop());

    stack.push(1);
    assertEquals(Integer.valueOf(1), stack.pop());
    assertEquals(0, stack.size());
  }

  @Test
  public void testMultipleOperations() {
    stack.push(1);
    stack.push(2);
    stack.push(3);

    assertEquals(3, stack.size());
    assertEquals(Integer.valueOf(3), stack.pop());
    assertEquals(Integer.valueOf(2), stack.pop());
    assertEquals(Integer.valueOf(1), stack.pop());
    assertEquals(0, stack.size());
  }

  @Test
  public void testDifferentTypes() {
    Stack<String> stringStack = new Stack<>();
    stringStack.push("first");
    stringStack.push("second");
    assertEquals(2, stringStack.size());
    assertEquals("second", stringStack.pop());
    assertEquals("first", stringStack.pop());
    assertNull(stringStack.pop());

    Stack<Double> doubleStack = new Stack<>();
    doubleStack.push(1.5);
    doubleStack.push(2.7);
    assertEquals(2, doubleStack.size());
    assertEquals(Double.valueOf(2.7), doubleStack.peek());
    assertEquals(Double.valueOf(2.7), doubleStack.pop());
    assertEquals(Double.valueOf(1.5), doubleStack.pop());

    Stack<Boolean> booleanStack = new Stack<>();
    booleanStack.push(true);
    booleanStack.push(false);
    assertEquals(2, booleanStack.size());
    assertEquals(false, booleanStack.pop());
    assertEquals(true, booleanStack.pop());
  }
}



