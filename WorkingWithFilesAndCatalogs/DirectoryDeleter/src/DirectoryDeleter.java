import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;
import java.io.File;


public class DirectoryDeleter {

  public static boolean deleteDirectoryAndFiles(String directoryPath) throws IOException {
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

  public static void deleteIfExists(String directoryPath) throws IOException { // Взято с https://www.baeldung.com/java-delete-directory
    Path pathToBeDeleted = Paths.get(directoryPath);
    if (!Files.exists(pathToBeDeleted) || !Files.isDirectory(pathToBeDeleted))
      return;

    try (Stream<Path> pathStream = Files.walk(pathToBeDeleted)) {
      pathStream.sorted(Comparator.reverseOrder())
              .map(Path::toFile)
              .forEach(File::delete);
    }
  }

  public static void main(String[] args) throws IOException {
    deleteIfExists("EmptyDirectory");
    deleteIfExists("FullDirectory");
    deleteIfExists("FilesOnlyDirectory");

    try {
      // Создание пустой директории
      Path emptyDir = Paths.get("EmptyDirectory");
      Files.createDirectory(emptyDir);

      // Создание директории с подкаталогами и файлами
      Path FullDirectory = Paths.get("FullDirectory");
      Files.createDirectory(FullDirectory);
      Files.createDirectory(FullDirectory.resolve("subDirectory1"));
      Files.createDirectory(FullDirectory.resolve("subDirectory2"));
      Files.createFile(FullDirectory.resolve("file1.txt"));
      Files.createFile(FullDirectory.resolve("file2.txt"));

      // Создание директории с файлами, но без подкаталогов
      Path filesOnlyDir = Paths.get("FilesOnlyDirectory");
      Files.createDirectory(filesOnlyDir);
      Files.createFile(filesOnlyDir.resolve("file1.txt"));
      Files.createFile(filesOnlyDir.resolve("file2.txt"));

      // Применение метода удаления к пустой директории
      boolean result1 = deleteDirectoryAndFiles("EmptyDirectory");
      System.out.println("Deletion of empty directory " + (result1 ? "successful" : "failed"));

      // Применение метода удаления к директории с подкаталогами и файлами
      boolean result2 = deleteDirectoryAndFiles("FullDirectory");
      System.out.println("Deletion of parent directory " + (result2 ? "successful" : "failed"));

      // Применение метода удаления к директории с файлами, но без подкаталогов
      boolean result3 = deleteDirectoryAndFiles("FilesOnlyDirectory");
      System.out.println("Deletion of files-only directory " + (result3 ? "successful" : "failed"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
