import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileSumCalculator {

  private static final Logger logger = Logger.getLogger(FileSumCalculator.class.getName());

  public static Integer calculateSum(int fileNum1, int fileNum2, String path) {
    int sum = 0;
    String[] filenames = {path + fileNum1 + ".txt", path + fileNum2 + ".txt"};

    for (String filename : filenames) {
      int count = 0;

      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;

        while ((line = br.readLine()) != null) {
          try {
            int number = Integer.parseInt(line);
            sum += number;
            count++;
          } catch (NumberFormatException e) {
            logger.warning("Файл " + filename + " содержит невалидные данные.");
            return null;
          }
        }
      }
      catch (IOException e) {
        logger.warning("Не удалось открыть или прочитать файл " + filename);
        return null;
      }

      if (count != 3) {
        logger.warning("Файл " + filename + " содержит отличное от 3-х число параметров.");
        return null;
      }
    }

    return sum;
  }

  public static void main(String[] args) {
    logger.info("Введите два номера файлов от 1 до 10: ");
    Scanner sc = new Scanner(System.in);
    int[] integers = new int[2];

    for (int i = 0; i < 2; i++) {
      integers[i] = sc.nextInt();
    }

    Integer sum = calculateSum(integers[0], integers[1], "../TextFiles/");
    if (sum != null) {
      logger.info("Сумма чисел в файлах " + integers[0] + ".txt и " + integers[1] + ".txt : " + sum);
    } else {
      logger.info("Произошла ошибка при вычислении суммы чисел в файлах.");
    }
  }
}
