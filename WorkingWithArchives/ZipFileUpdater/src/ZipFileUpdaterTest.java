import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipFileUpdaterTest {

  private static final String ZIP_FILE_PATH = "test.zip";

  @BeforeEach
  public void setUp() throws IOException {
    Files.deleteIfExists(Paths.get(ZIP_FILE_PATH));
  }

  @Test
  public void testCreateEmptyZip() throws IOException {
    ZipFileUpdater.createEmptyZip(ZIP_FILE_PATH);
    assertTrue(Files.exists(Paths.get(ZIP_FILE_PATH)));

    // Проверяем, что архив пустой
    try (ZipFile zipFile = new ZipFile(ZIP_FILE_PATH)) {
      assertEquals(0, zipFile.size());
    }
  }

  @Test
  public void testAddFilesToExistingZip() throws IOException {
    // Создаём пустой архив
    ZipFileUpdater.createEmptyZip(ZIP_FILE_PATH);
    assertTrue(Files.exists(Paths.get(ZIP_FILE_PATH)));

    // Создаём временные файлы и записываем в них содержимое
    String[] filesToAdd = {"testFile1.txt", "testFile2.xml"};
    for (String fileName : filesToAdd) {
      Files.write(Paths.get(fileName), ("Содержимое файла " + fileName).getBytes());
    }

    // Добавляем файлы в архив
    ZipFileUpdater.addFilesToExistingZip(ZIP_FILE_PATH, filesToAdd);

    // Проверяем, что файлы успешно добавлены
    try (ZipFile zipFile = new ZipFile(ZIP_FILE_PATH)) {
      for (String fileName : filesToAdd) {
        assertNotNull(zipFile.getEntry(fileName));
        // Можно также проверить содержимое файлов в архиве, если это необходимо
      }
    }
  }
}
