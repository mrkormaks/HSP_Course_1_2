import java.util.*;

public class Level1 {
  public static boolean Football(int[] F, int N) {
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        swap(F, i, j);
        if (isSorted(F))
          return true;
          
        swap(F, i, j);
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        reverse(F, i, j);
        if (isSorted(F)) 
          return true;

        reverse(F, i, j);
      }
    }

    return false;
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private static void reverse(int[] arr, int start, int end) {
    for (int i = 0; i <= (end - start) / 2; i++) {
      swap(arr, start + i, end - i);
    }
  }

  private static boolean isSorted(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < arr[i-1])
        return false;
    }

    return true;
  }
}
