package day4;

import java.util.ArrayList;
import java.util.List;

public class Day4PartOne {

    private static List<Integer> allPasswords = new ArrayList<>();
    private static List<Integer> passedAllChecks = new ArrayList<>();

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

        allPasswords.forEach(password -> {
            if(checkAdjacentNumbers(password)){
                passedAllChecks.add(password);
            }
        });
        System.out.println(allPasswords.size());
        System.out.println(passedAllChecks.size());
    }

    private static boolean checkAdjacentNumbers(int password) {
        String temp = String.valueOf(password);
        // Check for dupes
        boolean hasDuplicates = false;
        boolean hasBiggerNextInt = false;
        for (int i = 0; i < temp.length()-1; i++) {
            if(temp.substring(i,i+1).equals(temp.substring(i+1,i+2))){
                hasDuplicates = true;
            }

            if(Integer.parseInt(temp.substring(i+1,i+2)) >= Integer.parseInt(temp.substring(i,i+1))){
                hasBiggerNextInt = true;
            }else{
                hasBiggerNextInt = false;
                break;
            }
        }

        return hasDuplicates && hasBiggerNextInt;
    }
}
