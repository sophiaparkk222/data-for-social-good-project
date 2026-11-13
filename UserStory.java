public class UserStory {
  // 1D arrays for country, niche, and profit
  private String[] countries;
  private String[] niches;
  private double[] profits;

  public UserStory() {
    countries = FileReader.toStringArray("country.txt");
    niches = FileReader.toStringArray("niche.txt");

    String[] profitText = FileReader.toStringArray("profit.txt");
    profits = new double[profitText.length];
    for (int i = 0; i < profitText.length; i++) {
      profits[i] = Double.parseDouble(
          profitText[i].replace("$", "").replace(",", "").trim());
    }
  }

  /*
  * Parameterized constructor
  */
  public UserStory(String[] countries, String[] niches, double[] profits) {
    this.countries = countries;
    this.niches = niches;
    this.profits = profits;
  }

  /*
  * Finds the category with the highest total profit
  */
  private String highestTotal(String[] categories, double[] values) {
    String bestCategory = "";
    double bestTotal = -1;

    for (int i = 0; i < categories.length; i++) {
      double total = 0;
      for (int j = 0; j < categories.length; j++) {
        if (categories[i].equals(categories[j])) {
          total += values[j];
        }
      }

      if (total > bestTotal) {
        bestTotal = total;
        bestCategory = categories[i];
      }
    }
    return bestCategory;
  }

  /*
  * Adds up the profit for one category
  */
  private double totalFor(String[] categories, double[] values, String target) {
    double total = 0;
    for (int i = 0; i < categories.length; i++) {
      if (categories[i].equals(target)) {
        total += values[i];
      }
    }
    return total;
  }

  /*
  * Returns an array of the distinct values in categories/no repeats
  */
  private String[] uniqueValues(String[] categories) {
    int count = 0;
    for (int i = 0; i < categories.length; i++) {
      boolean seenBefore = false;
      for (int j = 0; j < i; j++) {
        if (categories[i].equals(categories[j])) {
          seenBefore = true;
        }
      }
      if (!seenBefore) {
        count++;
      }
    }

    String[] unique = new String[count];
    int index = 0;
    for (int i = 0; i < categories.length; i++) {
      boolean seenBefore = false;
      for (int j = 0; j < i; j++) {
        if (categories[i].equals(categories[j])) {
          seenBefore = true;
        }
      }
      if (!seenBefore) {
        unique[index] = categories[i];
        index++;
      }
    }
    return unique;
  }

  /*
   * Returns the categories with the highest total profit
   */
  private String[] topCategories(String[] categories, double[] values, int n) {
    String[] unique = uniqueValues(categories);

    double[] totals = new double[unique.length];
    for (int i = 0; i < unique.length; i++) {
      totals[i] = totalFor(categories, values, unique[i]);
    }

    int count = Math.min(n, unique.length);
    String[] result = new String[count];
    boolean[] used = new boolean[unique.length];

    for (int k = 0; k < count; k++) {
      int best = -1;
      for (int i = 0; i < unique.length; i++) {
        if (!used[i] && (best == -1 || totals[i] > totals[best])) {
          best = i;
        }
      }
      used[best] = true;
      result[k] = unique[best];
    }
    return result;
  }

  public String topCountry() {
    return highestTotal(countries, profits);
  }

  public String topNiche() {
    return highestTotal(niches, profits);
  }

  public double topCountryProfit() {
    return totalFor(countries, profits, topCountry());
  }

  public double topNicheProfit() {
    return totalFor(niches, profits, topNiche());
  }

  public String[] topCountries(int n) {
    return topCategories(countries, profits, n);
  }

  public String[] topNiches(int n) {
    return topCategories(niches, profits, n);
  }

  public double countryProfit(String country) {
    return totalFor(countries, profits, country);
  }

  public double nicheProfit(String niche) {
    return totalFor(niches, profits, niche);
  }

  public String toString() {
    double sum = 0;
    for (double p : profits) {
      sum += p;
    }
    return "Number of influencers: " + profits.length
        + "\nCombined profit: $" + String.format("%,.2f", sum);
  }
}