import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class DuplicateValuesTest {

  @Test
  public void testFindDuplicateValues() {
    List<Integer> values = Arrays.asList(
            1, 2, 3, 4, 5, 1, 2, 3, 4, 5,
            1, 2, 3, 4, 5, 1, 2, 3, 4, 5,
            1, 2, 3, 4, 5, 1, 2, 3, 4, 5,
            6, 7, 8, 9, 10, 6, 7, 8, 9, 10,
            6, 7, 8, 9, 10, 6, 7, 8, 9, 10,
            6, 7, 8, 9, 10, 6, 7, 8, 9, 10,
            6, 7, 8, 9, 10, 6, 7, 8, 9, 10,
            6, 7, 8, 9, 10, 6, 7, 8, 9, 10,
            6, 7, 8, 9, 10, 6, 7, 8, 9, 10,
            6, 7, 8, 9, 10, 6, 7, 8, 9, 10
    );

    int num = 5;
    List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Integer> result = DuplicateValues.findDuplicateValues(values, num);

    assertEquals(expected.size(), result.size());
    assertTrue(result.containsAll(expected), "Результат должен содержать все ожидаемые элементы");
  }

  @Test
  public void testFindDuplicateValuesWithDifferentCounts() {
    List<Integer> values = Arrays.asList(
            1, 1, 2, 2, 2, 3, 3, 3, 3, 4,
            4, 4, 4, 4, 5, 5, 5, 5, 5, 6,
            6, 6, 6, 6, 6, 7, 7, 7, 7, 7,
            7, 8, 8, 8, 8, 8, 8, 8, 9, 9,
            9, 9, 9, 9, 9, 9, 10, 10, 10, 10,
            10, 10, 10, 10, 10, 10, 6, 7, 8, 9,
            6, 7, 7, 7, 8, 8, 9, 9, 9, 9,
            10, 10, 10, 10, 10, 7, 8, 9, 9, 10,
            6, 6, 6, 6, 6, 6, 7, 7, 7, 7,
            7, 8, 8, 8, 8, 8, 9, 9, 9, 9
    );

    int num = 7;
    List<Integer> expected = Arrays.asList(6, 7, 8, 9, 10);
    List<Integer> result = DuplicateValues.findDuplicateValues(values, num);

    assertEquals(expected.size(), result.size());
    assertTrue(result.containsAll(expected), "Результат должен содержать все ожидаемые элементы");
  }

  @Test
  public void testFindDuplicateValuesWithNoRepetitions() {
    List<Integer> values = Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    );

    int num = 2; // заведомо ставим число больше, чем любой элемент из списка повторяется
    List<Integer> expected = new ArrayList<>();
    List<Integer> result = DuplicateValues.findDuplicateValues(values, num);

    assertEquals(expected.size(), result.size());
    assertTrue(result.isEmpty(), "Результат должен быть пустым");
  }
}