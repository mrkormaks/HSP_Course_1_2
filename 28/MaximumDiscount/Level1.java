import java.util.Arrays;

public class Level1 {
  public static int MaximumDiscount(int N, int[] price) {
    Arrays.sort(price);
    for(int i = 0; i < N/2; i++) {
      int temp = price[i];
      price[i] = price[N-1-i];
      price[N-1-i] = temp;
    }

    int totalDiscount = 0;

    for(int i = 2; i < N; i += 3) {
      totalDiscount += price[i];
    }

    return totalDiscount;
  }
}