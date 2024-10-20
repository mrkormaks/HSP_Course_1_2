public static int Unmanned(int L, int N, int[][] track) {
  int currentTime = 0;
  int currentPosition = 0;

  for (int[] trafficLight : track) {
    int position = trafficLight[0];
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