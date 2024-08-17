import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class DirectoryDeleter {

  // Константы
  private static final String EMPTY_DIR = "EmptyDirectory";
  private static final String FULL_DIR = "FullDirectory";
  private static final String FILES_ONLY_DIR = "FilesOnlyDirectory";
  private static final String SUCCESS = "успешно";
  private static final String FAILURE = "не успешно";


  private static final Logger logger = Logger.getLogger(DirectoryDeleter.class.getName());

  public static boolean tryDeleteDirectoryAndFiles(String directoryPath) throws IOException {
    Path dir = Paths.get(directoryPath);

    if (!Files.exists(dir) || !Files.isDirectory(dir)) {
      return false;
    }

    List<List<String>> directoryContentLists = returnDirectoryContent(directoryPath, "txt", false);
    if (!directoryContentLists.get(1).isEmpty())
      return false;

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) { // Объект для перебора записей в каталоге. Поток каталогов позволяет удобно использовать конструкцию for-each для перебора каталогов (https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/file/DirectoryStream.html)

      for (Path path : stream)
        Files.delete(path);
      Files.delete(dir);

      return true;
    } catch (IOException e) {
      e.printStackTrace();

      return false;
    }
  }

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

  public static void main(String[] args) throws IOException {

    try {
      // Создание пустой директории
      Path emptyDir = Paths.get(EMPTY_DIR);
      Files.createDirectory(emptyDir);

      // Создание директории с подкаталогами и файлами
      Path fullDirectory = Paths.get(FULL_DIR);
      Files.createDirectory(fullDirectory);
      Files.createDirectory(fullDirectory.resolve("subDirectory1"));
      Files.createDirectory(fullDirectory.resolve("subDirectory2"));
      Files.createFile(fullDirectory.resolve("file1.txt"));
      Files.createFile(fullDirectory.resolve("file2.txt"));

      // Создание директории с файлами, но без подкаталогов
      Path filesOnlyDir = Paths.get(FILES_ONLY_DIR);
      Files.createDirectory(filesOnlyDir);
      Files.createFile(filesOnlyDir.resolve("file1.txt"));
      Files.createFile(filesOnlyDir.resolve("file2.txt"));

      // Применение метода удаления к пустой директории
      boolean result1 = tryDeleteDirectoryAndFiles(EMPTY_DIR);
      logger.info("Удаление пустого каталога " + (result1 ? SUCCESS : FAILURE));

      // Применение метода удаления к директории с подкаталогами и файлами
      boolean result2 = tryDeleteDirectoryAndFiles(FULL_DIR);
      logger.info("Удаление каталога с файлами и подкаталогами " + (result2 ? SUCCESS : FAILURE));

      // Применение метода удаления к директории с файлами, но без подкаталогов
      boolean result3 = tryDeleteDirectoryAndFiles(FILES_ONLY_DIR);
      logger.info("Удаление каталога с файлами " + (result3 ? SUCCESS : FAILURE));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
