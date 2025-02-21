import java.util.*;

public class Level1 {
  public static int Unmanned(int L, int N, int[][] track) {
    int currentTime = 0;
    int currentPosition = 0;

    for (int i = 0; i < N; i++) {
      int[] trafficLight = track[i];
      int position = trafficLight[0];

      if (position > L) {
        break;
      }

      int redDuration = trafficLight[1];
      int greenDuration = trafficLight[2];
      int cycleDuration = redDuration + greenDuration;

      currentTime += position - currentPosition;
      currentPosition = position;

      int cycleTime = currentTime % cycleDuration;
      if (cycleTime < redDuration) {
        currentTime += redDuration - cycleTime;
      }
    }

    currentTime += L - currentPosition;

    return currentTime;
  }
}
