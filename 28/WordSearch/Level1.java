import java.util.*;

public class Level1 {
    public static int[] WordSearch(int length, String s, String subs) {
        List<Integer> resultList = new ArrayList<>();
        String word = "";
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
                    word = s.substring(0, lastSpaceIndex);
                } else {
                    word = s.substring(0, length);
                }
            } else {
                word = s.substring(0, length);
            }

            if (s.charAt(length) == ' ') {
                word = s.substring(0, length);
            }

            wordsList.add(word);

            if (s.contains(word)) {
                s = s.replace(word, "");
            }
            if (s.charAt(0) == ' ') {
                s = s.substring(1);
            }
        }

        wordsList.add(s);

        for (String w : wordsList) {
            List<String> comparisonList = Arrays.asList(w.split("\\s+"));

            for (String comp : comparisonList) {
                if (comp.equals(subs)) {
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

        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }
}
