import org.junit.jupiter.api.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

public class FormatConverterTest {

  private static final String TEST_DIR = "../testDir";
  private static final String SOURCE_FORMAT = "bmp";
  private static final String TARGET_FORMAT = "png";

  @BeforeEach
  public void setUp() throws Exception {
    // Создаем тестовую директорию, если она не существует
    File dir = new File(TEST_DIR);
    if (!dir.exists()) {
      dir.mkdirs();
    }

    // Создаем тестовые изображения
    for (int i = 1; i <= 4; i++) {
      BufferedImage newImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
      ImageIO.write(newImage, SOURCE_FORMAT, new File(TEST_DIR + "/image" + i + "." + SOURCE_FORMAT));
    }
  }

  @AfterEach
  public void tearDown() {
    // Удаляем все тестовые файлы
    File dir = new File(TEST_DIR);
    File[] files = dir.listFiles();
    if (files != null) {
      for (File file : files) {
        file.delete();
      }
    }

    dir.delete();
  }

  @Test
  public void testImageConversion() {
    // Выполняем конвертацию с учетом подкаталогов
    FormatConverter.ErrorCode result = FormatConverter.tryConvertImageFormat(TEST_DIR, SOURCE_FORMAT, TARGET_FORMAT, true);

    // Проверяем успешность конвертации
    assertEquals(FormatConverter.ErrorCode.SUCCESS, result, "Конвертация должна завершиться успешно");

    // Проверяем, что файлы с исходным форматом были сконвертированы
    for (int i = 1; i <= 4; i++) {
      File targetFile = new File(TEST_DIR + "/image" + i + "." + TARGET_FORMAT);

      assertTrue(targetFile.exists(), "Файл " + targetFile.getName() + " должен существовать");

      try {
        BufferedImage img = ImageIO.read(targetFile);
        assertNotNull(img, "Изображение " + targetFile.getName() + " должно быть читаемым");
      } catch (Exception e) {
        fail("Исключение при чтении файла: " + targetFile.getName());
      }
    }
  }
}