import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class TwoLists {

  public static List<List<String>> returnLists(String directoryPath, String fileExtension, boolean includeSubdirectories) throws IllegalArgumentException, IOException {
    // Создаю два списка: для файлов и для каталогов
    List<String> filesList = new ArrayList<>();
    List<String> subdirectoriesList = new ArrayList<>();

    // Использую Path и Files вместо File для более удобной и современной работы с каталогами и файлами
    Path directory = Paths.get(directoryPath);
    if (!Files.exists(directory) || !Files.isDirectory(directory))
      throw new IllegalArgumentException("Каталога с таким именем не существует или файл не является каталогом: " + directoryPath);

    // try-with-resources гарантирует закрытие потока после завершения блока
    try (Stream<Path> stream = Files.walk(directory, 1)) { // Максимальная глубина 1 (первый уровень вложенности)
      stream.forEach(path -> {
        // https://javarush.com/groups/posts/2203-stream-api
        if (Files.isRegularFile(path) && path.toString().endsWith("." + fileExtension)) {
          filesList.add(path.toString());
        } else if (Files.isDirectory(path) && !path.equals(directory)) { // проверка !path.equals(directory) нужна для того, чтобы при рекурсивном вызове метода не проверять ещё раз основной каталог
          subdirectoriesList.add(path.toString());
          if (includeSubdirectories) {
            try {
              List<List<String>> subdirectoryResult = returnLists(path.toString(), fileExtension, false);
              filesList.addAll(subdirectoryResult.get(0));
              subdirectoriesList.addAll(subdirectoryResult.get(1));
            } catch (IOException e) {
              e.printStackTrace(); // Обработка ошибок ввода-вывода
            }
          }
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Заполнение списка с результатами
    List<List<String>> resultList = new ArrayList<>();
    resultList.add(filesList);
    resultList.add(subdirectoriesList);

    return resultList;
  }

  public static void main(String[] args) {
    String directoryPath = "TestDir";
    String fileExtension = "txt";
    boolean includeSubdirectories = true;

    try {
      List<List<String>> result = returnLists(directoryPath, fileExtension, includeSubdirectories);
      List<String> fileList = result.get(0);
      List<String> subdirectoryList = result.get(1);

      System.out.println("Файлов с расширением '." + fileExtension + "':");
      fileList.forEach(System.out::println);

      System.out.println("\nПодкаталогов 1 уровня вложенности:");
      subdirectoryList.forEach(System.out::println);

    } catch (IllegalArgumentException | IOException e) {
      System.err.println("Ошибка: " + e.getMessage());
    }
  }
}