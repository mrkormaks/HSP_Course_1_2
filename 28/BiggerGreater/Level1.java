public class Level1 {
  public static String BiggerGreater(String input) {
    if (input == null || input.length() < 2) {
      return "";
    }

    char[] arr = input.toCharArray();
    int n = arr.length;

    int i = n - 2;
    while (i >= 0 && arr[i] >= arr[i + 1]) {
      i--;
    }

    if (i == -1) {
      return "";
    }

    int j = n - 1;
    while (arr[j] <= arr[i]) {
      j--;
    }

    swap(arr, i, j);
    reverse(arr, i + 1, n - 1);

    return new String(arr);
  }

  private static void swap(char[] arr, int i, int j) {
    char temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private static void reverse(char[] arr, int start, int end) {
    while (start < end) {
      swap(arr, start, end);
      start++;
      end--;
    }
  }
}
