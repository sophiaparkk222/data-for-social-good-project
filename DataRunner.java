import java.util.Scanner;

public class DataRunner {
  public static void main(String[] args) {
    // no-argument constructor reads country.txt, niche.txt, and profit.txt
    UserStory story = new UserStory();

    System.out.println(story);
    System.out.println();

    System.out.println("Top 5 countries by profit:");
    String[] topCountries = story.topCountries(5);
    for (int i = 0; i < topCountries.length; i++) {
      System.out.println((i + 1) + ") " + topCountries[i]
          + " ($" + String.format("%,.2f", story.countryProfit(topCountries[i])) + ")");
    }
    System.out.println();

    System.out.println("Top 2 niches by profit:");
    String[] topNiches = story.topNiches(5);
    for (int i = 0; i < topNiches.length; i++) {
      System.out.println((i + 1) + ") " + topNiches[i]
          + " ($" + String.format("%,.2f", story.nicheProfit(topNiches[i])) + ")");
    }
  }
}
