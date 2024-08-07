import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FileSumCalculatorTest {
  @Test
  void testCalculateSumValidFiles() {
    createTestFile("../TextFiles/1.txt", "10\n20\n30\n");
    createTestFile("../TextFiles/2.txt", "5\n15\n25\n");

    Integer result = FileSumCalculator.calculateSum(1, 2, "../TextFiles/");
    assertNotNull(result,  "Результат НЕ должен быть null для файлов с валидными данными");
    assertEquals(105, result, "Ожидаемая сумма чисел в файлах 1.txt и 2.txt должна быть 105");
  }

  @Test
  void testCalculateSumInvalidData() {
    createTestFile("../TextFiles/1.txt", "10\ninvalid\n30\n");
    createTestFile("../TextFiles/2.txt", "5\n15\n25\n");

    Integer result = FileSumCalculator.calculateSum(1, 2, "../TextFiles/");
    assertNull(result, "Результат должен быть null для файлов с невалидными данными");
  }

  private void createTestFile(String path, String content) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
      bw.write(content);
    } catch (IOException e) {
      fail("Не удалось создать тестовый файл: " + path);
    }
  }
}