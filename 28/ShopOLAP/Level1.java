import java.util.*;

public class Level1 {
  public static String[] ShopOLAP(int N, String[] items) {
    Map<String, Integer> salesMap = new HashMap<>();

    for (String item : items) {
      String[] parts = item.split(" ");
      String name = parts[0];
      int count = Integer.parseInt(parts[1]);

      if (salesMap.containsKey(name)) {
        salesMap.put(name, salesMap.get(name) + count);
      } else {
        salesMap.put(name, count);
      }
    }

    List<Map.Entry<String, Integer>> list = new ArrayList<>(salesMap.entrySet());

    list.sort((o1, o2) -> {
      int compareByValue = o2.getValue().compareTo(o1.getValue());
      if (compareByValue == 0) {
        return o1.getKey().compareTo(o2.getKey());
      }
      return compareByValue;
    });
    
    String[] result = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i).getKey() + " " + list.get(i).getValue();
    }

    return result;
  }
}