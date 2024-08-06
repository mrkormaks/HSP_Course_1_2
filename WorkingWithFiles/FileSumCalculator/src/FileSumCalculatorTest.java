import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FileSumCalculatorTest {
  @Test
  void testCalculateSumValidFiles() throws IOException {
    createTestFile("../TextFiles/1.txt", "10\n20\n30\n");
    createTestFile("../TextFiles/2.txt", "5\n15\n25\n");

    int result = FileSumCalculator.calculateSum(1, 2, "../TextFiles/");
    assertEquals(105, result);
  }

  @Test
  void testCalculateSumInvalidData() {
    createTestFile("../TextFiles/1.txt", "10\ninvalid\n30\n");
    createTestFile("../TextFiles/2.txt", "5\n15\n25\n");

    assertThrows(NumberFormatException.class, () -> FileSumCalculator.calculateSum(1, 2, "../TextFiles/"));
  }

  private void createTestFile(String path, String content) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
      bw.write(content);
    } catch (IOException e) {
      fail("Не удалось создать тестовый файл: " + path);
    }
  }
}