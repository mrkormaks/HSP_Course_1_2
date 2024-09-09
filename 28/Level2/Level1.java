<<<<<<< HEAD:28/Level2/Level1.java
=======
import java.util.*;

>>>>>>> 11971180b8be31b12e29400704a7869b8d33ed94:28/Level2/Level2.java
public class Level1 {
  public static int odometer(int[] oksana) {
    int distance = 0;
    int[] list1 = new int[(oksana.length / 2) + 1];
    int[] list2 = new int[oksana.length / 2];
    int[] list3 = new int[oksana.length / 2];

    int j = 0;
    int k = 0;
    for (int i = 0; i < oksana.length; i++) {
      if (i % 2 == 0) {
        list1[j++] = oksana[i];
      } else {
        list2[k++] = oksana[i];
      }
    }

    list3[0] = list2[0];
    for (int i = 1; i < list2.length; i++) {
      list3[i] = list2[i] - list2[i - 1];
    }

    for (int i = 0; i < list3.length; i++) {
      distance += list1[i] * list3[i];
    }

    return distance;
  }
}
