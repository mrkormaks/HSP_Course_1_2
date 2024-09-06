import java.util.*;

public class Level1 {
  public static int squirrel(int N) {
    long factorial = 1;
    while (N > 1) {
      factorial *= N;
      N--;
    }
    
    long result = factorial;
    while (result > 9) {
      result /= 10;
    }

    return (int)result;
  }
}
