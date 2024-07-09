import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class ZipFileUpdater {

  // Метод для добавления файлов в существующий архив
  public static void addFilesToExistingZip(String zipFilePath, String[] filesToAdd) throws IOException {
    Path tempFile = Files.createTempFile(Paths.get(zipFilePath).getFileName().toString(), null); // Создаём временный файл
    Files.delete(tempFile); // Удаляем временный файл для использования его имени

    Path originalFile = Paths.get(zipFilePath); // Исходный файл архива
    boolean renameOk = Files.move(originalFile, tempFile, StandardCopyOption.REPLACE_EXISTING) != null; // Переименовываем оригинальный файл архива
    if (!renameOk) {
      throw new RuntimeException("Не удаётся переименовать файл " + zipFilePath + " в " + tempFile.toAbsolutePath());
    }

    byte[] buf = new byte[1024]; // Создаём буфер для чтения данных
    try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(tempFile));
         ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(originalFile))) { // Открываем потоки для работы с архивами

      ZipEntry entry = zipInputStream.getNextEntry(); // Получаем следующую запись из временного архива
      while (entry != null) {
        String name = entry.getName(); // Получаем имя текущей записи
        boolean notInFilesToAdd = true; // Флаг, указывающий, что текущая запись не входит в список добавляемых файлов
        for (String file : filesToAdd) {
          if (file.equals(name)) {
            notInFilesToAdd = false; // Устанавливаем флаг в false, если файл уже существует в архиве
            break;
          }
        }
        if (notInFilesToAdd) {
          zipOutputStream.putNextEntry(new ZipEntry(name)); // Добавляем текущую запись в новый архив
          int len;
          while ((len = zipInputStream.read(buf)) > 0) {
            zipOutputStream.write(buf, 0, len); // Записываем данные в новый архив
          }
        }
        entry = zipInputStream.getNextEntry(); // Переходим к следующей записи
      }

      for (String file : filesToAdd) {
        try (InputStream input = Files.newInputStream(Paths.get(file))) { // Открываем входной поток для текущего файла
          zipOutputStream.putNextEntry(new ZipEntry(file)); // Добавляем новую запись в архив
          int len;
          while ((len = input.read(buf)) > 0) {
            zipOutputStream.write(buf, 0, len); // Записываем данные в архив
          }
        }
      }
    }

    Files.delete(tempFile); // Удаляем временный файл
  }

  // Метод для создания пустого архива
  public static void createEmptyZip(String zipFilePath) throws IOException {
    try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)))) {
      // Создаем пустой ZIP-архив
    }
  }

  public static void main(String[] args) {
    String zipFilePath = "example.zip"; // Имя архива
    String[] filesToAdd = {"file1.log", "file2.txt", "file2.xml"}; // Массив имён файлов, которые нужно добавить

    try {
      // Создаём пустой архив
      createEmptyZip(zipFilePath);
      System.out.println("Пустой архив успешно создан.");

      // Создаём файлы, которые будут добавлены в архив
      for (String fileName : filesToAdd) {
        Files.write(Paths.get(fileName), ("Содержимое файла " + fileName).getBytes());
      }

      // Добавляем файлы в архив
      addFilesToExistingZip(zipFilePath, filesToAdd);
      System.out.println("Файлы успешно добавлены в архив.");
    } catch (IOException e) {
      e.printStackTrace(); // Выводим стек трассировки исключения
    }
  }
}