import java.util.*;

public class Level1 {
  public static int[] UFO(int N, int[] data, boolean octal) {
    int[] result = new int[N];

    for (int i = 0; i < N; i++) {
      if (octal) {
        result[i] = Integer.parseInt(String.valueOf(data[i]), 8);
        continue;
      }
      
      result[i] = Integer.parseInt(String.valueOf(data[i]), 16);
    }

    return result;
  }
}
