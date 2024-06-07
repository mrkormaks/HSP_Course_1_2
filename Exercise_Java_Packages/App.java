import java.util.Scanner;
import mathoperations.MathUtils;
import converter.HexConverter;

public class App {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Введите любое число: ");
    int number = in.nextInt();

    // Проверка числа на четность
    boolean isEven = MathUtils.isEven(number);
    if (isEven) {
      System.out.println("Your number is even.");
    } else {
      System.out.println("Your number is odd.");
    }

    // Используем метод из класса MathUtils для возведения в квадрат
    int squaredNumber = MathUtils.squareInt(number);
    System.out.println("Your number squared: " + squaredNumber);

    // Используем метод из класса HexConverter для перевода в шестнадцатеричное представление
    String hexNumber = HexConverter.decToHex(number);
    System.out.println("Your number in hexadecimal: " + hexNumber);
    
    // Используем метод из HexConverter, который включает использование MathUtils
    String hexNumberDirect = HexConverter.squareIntToHex(number);
    System.out.println("Your number squared and converted to hexadecimal directly: " + hexNumberDirect);

    in.close();
  }
}
