import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FormatConverterWithDrawingTest {

  @Test
  public void testDrawRect() throws IOException {
    // Создание тестового изображения
    BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);

    // Запоминаем цвет пикселя до рисования
    int preDrawColor = img.getRGB(400, 300);

    // Вызов метода drawRect
    FormatConverterWithDrawing.drawRect(img);

    // Проверка изменения пикселя после рисования
    int postDrawColor = img.getRGB(400, 300);

    // Проверка, что пиксель изменился (в центре должно быть что-то нарисовано)
    assertNotEquals(preDrawColor, postDrawColor);
  }
}
