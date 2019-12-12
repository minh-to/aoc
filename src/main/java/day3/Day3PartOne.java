package day3;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day3PartOne {

//    private static int[][] grid = new int[9000][9000];
    private static byte[][] grid = new byte[20000][10000];

    private static String[] firstWire;
    private static String[] secondWire;
    private static List<String> wires = new ArrayList<>();

    //marks current wire position
    private static Point marker = new Point(0, 0);

    public static void main(String[] args) throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get("src/main/java/day3/input.txt"))) {
            stream.forEach(line -> {
                wires.add(line);
            });
        }
        firstWire = wires.get(0).split(",");
        secondWire = wires.get(1).split(",");
        System.out.println(firstWire.length + " : " + secondWire.length);

        // Starting point is the grid center
        // Crossing(s)
        // set value at indices X to 1. for the first wire
        // set value at indices Y to 2. if and index is already 1, set it to 3 instead.
        // save index of 3 or go iterate through array searching for all 3's
        // calculate distance

        // draw first wire part
//        drawLine(firstWire[0], marker, true, true);
//        // draw the rest of the fucking owl
//        for (int i = 1; i < firstWire.length; i++) {
//            drawLine(firstWire[i], marker, false, true);
//        }
//        System.out.println("Marker position: " + marker);
//
//        drawLine(secondWire[0], marker, true, false);
//        for (int i = 1; i < secondWire.length; i++) {
//            drawLine(secondWire[i], marker, false, false);
//        }
//        System.out.println("Marker position: " + marker);
    int x = 0;
    int y = 0;
        for (int i = 0; i < firstWire.length; i++) {
            if(firstWire[i].substring(0,1).equals("R")){
                x += Integer.parseInt(firstWire[i].substring(1));
            }

            if(firstWire[i].substring(0,1).equals("U")){
                y += Integer.parseInt(firstWire[i].substring(1));
            }
        }
        System.out.println(x);
        System.out.println(y);
    }

    private static void drawLine(String directionString, Point position, boolean isStart, boolean isFirstWire) {
        byte wireId;
        int partialWireLength = Integer.parseInt(directionString.substring(1));
//        double parsed = Double.parseDouble(directionString.substring(1));
//        double partialWireLength = parsed / 10;
        if (isFirstWire) {
            wireId = 1;
        } else {
            wireId = 2;
        }

        if (isStart) {
            position.setLocation(grid.length / 2, grid[0].length / 2);
//            position.setLocation(grid.length / 4, grid[0].length / 3.0);
        }
        System.out.println(position);
        System.out.println("direction: " + directionString);
        System.out.println(partialWireLength);

        if (directionString.substring(0, 1).equals("R")) {
            //go right
            for (double i = 0; i < partialWireLength; i += 0.1) {
                grid[(int) position.getX()][(int) (position.getY() + i)] = wireId;
            }
            //save current position...as Point?
            marker.setLocation(position.getX() + partialWireLength, position.getY());
        }

        if (directionString.substring(0, 1).equals("L")) {
            //go west
            for (double i = 0; i < partialWireLength; i += 0.1) {
                grid[(int) position.getX()][(int) (position.getY() - i)] = wireId;
            }
            marker.setLocation((int) position.getX() - partialWireLength, position.getY());
        }

        if (directionString.substring(0, 1).equals("U")) {
            //go up
            for (double i = 0; i < partialWireLength; i += 0.1) {
                grid[(int) (position.getX() - i)][(int) position.getY()] = wireId;
            }
            marker.setLocation((int) position.getX(), position.getY() + partialWireLength);
        }

        if (directionString.substring(0, 1).equals("D")) {
            //go down
            for (double i = 0; i < partialWireLength; i += 0.1) {
                grid[(int) (position.getX() + i)][(int) position.getY()] = wireId;
            }
            marker.setLocation((int) position.getX(), position.getY() - partialWireLength);
        }


    }


}
