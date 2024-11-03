import java.util.*;

public class Level1 {
  public static int squirrel(int N) {
    long factorial = 1;
    while (N > 1) {
      factorial *= N;
      N--;
    }

    String result = String.valueOf(factorial);
    char lastDigit = result.charAt(result.length() - 1);

    return Integer.parseInt(String.valueOf(lastDigit));
  }
}

