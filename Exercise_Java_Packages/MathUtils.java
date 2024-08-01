package mathoperations;

public class MathUtils {
  public static int squareInt(Integer number) {
    if (number == null) {
      throw new NullPointerException("Число не может быть null");
    }
    return number * number;
  }

  public static boolean isEven(Integer number) {
    if (number == null) {
      throw new NullPointerException("Число не может быть null");
    }
    return number % 2 == 0;
  }
}
