package converter;

import mathoperations.MathUtils;

public class HexConverter {
  public static String decToHex(int number) {
    return Integer.toHexString(number).toUpperCase();
  }

  public static String squareIntToHex(int number) {
    int squaredNumber = MathUtils.squareInt(number);
    return decToHex(squaredNumber);
  }
}
