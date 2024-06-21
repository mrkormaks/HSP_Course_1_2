import java.util.Scanner;
import java.util.logging.Logger;
import mathoperations.MathUtils;
import converter.HexConverter;

public class App {
  private static final Logger logger = Logger.getLogger(App.class.getName());

  public static void main(String[] args) {
    logger.info("Application started");

    Scanner in = new Scanner(System.in);
    System.out.println("Введите любое число: ");
    int number = in.nextInt();
    logger.info("User entered number: " + number);

    // Проверка числа на четность
    boolean isEven = MathUtils.isEven(number);
    if (isEven) {
      logger.info("Number is even");
    } else {
      logger.info("Number is odd");
    }

    // Используем метод из класса MathUtils для возведения в квадрат
    int squaredNumber = MathUtils.squareInt(number);
    logger.info("Number squared: " + squaredNumber);

    // Используем метод из класса HexConverter для перевода в шестнадцатеричное представление
    String hexNumber = HexConverter.decToHex(number);
    logger.info("Number in hexadecimal: " + hexNumber);

    // Используем метод из HexConverter, который включает использование MathUtils
    String hexNumberDirect = HexConverter.squareIntToHex(number);
    logger.info("Number squared and converted to hexadecimal: " + hexNumberDirect);

    // Ассерт для проверки корректности работы метода squareInt
    assert squaredNumber == number * number : "squareInt method failed";

    in.close();
    logger.info("Application finished");
  }
}