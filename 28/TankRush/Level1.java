import java.util.*;

public class Level1 {
  public static boolean TankRush(int H1, int W1, String S1, int H2, int W2, String S2) {
    String[] map1 = S1.split(" ");
    String[] map2 = S2.split(" ");

    return searchPattern(map1, map2, H1, H2, W1, W2);
  }

  private static boolean searchPattern(String[] map1, String[] map2, int H1, int H2, int W1, int W2) {
    for (int i = 0; i <= H1 - H2; i++) {
      for (int j = 0; j <= W1 - W2; j++) {
        if (isMatchFound(map1, map2, i, j, H2, W2)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean isMatchFound(String[] map1, String[] map2, int startRow, int startCol, int H2, int W2) {
    for (int x = 0; x < H2; x++) {
      if (!map1[startRow + x].substring(startCol, startCol + W2).equals(map2[x])) {
        return false;
      }
    }
    return true;
  }
}
