import java.awt.image.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;
import java.util.logging.Logger;

public class FormatConverter {

  private static final Logger logger = Logger.getLogger(FormatConverter.class.getName());

  public static void convertImageFormat(String path, String sourceFormat, String targetFormat) {
    File root = new File(path + "/");
    // Взято с https://stackoverflow.com/questions/2102952/listing-files-in-a-directory-matching-a-pattern-in-java
    File[] sourceFormatFiles = root.listFiles((dir, name) -> name.endsWith("." + sourceFormat));

    if (sourceFormatFiles == null || sourceFormatFiles.length == 0) {
      logger.info("В каталоге нет файлов с расширением ." + sourceFormat);
      return;
    }

    for (File file : sourceFormatFiles) {
      try {
        BufferedImage img = ImageIO.read(file);
        String newFileName = file.getName().replaceAll("\\." + sourceFormat + "$", "." + targetFormat);
        ImageIO.write(img, targetFormat, new File(path + "/" + newFileName));
        logger.info(file.getName() + " сконвертирован в " + newFileName);
      } catch (Exception e) {
        logger.info("Ошибка конвертации файла: " + file.getName());
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String path = "../images";
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите исходный формат:");
    String sourceFormat = scanner.nextLine();
    System.out.println("Введите необходмый формат:");
    String targetFormat = scanner.nextLine();
    scanner.close();

    for (int i = 1; i <= 4; i++) {
      BufferedImage newImage = new BufferedImage(800,600, BufferedImage.TYPE_INT_RGB);
      ImageIO.write(newImage, sourceFormat, new File(path + "/image" + i + "." + sourceFormat));
      logger.info("Файл изображения " + i + " создан");
    }

    convertImageFormat(path, sourceFormat, targetFormat);

    logger.info("\nКонвертация завершена");
  }
}