import java.util.*;

public class DuplicateValues {

  public static Map<Integer, Integer> findDuplicateValues(List<Integer> values, int num) {

    Map<Integer, Integer> frequencyMap = new HashMap<>(); // запишем сюда количество повторений каждого числа из List (ключ - число, значение - количество повторений)
    Map<Integer, Integer> result = new HashMap<>();

    for (Integer value : values) {
      int count = frequencyMap.getOrDefault(value, 0) + 1;
      frequencyMap.put(value, count);

      if (count >= num) {
        result.put(value, count);
      }
    }

    return result;
  }

  public static void main(String[] args) {

    List<Integer> values = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      values.add(random.nextInt(10) + 1); // единица добавляется для генерации от 1 до 10 включительно
    }

    System.out.println("Сгенерированные значения: " + values);

    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите количество повторений:");
    int num = scanner.nextInt();

    Map<Integer, Integer> result = findDuplicateValues(values, num);

    if (result.isEmpty()) {
      System.out.println("Нет значений, которые повторяются не менее " + num + " раз.");
    } else {
      System.out.println("Значения, которые повторяются не менее " + num + " раз:");
      for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
        System.out.println("Значение " + entry.getKey() + " повторяется " + entry.getValue() + " раз.");
      }
    }
  }
}