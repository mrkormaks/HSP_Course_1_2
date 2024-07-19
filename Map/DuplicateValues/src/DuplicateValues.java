import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class DuplicateValues {

  public static List<Integer> findDuplicateValues(List<Integer> values, int num) {

    Map<Integer, Integer> frequencyMap = new HashMap<>(); // запишем сюда количество повторений каждого числа из List (ключ - число, значение - количество повторений)

    for (Integer value : values) {
      if (frequencyMap.containsKey(value)) {
        frequencyMap.put(value, frequencyMap.get(value) + 1);
      } else {
        frequencyMap.put(value, 0 + 1);
      }
    }

    List<Integer> result = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
      if (entry.getValue() >= num) {
        result.add(entry.getKey());
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

    System.out.println("Значения " + result + " повторяются не менее " + num + " раз.");
  }
}