import java.util.*;

public class Level1 {
  public static String[] TreeOfLife(int H, int W, int N, String[] tree) {
    int[][] grid = new int[H][W];
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        grid[i][j] = tree[i].charAt(j) == '+' ? 1 : 0;
      }
    }

    for (int year = 1; year <= N; year++) {
      if (year % 2 == 1) {
        grow(grid);
      } else {
        grid = destroy(grid);
      }
    }

    return convertToText(grid);
  }

  private static void grow(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        grid[i][j] = grid[i][j] == 0 ? 1 : grid[i][j] + 1;
      }
    }
  }

  private static int[][] destroy(int[][] grid) {
    int H = grid.length, W = grid[0].length;
    int[][] nextGrid = new int[H][W];
    List<int[]> toDestroy = new ArrayList<>();

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        nextGrid[i][j] = grid[i][j] > 0 ? grid[i][j] + 1 : 0;
        if (grid[i][j] >= 3) {
          toDestroy.add(new int[]{i, j});
        }
      }
    }

    for (int[] cell : toDestroy) {
      int i = cell[0], j = cell[1];
      nextGrid[i][j] = 0;
      for (int[] dir : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
        int ni = i + dir[0], nj = j + dir[1];
        if (ni >= 0 && ni < H && nj >= 0 && nj < W) {
          nextGrid[ni][nj] = 0;
        }
      }
    }

    return nextGrid;
  }

  private static String[] convertToText(int[][] grid) {
    String[] result = new String[grid.length];
    for (int i = 0; i < grid.length; i++) {
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < grid[0].length; j++) {
        sb.append(grid[i][j] > 0 ? '+' : '.');
      }
      result[i] = sb.toString();
    }
    return result;
  }
}
