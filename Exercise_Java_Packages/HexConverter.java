package converter;

import mathoperations.MathUtils;

public class HexConverter {
  public static String decToHex(Integer number) {
    if (number == null) {
      throw new NullPointerException("Число не может быть null");
    }
    return Integer.toHexString(number).toUpperCase();
  }

  public static String squareIntToHex(Integer number) {
    if (number == null) {
      throw new NullPointerException("Число не может быть null");
    }
    int squaredNumber = MathUtils.squareInt(number);
    return decToHex(squaredNumber);
  }
}
