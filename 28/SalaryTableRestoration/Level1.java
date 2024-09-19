import java.util.*;

public class Level1 {
  public static int[] SynchronizingTables(int N, int[] ids, int[] salary) {
    List<Integer> correctSalaryList = new ArrayList<>();
    int[] idsSorted = ids.clone();
    int[] salarySorted = salary.clone();

    Arrays.sort(idsSorted);
    Arrays.sort(salarySorted);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (idsSorted[j] == ids[i]) {
          correctSalaryList.add(salarySorted[j]);
          break;
        }
      }
    }

    return correctSalaryList.stream().mapToInt(Integer::intValue).toArray();
  }
}