import java.util.*;

public class Level1 {
  public static int ConquestCampaign(int N, int M, int L, int[] battalion) {
    int days = 1;
    int count = 0;
    int[][] fieldsList = new int[N][M];

    for (int c = 0; c < L; c++) {
      int row = battalion[2 * c] - 1;
      int col = battalion[2 * c + 1] - 1;
      fieldsList[row][col] = 1;
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (fieldsList[i][j] < 1) {
          count++;
        }
      }
    }

    while (count > 0) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (fieldsList[i][j] == 1) {
            fieldsList[i][j]++;
          }
        }
      }

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (fieldsList[i][j] >= 2) {
            if (i - 1 >= 0) {
              fieldsList[i - 1][j] = 1;
            }
            if (i + 1 < N) {
              fieldsList[i + 1][j] = 1;
            }
            if (j - 1 >= 0) {
              fieldsList[i][j - 1] = 1;
            }
            if (j + 1 < M) {
              fieldsList[i][j + 1] = 1;
            }
          }
        }
      }

      days++;
      count = 0;

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (fieldsList[i][j] < 1) {
            count++;
          }
        }
      }
    }

    return days;
  }
}