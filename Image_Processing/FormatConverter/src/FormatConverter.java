import java.awt.image.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class FormatConverter {

  public static void convertImageFormat(String path, String sourceFormat, String targetFormat) {
    File root = new File(path + "/");
    File[] sourceFormatFiles = root.listFiles(new FilenameFilter() { // Взято с https://stackoverflow.com/questions/2102952/listing-files-in-a-directory-matching-a-pattern-in-java
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith("." + sourceFormat);
      }
    });

    if (sourceFormatFiles == null || sourceFormatFiles.length == 0) {
      System.out.println("В каталоге нет файлов с расширением ." + sourceFormat);
      return;
    }

    for (File file : sourceFormatFiles) {
      try {
        BufferedImage img = ImageIO.read(file);
        String newFileName = file.getName().replaceAll("\\." + sourceFormat + "$", "." + targetFormat);
        ImageIO.write(img, targetFormat, new File(path + "/" + newFileName));
        System.out.println(file.getName() + " сконвертирован в " + newFileName);
      } catch (Exception e) {
        System.out.println("Ошибка конвертации файла: " + file.getName());
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
      System.out.println("Файл изображения " + i + " создан");
    }

    convertImageFormat(path, sourceFormat, targetFormat);

    System.out.println("\nКонвертация завершена");
  }
}