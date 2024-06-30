import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileCreatorTest {

  @Test
  void testFilesExistence() {
    FileCreator.main(null);
    for (int i = 1; i <= 10; i++) {
      File file = new File("../TextFiles/" + i + ".txt");
      assertTrue(file.exists(), "Файл " + i + ".txt существует.");
    }
  }

  @Test
  void testFilesContent() throws IOException {
    FileCreator.main(null);
    for (int i = 1; i <= 10; i++) {
      File file = new File("../TextFiles/" + i + ".txt");
      List<String> lines = Files.readAllLines(file.toPath());
      assertEquals(3, lines.size(), "Файл " + i + ".txt должен содержать три строки.");
      for (String line : lines) {
        try {
          Integer.parseInt(line);
        } catch (NumberFormatException e) {
          fail("Файл " + i + ".txt должен содержать целочисленные переменные.");
        }
      }
    }
  }
}