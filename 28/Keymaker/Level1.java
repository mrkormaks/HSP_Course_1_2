import java.util.*;

public class Level1  {
  public static String Keymaker(int k) {
    boolean[] doors = new boolean[k];
    
    for (int step = 1; step <= k; step++) {
      for (int doorIndex = step - 1; doorIndex < k; doorIndex += step) {
        doors[doorIndex] = !doors[doorIndex];
      }
    }
    
    StringBuilder result = new StringBuilder(k);
    for (boolean door : doors) {
      result.append(door ? '1' : '0');
    }
    
    return result.toString();
  }
}
