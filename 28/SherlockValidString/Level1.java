public class Level1 {
  public static boolean SherlockValidString(String s) {
    int[] frequencies = new int[26];
    for (char c : s.toCharArray()) {
      frequencies[c - 'a']++;
    }

    int minFreq = Integer.MAX_VALUE;
    int maxFreq = 0;
    for (int freq : frequencies) {
      if (freq > 0) {
        minFreq = Math.min(minFreq, freq);
        maxFreq = Math.max(maxFreq, freq);
      }
    }

    if (minFreq == maxFreq) {
      return true;
    }

    int minCount = 0;
    int maxCount = 0;
    for (int freq : frequencies) {
      if (freq == minFreq)
        minCount++;
      if (freq == maxFreq)
        maxCount++;
    }

    return (maxFreq - minFreq == 1 && maxCount == 1) ||
            (minFreq == 1 && minCount == 1);
  }
}
