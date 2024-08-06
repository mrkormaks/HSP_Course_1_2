import java.io.*;
import java.util.Scanner;

public class FileSumCalculator {

  public static int calculateSum(int fileNum1, int fileNum2, String path) throws IOException, NumberFormatException {
    int sum = 0;
    String[] filenames = {path + fileNum1 + ".txt", path + fileNum2 + ".txt"};

    for (String filename : filenames) {
      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        int count = 0;

        while ((line = br.readLine()) != null) {
          sum += Integer.parseInt(line);
          count++;
        }

        if (count != 3) {
          throw new IOException("Файл " + filename + " содержит отличное от 3-х число параметров.");
        }
      }
    }

    return sum;
  }

  public static void main(String[] args) {
    System.out.println("Введите два номера файлов от 1 до 10: ");
    Scanner sc = new Scanner(System.in);
    int[] integers = new int[2];

    for (int i = 0; i < 2; i++) {
      integers[i] = sc.nextInt();
    }

    try {
      int sum = calculateSum(integers[0], integers[1], "../TextFiles/");
      System.out.println("Сумма чисел в файлах " + integers[0] + ".txt и " + integers[1] + ".txt : " + sum);
    } catch (NumberFormatException e) {
      System.out.println("Файлы содержат невалидные данные.");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
