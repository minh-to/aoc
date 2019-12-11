package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day2PartOne {


    private static List<Integer> intCode = new ArrayList<>();


    public static void main(String[] args) throws IOException {



        try (Stream<String> stream = Files.lines(Paths.get("C:\\Users\\tminhto\\Desktop\\aoc\\src\\main\\java\\day2\\intcode.txt"))) {
            stream.forEach(line -> {
                String[] strings = line.split(",");
                for (String s : strings) {
                    intCode.add(Integer.valueOf(s.trim()));
                }
            });
        }

        System.out.print("Before " + intCode + "\n");

        outerLoop:
        for (int i = 0; i < intCode.size(); i+=4) {
            switch (intCode.get(i)) {
                case 1: // get value at position .get(i+1) and .get(i+2), add them and overwrite value at .get(i+3)
                    int sum = intCode.get(intCode.get(i+1)) + intCode.get(intCode.get(i+2));
                    intCode.set(intCode.get(i + 3), sum);
//                    System.out.println("1 :" + intCode);
                    break;
                case 2:
                    int product = intCode.get(intCode.get(i+1)) * intCode.get(intCode.get(i+2));
                    intCode.set(intCode.get(i + 3), product);
//                    System.out.println("2 :" + intCode);
                    break;
                case 99:
                    System.out.println("break at index " + i);
                    break outerLoop;
            }
        }
        System.out.print("After " + intCode);
    }
}
