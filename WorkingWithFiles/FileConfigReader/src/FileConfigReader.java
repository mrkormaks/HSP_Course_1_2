import java.io.*;
import java.util.ArrayList;

class Cat {
  String name;
  double weight;
  int purrFrequency;

  public Cat(String name, double weight, int purrFrequency) {
    this.name = name;
    this.weight = weight;
    this.purrFrequency = purrFrequency;
  }
}

public class FileConfigReader {
  public static void main(String[] args) {
    ArrayList<Cat> cats = new ArrayList<>();
    String filename = "Cats.txt"; // Создал заранее

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = br.readLine()) != null) { // Чтобы каждый цикл не обновлять line внутри while
        try {
          String[] parts = line.split(" ");
          if (parts.length != 3) {
            throw new IllegalArgumentException("Некорректное количество параметров.");
          }

          String name = parts[0];
          double weight = Double.parseDouble(parts[1]);
          int purrFrequency = Integer.parseInt(parts[2]);

          cats.add(new Cat(name, weight, purrFrequency));
        } catch (Exception e) {
          System.out.println("Ошибка обработки строки: " + line);
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      System.out.println("Ошибка чтения файла " + filename + ".");
      e.printStackTrace();
    }

    for (Cat cat : cats) {
      System.out.println("Имя: " + cat.name + "; " + "Вес: " + cat.weight + "; " + "Частота мурчания: " + cat.purrFrequency + ".");
    }
  }
}