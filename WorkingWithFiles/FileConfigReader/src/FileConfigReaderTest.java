import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FileConfigReaderTest {

  @Test
  void testFileConfigReader() throws IOException {
    File tempFile = File.createTempFile("CatsTest", ".txt", new File("."));
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
      writer.write("Барсик 5.0 75\n");
      writer.write("Мурзик 4.5 80\n");
      writer.write("Рыжик 6.2 70\n");
    }

    FileConfigReader.Result result = FileConfigReader.parseCats(tempFile.getName());

    assertEquals(3, result.getCats().size(), "Должно быть создано 3 объекта Cat");

    assertEquals("Барсик", result.getCats().get(0).name);
    assertEquals(5.0, result.getCats().get(0).weight, 0.01);
    assertEquals(75, result.getCats().get(0).purrFrequency);

    assertEquals("Мурзик", result.getCats().get(1).name);
    assertEquals(4.5, result.getCats().get(1).weight, 0.01);
    assertEquals(80, result.getCats().get(1).purrFrequency);

    assertEquals("Рыжик", result.getCats().get(2).name);
    assertEquals(6.2, result.getCats().get(2).weight, 0.01);
    assertEquals(70, result.getCats().get(2).purrFrequency);

    tempFile.delete();
  }

  @Test
  void testFileConfigReaderWithIncorrectParametersCount() throws IOException {
    File tempFile = File.createTempFile("CatsTestErrors", ".txt", new File("."));
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
      writer.write("Барсик 5.0 75\n");
      writer.write("Мурзик 4.5 80\n");
      writer.write("Рыжик 6.2 50 yy\n"); // INCORRECT_PARAMETERS_COUNT
    }

    FileConfigReader.Result result = FileConfigReader.parseCats(tempFile.getName());

    assertNull(result.getCats(), "Массив должен быть пуст из-за ошибки в файле");
    assertEquals(FileConfigReader.ErrorCode.INCORRECT_PARAMETERS_COUNT, result.getErrorCode(), "Ожидается код INCORRECT_PARAMETERS_COUNT.");

    tempFile.delete();
  }

  @Test
  void testFileConfigReaderWithSomeInvalidData() throws IOException {
    File tempFile = File.createTempFile("CatsTestErrors", ".txt", new File("."));
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
      writer.write("Барсик 5.0 75\n");
      writer.write("Мурзик x.y 80\n");  // INVALID_DATA_FORMAT
      writer.write("Рыжик 6.2 50\n");
    }

    FileConfigReader.Result result = FileConfigReader.parseCats(tempFile.getName());

    assertNull(result.getCats(), "Массив должен быть пуст из-за ошибки в файле");
    assertEquals(FileConfigReader.ErrorCode.INVALID_DATA_FORMAT, result.getErrorCode(), "Ожидается код INVALID_DATA_FORMAT.");

    tempFile.delete();
  }
}
