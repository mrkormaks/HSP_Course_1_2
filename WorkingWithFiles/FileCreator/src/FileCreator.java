import java.io.*;
import java.util.Random;

public class FileCreator {
  public static void main(String[] args) {
    final int MAX_RANDOM = 100;
    Random random = new Random();

    for (int i = 1; i <= 10; i++) {
      File file = new File("../TextFiles/" + i + ".txt");  // Каталог на один уровень выше

      try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        for (int j = 0; j < 3; j++) {
          int randomNum = random.nextInt(MAX_RANDOM);
          bw.write(Integer.toString(randomNum));
          bw.newLine();
        }
      } catch (IOException e) {
        System.out.println("Ошибка записи файла " + file.getName());
        e.printStackTrace();
      }
    }
  }
}
