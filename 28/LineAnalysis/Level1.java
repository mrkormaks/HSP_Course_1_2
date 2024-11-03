import java.util.*;

public class Level1 {
  public static boolean LineAnalysis(String line) {
    if (line == null || line.isEmpty() || !line.startsWith("*") || !line.endsWith("*")) {
      return false;
    }
    
    if (line.length() == 1) {
      return true;
    }
    if (line.matches("\\*+")) {
      return true;
    }
    
    int firstStarIndex = 0;
    int secondStarIndex = line.indexOf('*', 1);
    if (secondStarIndex == -1) {
      return false;
    }

    String pattern = line.substring(firstStarIndex + 1, secondStarIndex);
    
    int currentPos = secondStarIndex;
    while (currentPos < line.length() - 1) {
      int nextStarIndex = line.indexOf('*', currentPos + 1);
      if (nextStarIndex == -1) {
        return false;
      }

      String currentSegment = line.substring(currentPos + 1, nextStarIndex);
      if (!currentSegment.equals(pattern)) {
        return false;
      }

      currentPos = nextStarIndex;
    }

    return true;
  }
}
