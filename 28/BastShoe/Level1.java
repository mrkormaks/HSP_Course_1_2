import java.util.Stack;

public class Level1 {
  private static StringBuilder currentString = new StringBuilder();
  private static Stack<String> undoStack = new Stack<>();
  private static Stack<String> redoStack = new Stack<>();
  private static boolean isFirstOperation = true;

  public static String BastShoe(String command) {
    if (command == null || command.isEmpty()) {
      return currentString.toString();
    }

    String[] parts = command.split(" ", 2);
    int operationCode;

    try {
      operationCode = Integer.parseInt(parts[0]);
    } catch (NumberFormatException e) {
      return currentString.toString();
    }

    String parameter = parts.length > 1 ? parts[1] : null;

    switch (operationCode) {
      case 1:
        if (parameter != null) {
          performAdd(parameter);
        }
        break;
      case 2:
        if (parameter != null) {
          try {
            int count = Integer.parseInt(parameter);
            performDelete(count);
          } catch (NumberFormatException e) {
            //
          }
        }
        break;
      case 3:
        if (parameter != null) {
          try {
            int index = Integer.parseInt(parameter);
            return performGetChar(index);
          } catch (NumberFormatException e) {
            return "";
          }
        }
        break;
      case 4:
        performUndo();
        break;
      case 5:
        performRedo();
        break;
      default:
        return currentString.toString();
    }

    return currentString.toString();
  }

  private static void performAdd(String s) {
    if (!isFirstOperation) {
      undoStack.push(currentString.toString());
      redoStack.clear();
    }
      currentString.append(s);
      isFirstOperation = false;
  }

  private static void performDelete(int count) {
    if (!isFirstOperation) {
      undoStack.push(currentString.toString());
      redoStack.clear();
    }
    int lengthToDelete = Math.min(count, currentString.length());
    currentString.delete(currentString.length() - lengthToDelete, currentString.length());
    isFirstOperation = false;
  }

  private static String performGetChar(int index) {
    if (index < 0 || index >= currentString.length()) {
      return "";
    }
    return String.valueOf(currentString.charAt(index));
  }

  private static void performUndo() {
    if (!undoStack.isEmpty()) {
      redoStack.push(currentString.toString());
      currentString.setLength(0);
      currentString.append(undoStack.pop());
    }
  }

  private static void performRedo() {
    if (!redoStack.isEmpty()) {
      undoStack.push(currentString.toString());
      currentString.setLength(0);
      currentString.append(redoStack.pop());
    }
  }
}
