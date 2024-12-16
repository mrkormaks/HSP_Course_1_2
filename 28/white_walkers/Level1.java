public class Level1 {
  public static boolean white_walkers(String village) {
    return checkVillage(village, 0, false);
  }

  private static boolean checkVillage(String village, int start, boolean hasPairs) {
    if (start >= village.length()) {
      return hasPairs;
    }

    if (!Character.isDigit(village.charAt(start))) {
      return checkVillage(village, start + 1, hasPairs);
    }

    return checkPairs(village, start, start + 1, hasPairs);
  }

  private static boolean checkPairs(String village, int first, int second, boolean hasPairs) {
    if (second >= village.length()) {
      return checkVillage(village, first + 1, hasPairs);
    }

    if (!Character.isDigit(village.charAt(second))) {
      return checkPairs(village, first, second + 1, hasPairs);
    }

    int sum = (village.charAt(first) - '0') + (village.charAt(second) - '0');
    if (sum == 10) {
      if (village.substring(first + 1, second).replaceAll("[^=]", "").length() != 3) {
        return false;
      }

      return checkPairs(village, second, second + 1, true);
    }

    return checkPairs(village, first, second + 1, hasPairs);
  }
}
