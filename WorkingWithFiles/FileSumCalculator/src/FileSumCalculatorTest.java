import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FileSumCalculatorTest {
  @Test
  void testCalculateSumValidFiles() {
    createTestFile("../TextFiles/1.txt", "10\n20\n30\n");
    createTestFile("../TextFiles/2.txt", "5\n15\n25\n");

    String result = FileSumCalculator.calculateSum(1, 2, "../TextFiles/");
    assertEquals("Сумма чисел в файлах 1.txt и 2.txt : 105", result);
  }

  @Test
  void testCalculateSumInvalidData() {
    createTestFile("../TextFiles/1.txt", "10\ninvalid\n30\n");
    createTestFile("../TextFiles/2.txt", "5\n15\n25\n");

    String result = FileSumCalculator.calculateSum(1, 2, "../TextFiles/");
    assertEquals("Файл ../TextFiles/1.txt содержит невалидные данные.", result);
  }

  private void createTestFile(String path, String content) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
      bw.write(content);
    } catch (IOException e) {
      fail("Не удалось создать тестовый файл: " + path);
    }
  }
}