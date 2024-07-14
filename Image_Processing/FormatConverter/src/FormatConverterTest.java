import org.junit.jupiter.api.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

public class FormatConverterTest {

  private final String testDir = "../testDir";
  private final String sourceFormat = "bmp";
  private final String targetFormat = "png";

  @BeforeEach
  public void setUp() throws Exception {
    // Создаем тестовую директорию, если она не существует
    File dir = new File(testDir);
    if (!dir.exists()) {
      dir.mkdirs();
    }

    // Создаем тестовые изображения
    for (int i = 1; i <= 4; i++) {
      BufferedImage newImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
      ImageIO.write(newImage, sourceFormat, new File(testDir + "/image" + i + "." + sourceFormat));
    }
  }

  @AfterEach
  public void tearDown() throws Exception {
    // Удаляем все тестовые файлы
    File dir = new File(testDir);
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
    // Выполняем конвертацию
    FormatConverter.convertImageFormat(testDir, sourceFormat, targetFormat);

    // Проверяем, что файлы с исходным форматом были сконвертированы
    for (int i = 1; i <= 4; i++) {
      File sourceFile = new File(testDir + "/image" + i + "." + sourceFormat);
      File targetFile = new File(testDir + "/image" + i + "." + targetFormat);

      assertTrue(targetFile.exists(),"Target file should exist: " + targetFile.getName());

      try {
        BufferedImage img = ImageIO.read(targetFile);
        assertNotNull(img,"Target image should be readable: " + targetFile.getName());
      } catch (Exception e) {
        fail("Exception thrown while reading target file: " + targetFile.getName());
      }
    }
  }
}