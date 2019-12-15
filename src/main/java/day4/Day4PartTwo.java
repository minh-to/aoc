package day4;

import java.util.ArrayList;
import java.util.List;

public class Day4PartTwo {

    private static List<Integer> allPasswords = new ArrayList<>();
    private static List<Integer> passedAllChecks = new ArrayList<>();
    private static List<Integer> passedSecondaryChecks = new ArrayList<>();

    //It is a six-digit number.
    //The value is within the range given in your puzzle input.
    //Two adjacent digits are the same (like 22 in 122345).
    //Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).

    public static void main(String[] args) {

        int max = 643603;
        // 171309 - 643603
        int min = 171309;
        for (int i = min; i < max; i++) {
            allPasswords.add(i);
        }
//
        allPasswords.forEach(password -> {
            if (checkAdjacentNumbers(password)) {
                passedAllChecks.add(password);
            }
        });
        System.out.println(passedAllChecks.size());

        // Part 2 of the puzzle

        passedAllChecks.forEach(password -> {
            if (checkSecondaryRequirement(password)) {
                passedSecondaryChecks.add(password);
            }
        });

        System.out.println(passedSecondaryChecks.size());
    }

    // Number has to have at least one matching group of equal digits with a size of 2 -> 111122 is ok; 177789 is not
    private static boolean checkSecondaryRequirement(Integer password) {
        String temp = String.valueOf(password);
        String first = temp.substring(0, 1);
        String second = temp.substring(1, 2);
        String third = temp.substring(2, 3);
        String fourth = temp.substring(3, 4);
        String fifth = temp.substring(4, 5);
        String sixth = temp.substring(5);

        List<String> stringList = new ArrayList<>();
        stringList.add(first);
        stringList.add(second);
        stringList.add(third);
        stringList.add(fourth);
        stringList.add(fifth);
        stringList.add(sixth);
        boolean isNotPartOfALargerGroup = false;

        // temp string only needs to at least have one dupe of the size 2 to be valid
        for (String string : stringList) {
            if (countOccurence(temp, string) == 2) {
                isNotPartOfALargerGroup = true;
            }
        }
        return isNotPartOfALargerGroup;
    }

    private static int countOccurence(String string, String otherString){
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == otherString.charAt(0)) {
                count++;
            }
        }
        return count;
    }


    private static boolean checkAdjacentNumbers(int password) {
        String temp = String.valueOf(password);
        // Check for dupes
        boolean hasDuplicates = false;
        boolean hasBiggerNextInt = false;
        for (int i = 0; i < temp.length() - 1; i++) {
            if (temp.substring(i, i + 1).equals(temp.substring(i + 1, i + 2))) {
                hasDuplicates = true;
            }

            if (Integer.parseInt(temp.substring(i + 1, i + 2)) >= Integer.parseInt(temp.substring(i, i + 1))) {
                hasBiggerNextInt = true;
            } else {
                hasBiggerNextInt = false;
                break;
            }
        }

        return hasDuplicates && hasBiggerNextInt;
    }
}
