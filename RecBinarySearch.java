// Import modules.
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/** Copyright (c) 2022 Mel Aguoth All rights reserved.
 * This program uses binary searching to find the index of an integer in an array.
 *
 * @author  Mel Aguoth
 * @version 11.0.13
 * @since 2022-01-18
 */

final class RecBinarySearch {
  private static int binarySearch(final int[] searchArray, final int searchInt, final int left,
                                  final int right) {

    // If the user's integer isn't in the list, return -1. 
    if (left > right) {
      return -1;
    }

    // Calculate the center of the array.
    final int middle = (left + right) / 2;

    // If the user's integer is in the middle, return the middle integer.
    if (searchArray[middle] == searchInt) {
      return middle;

    /* If the user's integer is lesser than the middle integer,
       shorten the search area by half to the left. */
    } else if (searchInt < searchArray[middle]) {
      return binarySearch(searchArray, searchInt, left, middle - 1);

    /* If the user's integer is greater than the middle integer,
       shorten the search area by half to the right. */
    } else {
      return binarySearch(searchArray, searchInt, middle + 1, right);
    }
  }

  public static void main(final String[] args) {

    // Declare an empty string for a NumberFormatException.
    String userString = "";

    try {

      // Declare constants.
      final int arraySize = 250;
      final int max = 999;
      final int min = 0;
      final File numFile = new File("/home/ubuntu/ICS4U/Unit2/Unit2-03/Unit2-03-Java/"
                                  + "randomIntegers.txt");

      // Initialize the random number generator.
      Random random = new Random();

      // Declare the list.
      ArrayList<String> integerList = new ArrayList<>();

      // Write the random numbers to a text file.
      StringBuilder builder = new StringBuilder();
      for (int integers = 0; integers < arraySize; ++integers) {
        final int randomInt = random.nextInt(max) + 1;
        builder.append(randomInt + "\n");
      }
      BufferedWriter writer = new BufferedWriter(new FileWriter("/home/ubuntu/ICS4U/Unit2/"
                            + "Unit2-03/Unit2-03-Java/randomIntegers.txt"));
      writer.write(builder.toString());
      writer.close();

      // Read the random numbers from the text file into a list.
      Scanner sc = new Scanner(numFile);
      while (sc.hasNextLine()) {
        integerList.add(sc.nextLine());
      }

      // Transfer the integers from the list to the array.
      int[] integerArray = new int[integerList.size()];
      for (int counter = 0; counter < integerArray.length; ++counter) {
        integerArray[counter] = Integer.parseInt(integerList.get(counter));
      }

      // Sort the array.
      Arrays.sort(integerArray);

      // Introduce the program.
      System.out.print("This program allows you to search for an integer between 0 and 999"
                      + " in an array of 250 integers using the binary search method.");

      // Display the array to the user.
      System.out.print("\n" + "These are the integers in the array: ");
      System.out.print(Arrays.toString(integerArray) + "\n");

      // Get the user's integer.
      Scanner input = new Scanner(System.in);
      System.out.print("Enter the integer to search for (between 0 and 999): ");
      userString = input.next();
      final int userInt = Integer.parseInt(userString);
      input.close();

      // If the user's integer isn't between 0 and 999, display an error to the user.
      if (userInt < min || userInt > max) {
        System.out.print("\n" + userInt + " isn't between 0 and 999. Please enter an integer"
                       + " between 0 and 999." + "\n");
      } else {

        // Call binarySearch.
        final int answer = binarySearch(integerArray, userInt, 0, integerArray.length - 1);

        // If the user's integer isn't in the list, display as such to the user.
        if (answer == -1) {
          System.out.print("\n" + userInt + " isn't in the list. Please try again with an"
                         + " integer from the list." + "\n");

        // Display the user's integer's index to the user.
        } else {
          System.out.println("\n" + userInt + " is at index: " + answer);
        }
      }

    // If the program cannot read or write to the file, display an error to the user.
    } catch (IOException e1) {
      e1.printStackTrace();

      // If the user's input isn't an integer, display an error to the user.
    } catch (NumberFormatException e2) {
      System.out.print("\n" + "'" + userString + "' isn't an integer."
                       + " Please enter a proper integer." + "\n");
    }
  }
}
