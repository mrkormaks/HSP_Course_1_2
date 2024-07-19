import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomPairsTest {

  @Test
  public void testAddAndRemoveEntries() {
    Map<Integer, String> map = new HashMap<>();
    Random random = new Random();

    while (map.size() < 100) {
      int key = random.nextInt(1000);
      String value = "v_" + map.size();
      if (!map.containsKey(key)) {
        map.put(key, value);
      }
    }

    assertEquals(100, map.size());

    for (Integer key : map.keySet()) {
      assertNotNull(map.get(key));
    }

    map.clear();

    assertTrue(map.isEmpty());
  }
}