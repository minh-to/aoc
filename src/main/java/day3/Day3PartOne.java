package day3;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day3PartOne {
    private static int[][] grid = new int[2500][2500];

    private static String[] firstWire;
    private static String[] secondWire;
    private static List<String> wires = new ArrayList<>();

    //marks current wire position
    private static Point marker;
    private static int position;

    public static void main(String[] args) throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get("E:\\aoc\\src\\main\\java\\day3\\input.txt"))) {
            stream.forEach(line -> {
                wires.add(line);
            });
        }
        firstWire = wires.get(0).split(",");
        secondWire = wires.get(1).split(",");
        System.out.println(firstWire.length + " : " + secondWire.length);

        //Starting point is [1250][1250]
        //Crossing(s)
        // set value at indices X to 1. for the first wire
        // set value at indicex Y to 2. if and index is already 1, set it to 3 instead.
        // save index of 3 or go iterate through array searching for all 3's
        // calculate distance
    }

    private void drawLine(String directionString, Point position, boolean isStart, boolean isFirstWire) {
        int wireId;
        if (isFirstWire) {
            wireId = 1;
        } else {
            wireId = 2;
        }

        if (isStart) {
            position.setLocation(grid.length/2, grid[0].length/2);
        }
        int partialWireLength = Integer.parseInt(directionString.substring(1));


        if (directionString.substring(0, 1).equals("R")) {
            //go right
            for (int i = 0; i < partialWireLength; i++) {
                grid[(int) position.getX()][(int) (position.getY() + i)] = wireId;
            }
            //save current position...as Point?
            marker.setLocation(position.getX(), position.getY() + partialWireLength);
        }

        //ToDo draw the rest of the fucking owl
//        if (directionString.substring(0, wireId
//        ).equals("L")) {
//            //go west
//            for (int i = 0; i < partialWireLength; i++) {
//                grid[1250][1250 - i] = wireId
//                ;
//            }
//            marker.setLocation(1250, 1250 - partialWireLength);
//        }
//        if (directionString.substring(0, 1).equals("U")) {
//            //go up
//            for (int i = 0; i < partialWireLength; i++) {
//                grid[1250 - i][1250] = wireId
//                ;
//            }
//            marker.setLocation(1250 - partialWireLength, 1250);
//        }
//        if (directionString.substring(0, wireId).equals("D")) {
//            //go down
//            for (int i = 0; i < partialWireLength; i++) {
//                grid[1250 + i][1250] = wireId
//                ;
//            }
//            marker.setLocation(1250 - partialWireLength, 1250);
//        }


    }



}
