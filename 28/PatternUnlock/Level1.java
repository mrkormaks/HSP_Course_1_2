import java.util.*;

public class Level1 {
  public static String PatternUnlock(int N, int[] hits) {
    int[][] keyboardList = {
            {6, 1, 9},
            {5, 2, 8},
            {4, 3, 7}
    };

    int x_prev = -1;
    int y_prev = -1;
    double result = 0;

    for (int i = 0; i < N; i++) {
      int x_curr = -1;
      int y_curr = -1;

      for (int x = 0; x < keyboardList.length; x++) {
        for (int y = 0; y < keyboardList[x].length; y++) {
          if (hits[i] == keyboardList[x][y]) {
            x_curr = x;
            y_curr = y;
            break;
          }
        }
      }

      if (i > 0) {
        int dx = Math.abs(x_curr - x_prev);
        int dy = Math.abs(y_curr - y_prev);

        if (dx == 1 && dy == 1) {
          result += Math.sqrt(2);
        } else {
          result += 1;
        }
      }

      x_prev = x_curr;
      y_prev = y_curr;
    }

    String res = String.valueOf((int) Math.round(result * 100000));
    return res.replace("0", "");
  }
}