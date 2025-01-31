import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class DynArrayTest {
  private DynArray<Integer> dynArray;

  @BeforeEach
  void setUp() {
    dynArray = new DynArray<>(Integer.class);
  }

  @Test
  void testAppendAndGetItem() {
    dynArray.append(10);
    assertEquals(10, dynArray.getItem(0));
  }

  @Test
  void testAppendExpandsCapacity() {
    for (int i = 0; i < 17; i++) {
      dynArray.append(i);
    }
    assertEquals(17, dynArray.count);
    assertTrue(dynArray.capacity >= 32);
  }

  @Test
  void testInsertAtValidPosition() {
    dynArray.append(1);
    dynArray.append(3);
    dynArray.insert(2, 1);
    assertEquals(2, dynArray.getItem(1));
  }

  @Test
  void testInsertAtEnd() {
    dynArray.append(1);
    dynArray.insert(2, 1);
    assertEquals(2, dynArray.getItem(1));
  }

  @Test
  void testInsertOutOfBounds() {
    assertThrows(IndexOutOfBoundsException.class, () -> dynArray.insert(5, 2));
  }

  @Test
  void testRemoveElement() {
    dynArray.append(1);
    dynArray.append(2);
    dynArray.remove(0);
    assertEquals(2, dynArray.getItem(0));
  }

  @Test
  void testRemoveShrinkCapacity() {
    for (int i = 0; i < 33; i++) {
      dynArray.append(i);
    }
    for (int i = 0; i < 17; i++) {
      dynArray.remove(0);
    }
    assertTrue(dynArray.capacity < 64);
  }

  @Test
  void testRemoveOutOfBounds() {
    assertThrows(IndexOutOfBoundsException.class, () -> dynArray.remove(0));
  }
}



