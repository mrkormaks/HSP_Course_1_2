import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileSumCalculator {

  private static final Logger logger = Logger.getLogger(FileSumCalculator.class.getName());

  public enum ErrorCode {
    SUCCESS,
    FILE_NOT_FOUND,
    FILE_READ_ERROR,
    INVALID_DATA,
    INCORRECT_PARAMETER_COUNT
  }

  public static class Result {
    private final Integer sum;
    private final ErrorCode errorCode;

    public Result(Integer sum, ErrorCode errorCode) {
      this.sum = sum;
      this.errorCode = errorCode;
    }

    public Integer getSum() {
      return this.sum;
    }

    public ErrorCode getErrorCode() {
      return this.errorCode;
    }
  }

  public static Result calculateSum(int fileNum1, int fileNum2, String path) {
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
            return new Result(null, ErrorCode.INVALID_DATA);
          }
        }
      }
      catch (FileNotFoundException e) {
        logger.warning("Файл " + filename + " не найден.");
        return new Result(null, ErrorCode.FILE_NOT_FOUND);
      }
      catch (IOException e) {
        logger.warning("Не удалось открыть или прочитать файл " + filename);
        return new Result(null, ErrorCode.FILE_READ_ERROR);
      }

      if (count != 3) {
        logger.warning("Файл " + filename + " содержит отличное от 3-х число параметров.");
        return new Result(null, ErrorCode.INCORRECT_PARAMETER_COUNT);
      }
    }

    return new Result(sum, ErrorCode.SUCCESS);
  }

  public static void main(String[] args) {
    logger.info("Введите два номера файлов от 1 до 10: ");
    Scanner sc = new Scanner(System.in);
    int[] integers = new int[2];

    for (int i = 0; i < 2; i++) {
      integers[i] = sc.nextInt();
    }

    Result result = calculateSum(integers[0], integers[1], "../TextFiles/");
    if (result.errorCode == ErrorCode.SUCCESS) {
      logger.info("Сумма чисел в файлах " + integers[0] + ".txt и " + integers[1] + ".txt : " + result.sum);
    } else {
      logger.info("Произошла ошибка: " + result.errorCode);
    }
  }
}
