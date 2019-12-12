package day3;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day3PartOne {

    private static byte[][] grid = new byte[25][25];
    private static byte[][] testGrid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private static String[] firstWire;
    private static String[] secondWire;
    private static List<String> wires = new ArrayList<>();

    //marks current wire position
    private static Point marker = new Point(0, 0);

    public static void main(String[] args) throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get("src/main/java/day3/tester.txt"))) {
            stream.forEach(line -> {
                wires.add(line);
            });
        }
        firstWire = wires.get(0).split(",");
        secondWire = wires.get(1).split(",");

        // Starting point is the grid center
        // Crossing(s)
        // set value at indices X to 1. for the first wire
        // set value at indices Y to 2. if and index is already 1, set it to 3 instead.
        // save index of 3 or go iterate through array searching for all 3's
        // calculate distance

        // draw first wire part
        drawLine(firstWire[0], marker, true, true);
        // draw the rest of the fucking owl
        for (int i = 1; i < firstWire.length; i++) {
            drawLine(firstWire[i], marker, false, true);
        }
        System.out.println("First wire end position: " + marker);

//        drawLine(secondWire[0], marker, true, false);
////        for (int i = 1; i < secondWire.length; i++) {
////            drawLine(secondWire[i], marker, false, false);
////        }
////        System.out.println("Second wire end position: " + marker);
////
        findIntersections();

        drawGrid();

// sdfsdfdsfsfsd
//        int x = 0;
//        int y = 0;
//        for (int i = 0; i < firstWire.length; i++) {
//            if (firstWire[i].substring(0, 1).equals("R")) {
//                x += Integer.parseInt(firstWire[i].substring(1));
//            }
//
//            if (firstWire[i].substring(0, 1).equals("U")) {
//                y += Integer.parseInt(firstWire[i].substring(1));
//            }
//        }
//        System.out.println(x);
//        System.out.println(y);
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
//            position.setLocation(0,0);
        }
        System.out.println("pos before:" + position);
        System.out.println("direction: " + directionString);

        if (directionString.substring(0, 1).equals("R")) {
            //go right
            for (int i = 0; i < partialWireLength; i++) {
//                if (grid[(int) position.getX()][(int) (position.getY() + i)] == 1) {
//                    grid[(int) position.getX()][(int) (position.getY() + i)] = 3;
//
//                } else {
//                    grid[(int) position.getX()][(int) (position.getY() + i)] = wireId;
//                }
                if (grid[(int) position.getY()][(int) (position.getX() + i)] == 1) {
                    grid[(int) position.getY()][(int) (position.getX() + i)] = 3;

                } else {
                    grid[(int) position.getY()][(int) (position.getX() + i)] = wireId;
                }
            }
            //save current position...as Point?
            marker.setLocation(position.getX() + partialWireLength, position.getY());
        }

        if (directionString.substring(0, 1).equals("L")) {
            //go west
            for (int i = 0; i < partialWireLength; i++) {
//                if (grid[(int) position.getX()][(int) (position.getY() - i)] == 1) {
//                    grid[(int) position.getX()][(int) (position.getY() - i)] = 3;
//                } else {
//                    grid[(int) position.getX()][(int) (position.getY() - i)] = wireId;
//                }
                if (grid[(int) position.getY()][(int) (position.getX()) - i] == 1) {
                    grid[(int) position.getY()][(int) (position.getX()) - i] = 3;
                } else {
                    grid[(int) position.getY()][(int) (position.getX()) - i] = wireId;
                }
            }
            marker.setLocation((int) position.getX() - partialWireLength, position.getY());
        }

        if (directionString.substring(0, 1).equals("U")) {
            //go up
            for (int i = 0; i < partialWireLength + 1; i++) {
//                if (grid[(int) (position.getX() - i)][(int) position.getY()] == 1) {
//                    grid[(int) (position.getX() - i)][(int) position.getY()] = 3;
//                } else {
//                    grid[(int) (position.getX() - i)][(int) position.getY()] = wireId;
//                }
                if (grid[(int) (position.getY()) - i][(int) position.getX()] == 1) {
                    grid[(int) (position.getY()) - i][(int) position.getX()] = 3;
                } else {
                    grid[(int) (position.getY()) - i][(int) position.getX()] = wireId;
                }
            }
            marker.setLocation((int) position.getX(), position.getY() + partialWireLength);
        }

        if (directionString.substring(0, 1).equals("D")) {
            //go down
            for (int i = 0; i < partialWireLength; i++) {
//                if (grid[(int) (position.getX() + i)][(int) position.getY()] == 1) {
//                    grid[(int) (position.getX() + i)][(int) position.getY()] = 3;
//                } else {
//                    grid[(int) (position.getX() + i)][(int) position.getY()] = wireId;
//                }
                if (grid[(int) (position.getY() + i)][(int) position.getX()] == 1) {
                    grid[(int) (position.getY() + i)][(int) position.getX()] = 3;
                } else {
                    grid[(int) (position.getY() + i)][(int) position.getX()] = wireId;
                }
            }
            marker.setLocation((int) position.getX(), position.getY() - partialWireLength);
        }
    }

    private static void findIntersections() {
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 3) {
                    System.out.println("Overlap at: " + i + " " + j);
                }
            }
        }
    }

    private static void drawGrid() {
        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }
}
