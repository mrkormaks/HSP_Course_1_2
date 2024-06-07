import java.util.Scanner;
import mathoperations.MathUtils;
import converter.HexConverter;

public class App {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Введите любое число: ");
    int number = in.nextInt();

    // Используем метод из класса MathUtils для возведения в квадрат
    int squaredNumber = MathUtils.squareInt(number);
    System.out.println("Your number squared: " + squaredNumber);

    // Используем метод из класса HexConverter для перевода в шестнадцатеричное представление
    String hexNumber = HexConverter.decToHex(squaredNumber);
    System.out.println("Your squared number in hexadecimal: " + hexNumber);

    in.close();
  }
}