import java.util.*;

public class Level1 {
  public static int SumOfThe(int N, int[] data) {
    int totalSum = Arrays.stream(data).sum();
    for (int num : data) {
      if (totalSum - num == num) {
        return num;
      }
    }
    return 0;
  }
}