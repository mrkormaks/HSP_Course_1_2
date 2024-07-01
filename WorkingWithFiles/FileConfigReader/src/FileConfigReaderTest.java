import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileConfigReaderTest {

  @Test
  void testFileConfigReader() throws IOException {
    File tempFile = File.createTempFile("CatsTest", ".txt");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
      writer.write("Барсик 5.0 75\n");
      writer.write("Мурзик 4.5 80\n");
      writer.write("Рыжик 6.2 70\n");
    }

    ArrayList<Cat> cats = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(tempFile))) {
      String line;
      while ((line = br.readLine()) != null) {
        try {
          String[] parts = line.split(" ");
          if (parts.length != 3) {
            throw new IllegalArgumentException("Некорректное количество параметров.");
          }
          String name = parts[0];
          double weight = Double.parseDouble(parts[1]);
          int purrFrequency = Integer.parseInt(parts[2]);
          cats.add(new Cat(name, weight, purrFrequency));
        } catch (Exception e) {
          System.out.println("Ошибка обработки строки: " + line);
          e.printStackTrace();
        }
      }
    }

    assertEquals(3, cats.size(), "Должно быть создано 3 объекта Cat");

    assertEquals("Барсик", cats.get(0).name);
    assertEquals(5.0, cats.get(0).weight, 0.01);
    assertEquals(75, cats.get(0).purrFrequency);

    assertEquals("Мурзик", cats.get(1).name);
    assertEquals(4.5, cats.get(1).weight, 0.01);
    assertEquals(80, cats.get(1).purrFrequency);

    assertEquals("Рыжик", cats.get(2).name);
    assertEquals(6.2, cats.get(2).weight, 0.01);
    assertEquals(70, cats.get(2).purrFrequency);

    tempFile.delete();
  }
}
