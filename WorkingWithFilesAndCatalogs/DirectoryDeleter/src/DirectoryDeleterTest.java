import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class DirectoryDeleterTest {

  // Пути к тестовым директориям
  private static final String EMPTY_DIR = "TestEmptyDirectory";
  private static final String FULL_DIR = "TestFullDirectory";
  private static final String FILES_ONLY_DIR = "TestFilesOnlyDirectory";

  @BeforeEach // Аннотацией @BeforeEach помечаются методы, которые будут выполняться перед стартом каждого из тестовых методов (https://ru.hexlet.io/courses/java-testing/lessons/setup/theory_unit)
  void setUp() throws IOException {
    // Создаем тестовые директории и файлы перед каждым тестом
    Files.createDirectory(Paths.get(EMPTY_DIR));
    Files.createDirectory(Paths.get(FULL_DIR));
    Files.createDirectory(Paths.get(FULL_DIR, "subDirectory1"));
    Files.createDirectory(Paths.get(FULL_DIR, "subDirectory2"));
    Files.createFile(Paths.get(FULL_DIR, "file1.txt"));
    Files.createFile(Paths.get(FULL_DIR, "file2.txt"));
    Files.createDirectory(Paths.get(FILES_ONLY_DIR));
    Files.createFile(Paths.get(FILES_ONLY_DIR, "file1.txt"));
    Files.createFile(Paths.get(FILES_ONLY_DIR, "file2.txt"));
  }

  @Test
  void testDeleteDirectoryAndFiles() throws IOException {
    boolean result = DirectoryDeleter.tryDeleteDirectoryAndFiles(EMPTY_DIR);
    assertTrue(result, "Удаление пустого каталога должно быть успешным");
    assertFalse(Files.exists(Paths.get(EMPTY_DIR)), "Пустой каталог должен быть удален");

    result = DirectoryDeleter.tryDeleteDirectoryAndFiles(FULL_DIR);
    assertFalse(result, "Удаление каталога с подкаталогами и файлами не должно быть успешным");
    assertTrue(Files.exists(Paths.get(FULL_DIR)), "Каталог с подкаталогами и файлами должен быть удален");

    result = DirectoryDeleter.tryDeleteDirectoryAndFiles(FILES_ONLY_DIR);
    assertTrue(result, "Удаление каталога только с файлами должно быть успешным");
    assertFalse(Files.exists(Paths.get(FILES_ONLY_DIR)), "Каталог с файлами только должен быть удален");
  }
}
