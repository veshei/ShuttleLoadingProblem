package cs4800.hw03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by veronicashei on 11/2/16.
 */
public class Solution {

  public Solution() {
  }

  public static void main(String[] args) {
    List<String> strings = readStrings();
    Integer gadget = numGadgets(strings);
    Integer[] futProfits = profits(strings);
    Integer[] gadgetWeights = weights(strings);
    Integer weight = weightLimit(strings);
    Integer solution = shuttleLoading(gadget, futProfits, gadgetWeights, weight);
    System.out.println(solution);
  }

  private static Integer shuttleLoading(Integer size, Integer[] profit, Integer[] weight,
                                        Integer limit) {
    Integer[][] matrix = new Integer[size + 1][limit + 1];

    for (int col = 0; col <= limit; col++) {
      matrix[0][col] = 0;
    }
    for (int row = 0; row <= size; row++) {
      matrix[row][0] = 0;
    }
    for (int item = 1; item <= size; item++) {
      for (int w = 1; w <= limit; w++) {
        if (weight[item - 1] <= w) {
          matrix[item][w] = Math.max(profit[item - 1] +
              matrix[item - 1][w - weight[item - 1]], matrix[item - 1][w]);
        } else {
          matrix[item][w] = matrix[item - 1][w];
        }
      }
    }
    return matrix[size][limit];
  }

  private static List<String> readStrings() {
    List<String> myStrings = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      myStrings.add(line + "\n");
    }
    return myStrings;
  }

  private static Integer numGadgets(List<String> input) {
    Integer gadget = Integer.parseInt(input.get(0).trim());
    return gadget;
  }

  private static Integer weightLimit(List<String> input) {
    Integer weight = Integer.parseInt(input.get(3).trim());
    return weight;
  }

  private static Integer[] profits(List<String> input) {
    String value = input.get(1);
    String[] numbers = value.split(",");
    List<Integer> result = new ArrayList<Integer>();
    for (String number : numbers) {
      result.add(Integer.parseInt(number.trim()));
    }
    Integer[] totalProfits = result.toArray(new Integer[result.size()]);
    return totalProfits;
  }

  private static Integer[] weights(List<String> input) {
    String value = input.get(2);
    String[] numbers = value.split(",");
    List<Integer> result = new ArrayList<Integer>();
    for (String number : numbers) {
      result.add(Integer.parseInt(number.trim()));
    }
    Integer[] totalWeights = result.toArray(new Integer[result.size()]);
    return totalWeights;
  }

}
