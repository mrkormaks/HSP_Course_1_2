import java.util.*;

public class Level1 {
  public static boolean MisterRobot(int N, int[] data) {
    for (int i = 0; i <= N - 3;) {
      if (hasWrongOrder(data, i)) {
        boolean sorted = false;
        for (int rot = 0; rot < 3; rot++) {
          rotateLeft(data, i);
          if (!hasWrongOrder(data, i)) {
            sorted = true;
            break;
          }
        }

        if (!sorted) {
          return false;
        }

        i = Math.max(0, i - 2);
      } else {
        i++;
      }
    }

    return isSorted(data);
  }

  private static boolean hasWrongOrder(int[] arr, int start) {
    return arr[start] > arr[start + 1] ||
            arr[start + 1] > arr[start + 2];
  }

  private static void rotateLeft(int[] arr, int start) {
    int temp = arr[start];
    arr[start] = arr[start + 1];
    arr[start + 1] = arr[start + 2];
    arr[start + 2] = temp;
  }
}
