import java.util.Arrays; // воспользовался StreamAPI

public class Level1 {
  public static String MassVote(int N, int[] Votes) {
    int totalVotes = Arrays.stream(Votes).sum();
    int maxVotes = Arrays.stream(Votes).max().orElse(0);

    long countOfWinners = Arrays.stream(Votes).filter(v -> v == maxVotes).count();

    if (countOfWinners > 1) {
      return "no winner";
    }

    int winnerIndex = Arrays.asList(Arrays.stream(Votes).boxed().toArray(Integer[]::new)).indexOf(maxVotes);
    double winnerPercentage = (double) maxVotes / totalVotes * 100;

    if (winnerPercentage > 50) {
      return "majority winner " + (winnerIndex + 1);
    } else {
      return "minority winner " + (winnerIndex + 1);
    }
  }
}