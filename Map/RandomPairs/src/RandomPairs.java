import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomPairs {
  public static void main(String[] args) {
    Map<Integer, String> map = new HashMap<>();
    Random random = new Random();

    while (map.size() < 100) {
      int key = random.nextInt(1000);
      String value = "v_" + map.size();
      if (!map.containsKey(key)) {
        map.put(key, value);
      }
    }

    System.out.println("Конейнер заполнен. Текущий размер: " + map.size() + "\n");

    for (Integer key : map.keySet()) {
      System.out.println("Ключ: " + key + ", Значение: " + map.get(key));
    }

    map.clear();
    System.out.println("\nВсё содержимое контейнера было удалено. Текущий размер: " + map.size());
  }
}