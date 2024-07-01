import java.io.*;
import java.util.Scanner;

public class FileSumCalculator {
  public static String calculateSum(int fileNum1, int fileNum2, String path) {
    int sum = 0;
    String[] filenames = {path + fileNum1 + ".txt", path + fileNum2 + ".txt"};

    for (String filename : filenames) {
      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        int count = 0;

        while ((line = br.readLine()) != null) {
          try {
            sum += Integer.parseInt(line);
            count++;
          } catch (NumberFormatException e) {
            return "Файл " + filename + " содержит невалидные данные.";
          }
        }

        if (count != 3) {
          return "Файл " + filename + " содержит отличное от 3-х число параметров.";
        }
      } catch (IOException e) {
        return "Ошибка во время чтения файла " + filename + ".";
      }
    }

    return "Сумма чисел в файлах " + fileNum1 + ".txt и " + fileNum2 + ".txt : " + sum;
  }

  public static void main(String[] args) {
    System.out.println("Введите два номера файлов от 1 до 10: ");
    Scanner sc = new Scanner(System.in);
    int[] integers = new int[2];

    for (int i = 0; i < 2; i++) {
      integers[i] = sc.nextInt();
    }

    System.out.println(calculateSum(integers[0], integers[1], "../TextFiles/"));
  }
}