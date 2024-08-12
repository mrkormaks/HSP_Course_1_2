import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

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

  private static final Logger logger = Logger.getLogger(FileConfigReader.class.getName());

  public enum ErrorCode {
    SUCCESS,
    FILE_NOT_FOUND,
    FILE_READ_ERROR,
    ILLEGAL_DATA,
    INVALID_DATA_FORMAT,
    INCORRECT_PARAMETERS_COUNT
  }

  public static class Result {
    private final ArrayList<Cat> cats;
    private final ErrorCode errorCode;

    public Result(ArrayList<Cat> cats, ErrorCode errorCode) {
      this.cats = cats;
      this.errorCode = errorCode;
    }

    public ArrayList<Cat> getCats() {
      return this.cats;
    }

    public ErrorCode getErrorCode() {
      return this.errorCode;
    }
  }

  // Взято с https://www.baeldung.com/java-log-console-in-color
  public static void logInfo(String msg) {
    logger.info("\u001B[32m" + msg + "\u001B[0m");
  }

  public static void logWarning(String msg) {
    logger.warning("\u001B[33m" + msg + "\u001B[0m");
  }

  public static Result parseCats(String filename) {
    ArrayList<Cat> cats = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = br.readLine()) != null) { // Чтобы каждый цикл не обновлять line внутри while
        try {
          String[] parts = line.split(" ");
          if (parts.length != 3) {
            logWarning("Некорректное количество параметров в строке " + line + ". " + "Должно быть 3 параметра. Сейчас: " + parts.length);
            return new Result(null, ErrorCode.INCORRECT_PARAMETERS_COUNT);
          }

          String name;
          double weight;
          int purrFrequency;

          try {
            name = parts[0];
            weight = Double.parseDouble(parts[1]);
            purrFrequency = Integer.parseInt(parts[2]);
          } catch (NumberFormatException e) {
            logWarning("Невалидный тип данных в строке " + line + ".");
            return new Result(null, ErrorCode.INVALID_DATA_FORMAT);
          }

          cats.add(new Cat(name, weight, purrFrequency));
        } catch (IllegalArgumentException e) {
          logWarning("Недопустимый параметр в строке " + line + ".");
          return new Result(null, ErrorCode.ILLEGAL_DATA);
        }
      }
    } catch (FileNotFoundException e) {
      logWarning("Файл " + filename + " не найден.");
      return new Result(null, ErrorCode.FILE_NOT_FOUND);
    } catch (IOException e) {
      logWarning("Ошибка чтения файла " + filename + ".");
      return new Result(null, ErrorCode.FILE_READ_ERROR);
    }

    return new Result(cats, ErrorCode.SUCCESS);
  }

  public static void main(String[] args) {
    String filename = "Cats.txt"; // Создал заранее

    Result result = parseCats(filename);

    if (result.getErrorCode() == ErrorCode.SUCCESS) {
      for (Cat cat : result.getCats()) {
        logInfo("Имя: " + cat.name + "; " + "Вес: " + cat.weight + "; " + "Частота мурчания: " + cat.purrFrequency + ".");
      }
    } else {
      logWarning("Массив данных пуст. Код ошибки: " + result.getErrorCode());
    }
  }
}