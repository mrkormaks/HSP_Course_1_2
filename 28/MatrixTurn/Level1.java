import java.util.*;

public class Level1 {
  public static void MatrixTurn(String[] matrix, int M, int N, int T) {
    int layers = Math.min(M, N) / 2;
    char[][] charMatrix = new char[M][N];

    for (int i = 0; i < M; i++) {
      charMatrix[i] = matrix[i].toCharArray();
    }

    for (int layer = 0; layer < layers; layer++) {
      List<Character> elements = new ArrayList<>();

      for (int col = layer; col < N - layer; col++) {
        elements.add(charMatrix[layer][col]);
      }
      for (int row = layer + 1; row < M - layer; row++) {
        elements.add(charMatrix[row][N - layer - 1]);
      }
      for (int col = N - layer - 2; col >= layer; col--) {
        elements.add(charMatrix[M - layer - 1][col]);
      }
      for (int row = M - layer - 2; row > layer; row--) {
        elements.add(charMatrix[row][layer]);
      }

      int len = elements.size();
      int rotation = T % len;
      Collections.rotate(elements, rotation);

      int index = 0;

      for (int col = layer; col < N - layer; col++) {
        charMatrix[layer][col] = elements.get(index++);
      }
      for (int row = layer + 1; row < M - layer; row++) {
        charMatrix[row][N - layer - 1] = elements.get(index++);
      }
      for (int col = N - layer - 2; col >= layer; col--) {
        charMatrix[M - layer - 1][col] = elements.get(index++);
      }
      for (int row = M - layer - 2; row > layer; row--) {
        charMatrix[row][layer] = elements.get(index++);
      }
    }

    for (int i = 0; i < M; i++) {
      matrix[i] = new String(charMatrix[i]);
    }
  }
}
