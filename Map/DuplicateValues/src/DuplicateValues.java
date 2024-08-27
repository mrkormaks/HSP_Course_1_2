import java.util.*;

public class DuplicateValues {

  public static List<Integer> findDuplicateValues(List<Integer> values, int num) {

    Map<Integer, Integer> frequencyMap = new HashMap<>(); // запишем сюда количество повторений каждого числа из List (ключ - число, значение - количество повторений)
    List<Integer> result = new ArrayList<>();

    for (Integer value : values) {
      int count = frequencyMap.getOrDefault(value, 0) + 1;
      frequencyMap.put(value, count);

      if (count == num) {
        result.add(value);
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

    List<Integer> result = findDuplicateValues(values, num);

    if (result.isEmpty()) {
      System.out.println("Нет значений, которые повторяются не менее " + num + " раз.");
    } else {
      System.out.println("Значения " + result + " повторяются не менее " + num + " раз:");
    }
  }
}