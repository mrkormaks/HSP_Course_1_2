import java.util.*;

public class Level1 {
  public static int[] MadMax(int N, int[] Tele) {
    boolean bubbleSort = true;
    while (bubbleSort) {
      bubbleSort = false;
      for (int i = 0; i < Tele.length - 1; i++) {
        if (Tele[i] > Tele[i + 1]) {
          int temp = Tele[i];
          Tele[i] = Tele[i + 1];
          Tele[i + 1] = temp;
          bubbleSort = true;
        }
      }
    }

    int maxVal = Tele[N - 1];

    int[] newTele = new int[N - 1];
    System.arraycopy(Tele, 0, newTele, 0, N - 1);

    int halfSize = N / 2;

    List<Integer> leftList = new ArrayList<>();
    for (int i = 0; i < halfSize; i++) {
      leftList.add(newTele[i]);
    }
    leftList.add(maxVal);

    List<Integer> rightList = new ArrayList<>();
    for (int i = halfSize; i < newTele.length; i++) {
      rightList.add(newTele[i]);
    }
    Collections.reverse(rightList);

    List<Integer> impulseList = new ArrayList<>(leftList);
    impulseList.addAll(rightList);
    
    int[] result = new int[impulseList.size()];
    for (int i = 0; i < impulseList.size(); i++) {
      result[i] = impulseList.get(i);
    }

    return result;
  }
}