package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day1PartOne {
    private static int requiredFuel;
    private static List<Integer> masses = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get("src/main/java/day1/modules.txt"))) {
            stream.forEach(line->masses.add(Integer.valueOf(line)));
        }
        masses.forEach(Day1PartOne::calculateFuel);

        System.out.println(requiredFuel);
    }


    private static void calculateFuel(int mass){
        requiredFuel += Math.floor(mass / 3)-2;
    }
}
