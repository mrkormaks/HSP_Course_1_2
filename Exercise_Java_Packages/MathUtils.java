package mathoperations;

public class MathUtils {
  public static int squareInt(Integer number) {
    assert number != null : "Число не может быть null";
    return number * number;
  }

  public static boolean isEven(Integer number) {
    assert number != null : "Число не может быть null";
    return number % 2 == 0;
  }
}