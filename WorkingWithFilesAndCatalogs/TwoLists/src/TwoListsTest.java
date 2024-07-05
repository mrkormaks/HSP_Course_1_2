import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;

public class TwoListsTest {

  @Test
  public void testReturnLists() throws IOException {
    // Создание временной директории
    Path tempDirectory = Files.createTempDirectory("AnotherTestDir");

    try {
      // Создание файлов и подкаталогов
      Files.createFile(tempDirectory.resolve("file1.txt"));
      Files.createFile(tempDirectory.resolve("file2.txt"));
      Files.createFile(tempDirectory.resolve("log1.log"));
      Files.createDirectory(tempDirectory.resolve("subdir1"));
      Files.createDirectory(tempDirectory.resolve("subdir2"));
      Files.createFile(tempDirectory.resolve("subdir1").resolve("file3.txt"));
      Files.createFile(tempDirectory.resolve("subdir2").resolve("log2.log"));
      Files.createDirectory(tempDirectory.resolve("subdir2").resolve("subdir3"));

      String directoryPath = tempDirectory.toString();
      String fileExtension = "txt";
      boolean includeSubdirectories = true;

      List<List<String>> result = TwoLists.returnLists(directoryPath, fileExtension, includeSubdirectories);
      List<String> fileList = result.get(0);
      List<String> subdirectoryList = result.get(1);

      // Проверка файлов
      assertEquals(3, fileList.size(), "Должно быть 3 .txt файла");
      assertTrue(fileList.contains(tempDirectory.resolve("file1.txt").toString()));
      assertTrue(fileList.contains(tempDirectory.resolve("file2.txt").toString()));
      assertTrue(fileList.contains(tempDirectory.resolve("subdir1").resolve("file3.txt").toString()));

      // Проверка подкаталогов
      assertEquals(3, subdirectoryList.size(), "Должно быть 2 подкаталога в тестовом каталоге и 1 каталог первого уровня вложенности в одном из них");
      assertTrue(subdirectoryList.contains(tempDirectory.resolve("subdir1").toString()));
      assertTrue(subdirectoryList.contains(tempDirectory.resolve("subdir2").toString()));
      assertTrue(subdirectoryList.contains(tempDirectory.resolve("subdir2").resolve("subdir3").toString()));
    } finally {
      // Удаление временной директории и всех её содержимого
      Files.walk(tempDirectory)
              .sorted(Comparator.reverseOrder())
              .map(Path::toFile)
              .forEach(java.io.File::delete);
    }
  }
}
