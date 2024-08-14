import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Logger;

public class DirectoryDeleter {

  // Пути к тестовым директориям
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

    if (hasSubdirectories(dir))
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

  private static boolean hasSubdirectories(Path dir) throws IOException {
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
      for (Path path : stream) {
        if (Files.isDirectory(path)) {
          return true; // Если есть подкаталоги
        }
      }
    }
    return false; // Нет подкаталогов
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
