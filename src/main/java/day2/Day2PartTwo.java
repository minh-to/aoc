package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day2PartTwo {

    private static List<Integer> intCode = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get("C:\\Users\\tminhto\\Desktop\\aoc\\src\\main\\java\\day2\\bruteforce.txt"))) {
            stream.forEach(line -> {
                String[] strings = line.split(",");
                for (String s : strings) {
                    intCode.add(Integer.valueOf(s.trim()));
                }
            });
        }

        //Change the values to 0-99 of noun/verb
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                // intCode contains all the current entries
                List<Integer> tempList = new ArrayList<>(intCode);
                tempList.set(1, noun);
                tempList.set(2, verb);
                // call method
                calculateIntCode(tempList);
            }
        }

    }
      private static void calculateIntCode(List<Integer> tempIntCode){
            outerLoop:
            for (int i = 0; i < tempIntCode.size(); i += 4) {
                switch (tempIntCode.get(i)) {
                    case 1:
                        int sum = tempIntCode.get(tempIntCode.get(i + 1)) + tempIntCode.get(tempIntCode.get(i + 2));
                        tempIntCode.set(tempIntCode.get(i + 3), sum);
                        break;
                    case 2:
                        int product = tempIntCode.get(tempIntCode.get(i + 1)) * tempIntCode.get(tempIntCode.get(i + 2));
                        tempIntCode.set(tempIntCode.get(i + 3), product);
                        break;
                    case 99:
                        break outerLoop;
                }
            }
            if(tempIntCode.get(0) == 19690720){
                System.out.println(tempIntCode);
                System.out.println("Solution is: " + (100* tempIntCode.get(1) + tempIntCode.get(2)));
                System.exit(420);
            }
      }
}
