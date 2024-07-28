import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TwoListsTest {

  private static final String SUBDIR_1 = "subdir1";
  private static final String SUBDIR_2 = "subdir2";
  private static final String SUBDIR_3 = "subdir3";

  @Test
  public void testReturnLists() throws IOException {
    // Создание временной директории
    Path tempDirectory = Files.createTempDirectory("AnotherTestDir");

    try {
      // Создание файлов и подкаталогов
      Files.createFile(tempDirectory.resolve("file1.txt"));
      Files.createFile(tempDirectory.resolve("file2.txt"));
      Files.createFile(tempDirectory.resolve("log1.log"));
      Files.createDirectory(tempDirectory.resolve(SUBDIR_1));
      Files.createDirectory(tempDirectory.resolve(SUBDIR_2));
      Files.createFile(tempDirectory.resolve(SUBDIR_1).resolve("file3.txt"));
      Files.createFile(tempDirectory.resolve(SUBDIR_2).resolve("log2.log"));
      Files.createDirectory(tempDirectory.resolve(SUBDIR_2).resolve(SUBDIR_3));

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
      assertTrue(fileList.contains(tempDirectory.resolve(SUBDIR_1).resolve("file3.txt").toString()));

      // Проверка подкаталогов
      assertEquals(3, subdirectoryList.size(), "Должно быть 2 подкаталога в тестовом каталоге и 1 каталог первого уровня вложенности в одном из них");
      assertTrue(subdirectoryList.contains(tempDirectory.resolve(SUBDIR_1).toString()));
      assertTrue(subdirectoryList.contains(tempDirectory.resolve(SUBDIR_2).toString()));
      assertTrue(subdirectoryList.contains(tempDirectory.resolve(SUBDIR_2).resolve(SUBDIR_3).toString()));
    } finally {
      // Удаление временной директории и всех её содержимого
      try (Stream<Path> stream = Files.walk(tempDirectory)) {
        stream.sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(java.io.File::delete);
      }
    }
  }
}
