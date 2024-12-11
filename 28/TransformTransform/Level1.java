import java.util.ArrayList;
import java.util.List;

public class Level1 {
  public static boolean TransformTransform(int[] A, int N) {
    List<Integer> firstTransform = transform(A);
    List<Integer> secondTransform = transformList(firstTransform);
    int key = secondTransform.stream().mapToInt(Integer::intValue).sum();
    return key % 2 == 0;
  }

  private static List<Integer> transform(int[] A) {
    List<Integer> result = new ArrayList<>();
    int n = A.length;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - i; j++) {
        int k = i + j;
        int max = Integer.MIN_VALUE;
        for (int l = j; l <= k; l++) {
          max = Math.max(max, A[l]);
        }
        result.add(max);
      }
    }

    return result;
  }

  private static List<Integer> transformList(List<Integer> A) {
    int[] array = A.stream().mapToInt(Integer::intValue).toArray();
    return transform(array);
  }
}
