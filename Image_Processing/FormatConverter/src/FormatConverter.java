import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

public class FormatConverter {

  private static final Logger logger = Logger.getLogger(FormatConverter.class.getName());

  public enum ErrorCode {
    SUCCESS,
    FILE_NOT_FOUND,
    FILE_READ_ERROR,
    INVALID_DATA_FORMAT
  }

  // Логирование в консоль с цветом
  public static void logInfo(String msg) {
    logger.info("\u001B[32m" + msg + "\u001B[0m");
  }

  public static void logWarning(String msg) {
    logger.warning("\u001B[33m" + msg + "\u001B[0m");
  }

  // Метод для конвертации форматов изображений
  public static ErrorCode tryConvertImageFormat(String path, String sourceFormat, String targetFormat, boolean includeSubdirectories) {
    List<List<String>> directoryContent;
    try {
      directoryContent = returnDirectoryContent(path, sourceFormat, includeSubdirectories);
    } catch (IllegalArgumentException e) {
      logWarning("Ошибка: " + e.getMessage());
      return ErrorCode.FILE_NOT_FOUND;
    } catch (IOException e) {
      logWarning("Ошибка чтения файлов в директории: " + path);
      return ErrorCode.FILE_READ_ERROR;
    }

    List<String> filesList = directoryContent.get(0);
    if (filesList.isEmpty()) {
      logWarning("В каталоге нет файлов с расширением ." + sourceFormat);
      return ErrorCode.FILE_NOT_FOUND;
    }

    for (String filePath : filesList) {
      File file = new File(filePath);
      try {
        BufferedImage img = ImageIO.read(file);
        if (img == null) {
          logWarning("Неверный формат данных в файле: " + file.getName());
          return ErrorCode.INVALID_DATA_FORMAT;
        }
        String newFileName = file.getName().replaceAll("\\." + sourceFormat + "$", "." + targetFormat);
        ImageIO.write(img, targetFormat, new File(file.getParent() + "/" + newFileName));
        logInfo(file.getName() + " сконвертирован в " + newFileName);
      } catch (IOException e) {
        logWarning("Ошибка при чтении или записи файла: " + file.getName());
        return ErrorCode.FILE_READ_ERROR;
      } catch (IllegalArgumentException e) {
        logWarning("Некорректные данные в файле: " + file.getName());
        return ErrorCode.INVALID_DATA_FORMAT;
      }
    }
    return ErrorCode.SUCCESS;
  }

  // Метод для возврата содержимого директории
  public static List<List<String>> returnDirectoryContent(String directoryPath, String fileExtension, boolean includeSubdirectories) throws IllegalArgumentException, IOException {
    List<String> filesList = new ArrayList<>();
    List<String> subdirectoriesList = new ArrayList<>();

    Path directory = Paths.get(directoryPath);
    if (!Files.exists(directory) || !Files.isDirectory(directory))
      throw new IllegalArgumentException("Каталога с таким именем не существует или файл не является каталогом: " + directoryPath);

    try (Stream<Path> stream = Files.walk(directory, 1)) {
      stream.forEach(path -> {
        if (Files.isRegularFile(path) && path.toString().endsWith("." + fileExtension)) {
          filesList.add(path.toString());
        } else if (Files.isDirectory(path) && !path.equals(directory)) {
          subdirectoriesList.add(path.toString());
          if (includeSubdirectories) {
            try {
              List<List<String>> subdirectoryResult = returnDirectoryContent(path.toString(), fileExtension, false);
              filesList.addAll(subdirectoryResult.get(0));
              subdirectoriesList.addAll(subdirectoryResult.get(1));
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    List<List<String>> resultList = new ArrayList<>();
    resultList.add(filesList);
    resultList.add(subdirectoriesList);

    return resultList;
  }

  // Метод main
  public static void main(String[] args) throws IOException {
    String path = "../images";
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите исходный формат:");
    String sourceFormat = scanner.nextLine();
    System.out.println("Введите необходимый формат:");
    String targetFormat = scanner.nextLine();
    scanner.close();

    // Создание тестовых файлов изображений
    for (int i = 1; i <= 4; i++) {
      BufferedImage newImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
      ImageIO.write(newImage, sourceFormat, new File(path + "/image" + i + "." + sourceFormat));
      logInfo("Файл изображения " + i + " создан");
    }

    // Вызов метода конвертации с учетом подкаталогов
    ErrorCode result = tryConvertImageFormat(path, sourceFormat, targetFormat, true);
    if (result == ErrorCode.SUCCESS) {
      logInfo("\nКонвертация завершена успешно");
    } else {
      logWarning("\nКонвертация завершена с ошибкой: " + result);
    }
  }
}