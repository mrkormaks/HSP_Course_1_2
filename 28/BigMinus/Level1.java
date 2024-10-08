import java.util.*;

public class Level1 {
  public static String BigMinus(String s1, String s2) {
    if (isSmaller(s1, s2)) {
      String temp = s1;
      s1 = s2;
      s2 = temp;
    }

    int length1 = s1.length();
    int length2 = s2.length();
    int lengthDifference = length1 - length2;
    StringBuilder result = new StringBuilder();
    int borrow = 0;

    for (int i = length2 - 1; i >= 0; i--) {
      int digit1 = s1.charAt(i + lengthDifference) - '0';
      int digit2 = s2.charAt(i) - '0';
      int difference = digit1 - digit2 - borrow;

      if (difference < 0) {
        difference += 10;
        borrow = 1;
      } else {
        borrow = 0;
      }
      result.insert(0, difference);
    }

    for (int i = lengthDifference - 1; i >= 0; i--) {
      if (s1.charAt(i) == '0' && borrow > 0) {
        result.insert(0, '9');
        continue;
      }
      int digit = (s1.charAt(i) - '0') - borrow;
      if (i > 0 || digit > 0) {
        result.insert(0, digit);
      }
      borrow = 0;
    }

    while (result.length() > 1 && result.charAt(0) == '0') {
      result.deleteCharAt(0);
    }

    return result.length() == 0 ? "0" : result.toString();
  }

  private static boolean isSmaller(String s1, String s2) {
    int length1 = s1.length();
    int length2 = s2.length();

    if (length1 < length2) return true;
    if (length2 < length1) return false;

    for (int i = 0; i < length1; i++) {
      if (s1.charAt(i) < s2.charAt(i)) return true;
      if (s1.charAt(i) > s2.charAt(i)) return false;
    }
    return false;
  }
}
