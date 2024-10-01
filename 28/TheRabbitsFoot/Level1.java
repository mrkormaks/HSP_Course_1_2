import java.util.*;

public class Level1 {
  public static String TheRabbitsFoot(String s, boolean encode) {
    if (s.isEmpty()) {
      return "";
    }

    if (encode) {
      return encrypt(s);
    } else {
      return decrypt(s);
    }
  }

  private static String encrypt(String s) {
    s = s.replaceAll("\\s+", "");

    int n = s.length();
    int rows = (int) Math.floor(Math.sqrt(n));
    int cols = (int) Math.ceil(Math.sqrt(n));

    if (rows * cols < n) {
      rows++;
    }

    char[][] matrix = new char[rows][cols];
    int stringIndex = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (stringIndex < n) {
          matrix[i][j] = s.charAt(stringIndex++);
        } else {
          matrix[i][j] = ' ';
        }
      }
    }

    String result = "";

    for (int j = 0; j < cols; j++) {
      for (int i = 0; i < rows; i++) {
        if (matrix[i][j] != ' ') {
          result += matrix[i][j];
        }
      }
      if (j < cols - 1) {
        result += ' ';
      }
    }

    return result;
  }

  private static String decrypt(String s) {
    String[] parts = s.split(" ");
    int cols = parts.length;
    int rows = 0;

    for (String part : parts) {
      if (part.length() > rows) {
        rows = part.length();
      }
    }

    char[][] matrix = new char[rows][cols];

    for (int j = 0; j < cols; j++) {
      for (int i = 0; i < parts[j].length(); i++) {
        matrix[i][j] = parts[j].charAt(i);
      }
    }

    String result = "";
    int totalChars = s.replace(" ", "").length();

    for (int i = 0; i < rows && result.length() < totalChars; i++) {
      for (int j = 0; j < cols && result.length() < totalChars; j++) {
        if (matrix[i][j] != 0) {
          result += matrix[i][j];
        }
      }
    }

    return result;
  }
}
