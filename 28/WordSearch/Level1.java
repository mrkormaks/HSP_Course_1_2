import java.util.ArrayList;
import java.util.List;

public class Level1 {
    public static int[] WordSearch(int length, String s, String subs) {
        List<Integer> resultList = new ArrayList<>();
        List<String> wordsList = new ArrayList<>();
        boolean subsWasFound = false;

        while (s.length() > length) {
            int lastSpaceIndex = 0;

            if (s.charAt(length - 1) != ' ') {
                for (int i = 0; i < length; i++) {
                    if (s.charAt(i) == ' ') {
                        lastSpaceIndex = i;
                    }
                }

                if (lastSpaceIndex > 0) {
                    wordsList.add(s.substring(0, lastSpaceIndex));
                    s = s.substring(lastSpaceIndex + 1);
                } else {
                    wordsList.add(s.substring(0, length));
                    s = s.substring(length);
                }
            } else {
                wordsList.add(s.substring(0, length));
                s = s.substring(length + 1);
            }
        }

        wordsList.add(s);

        for (String line : wordsList) {
            String[] comparisonList = line.split(" ");
            for (String word : comparisonList) {
                if (word.equals(subs)) {
                    subsWasFound = true;
                    break;
                }
            }
            if (subsWasFound) {
                resultList.add(1);
            } else {
                resultList.add(0);
            }
            subsWasFound = false;
        }

        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }

        return resultArray;
    }
}
