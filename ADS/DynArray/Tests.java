import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class DynArrayTest {
  private DynArray<Integer> dynArray;
  private DynamicArray<Integer> array;
  private DynamicMultiArray<Integer> multiArray;

  @BeforeEach
  void setUp() {
    dynArray = new DynArray<>(Integer.class);
    array = new DynamicArray<>(Integer.class, 2);
    multiArray = new DynamicMultiArray<>(Integer.class, 3, 3, 3);
  }

  // Tests for DynArray
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

  // Test for DynamicArray (banker's algorithm)
  @Test
  void testAppendAndResize() {
    array.append(10);
    array.append(20);
    array.append(30);
    assertEquals(10, array.getItem(0));
    assertEquals(20, array.getItem(1));
    assertEquals(30, array.getItem(2));
  }

  @Test
  void testInsert() {
    array.append(10);
    array.append(30);
    array.insert(20, 1);
    assertEquals(10, array.getItem(0));
    assertEquals(20, array.getItem(1));
    assertEquals(30, array.getItem(2));
  }

  @Test
  void testRemove() {
    array.append(10);
    array.append(20);
    array.append(30);
    array.remove(1);
    assertEquals(10, array.getItem(0));
    assertEquals(30, array.getItem(1));
  }

  // Tests for MultiArray
  @Test
  void testInsertAndRetrieve() {
      multiArray.insert(new int[]{1, 2, 2}, 42);
      assertEquals(42, multiArray.retrieve(new int[]{1, 2, 2}));
  }

  @Test
  void testRemoveFromMultiArray() {
      multiArray.insert(new int[]{1, 1, 1}, 99);
      multiArray.remove(new int[]{1, 1, 1});
      assertThrows(IndexOutOfBoundsException.class, () -> multiArray.retrieve(new int[]{1, 1, 1}));
  }
}



