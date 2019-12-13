package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day1PartTwo {
    private static int requiredFuel;
    private static List<Integer> masses = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get("src/main/java/day1/modules.txt"))) {
            stream.forEach(line->masses.add(Integer.valueOf(line)));
        }
        masses.forEach(Day1PartTwo::calculateFuel);

        System.out.println(requiredFuel);
    }


    private static int calculateFuel(int mass) {
        int fuelPerModule = calc(mass);
        int temp = calc(fuelPerModule);
        while (temp > 0) {
            fuelPerModule += temp;
            temp = calc(temp);
            System.out.println(fuelPerModule);
        }
        requiredFuel += fuelPerModule;
        return requiredFuel;
    }

    private static int calc(int fuel) {
        return (int) Math.floor(fuel / 3) - 2;
    }

}
