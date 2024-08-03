package converter;

import mathoperations.MathUtils;

public class HexConverter {
  public static String decToHex(Integer number) {
    assert number != null : "Число не может быть null";
    return Integer.toHexString(number).toUpperCase();
  }

  public static String squareIntToHex(Integer number) {
    assert number != null : "Число не может быть null";
    int squaredNumber = MathUtils.squareInt(number);
    return decToHex(squaredNumber);
  }
}
