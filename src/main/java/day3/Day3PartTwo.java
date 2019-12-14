package day3;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day3PartTwo {

    //say goodbye to your heap space
    private static byte[][] grid = new byte[40000][40000];
    private static List<String> wires = new ArrayList<>();

    private static List<Point> intersections = new ArrayList<>();

    private static int firstWirePathUntilIntersection = 0;
    private static int secondWirePathUntilIntersection = 0;

    //marks current wire position
    private static Point marker = new Point(0, 0);

    public static void main(String[] args) throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get("src/main/java/day3/tester.txt"))) {
            stream.forEach(line -> {
                wires.add(line);
            });
        }
        String[] firstWire = wires.get(0).split(",");
        String[] secondWire = wires.get(1).split(",");

        //1st wire
        drawLine(firstWire[0], marker, true, true);
        for (int i = 1; i < firstWire.length; i++) {
            drawLine(firstWire[i], marker, false, true);
        }

        //2nd wire
        drawLine(secondWire[0], marker, true, false);
        for (int i = 1; i < secondWire.length; i++) {
            drawLine(secondWire[i], marker, false, false);
        }

//        findIntersections();

        //------------------------//
        calcDistance(firstWire[0], marker, true, true);
        for (int i = 1; i < firstWire.length; i++) {
            calcDistance(firstWire[i], marker, false, true);
        }

        System.out.println("Calculating for 2nd wire");
        firstWirePathUntilIntersection = 0;

        calcDistance(secondWire[0], marker, true, false);
        for (int i = 1; i < secondWire.length; i++) {
            calcDistance(secondWire[i], marker, false, false);
        }
    }

    private static void drawLine(String directionString, Point position, boolean isStart, boolean isFirstWire) {
        byte wireId;
        int partialWireLength = Integer.parseInt(directionString.substring(1));
        if (isFirstWire) {
            wireId = 1;
        } else {
            wireId = 2;
        }

        if (isStart) {
            position.setLocation(grid.length / 2, grid[0].length / 2);
        }
        System.out.println("pos before:" + position);
        System.out.println("direction: " + directionString);

        if (directionString.substring(0, 1).equals("R")) {
            //go right
            for (int i = 0; i < partialWireLength + 1; i++) {
                if (grid[(int) position.getX()][(int) (position.getY() + i)] == 1 && wireId == 2) {
                    grid[(int) position.getX()][(int) (position.getY() + i)] = 3;
                } else {
                    grid[(int) position.getX()][(int) (position.getY() + i)] = wireId;
                }
            }
            //save current position...as Point
            marker.setLocation(position.getX(), position.getY() + partialWireLength);
        }

        if (directionString.substring(0, 1).equals("L")) {
            //go west
            for (int i = 0; i < partialWireLength + 1; i++) {
                if (grid[(int) position.getX()][(int) (position.getY() - i)] == 1 && wireId == 2) {
                    grid[(int) position.getX()][(int) (position.getY() - i)] = 3;
                } else {
                    grid[(int) position.getX()][(int) (position.getY() - i)] = wireId;
                }
            }
            marker.setLocation((int) position.getX(), position.getY() - partialWireLength);
        }
        if (directionString.substring(0, 1).equals("U")) {
            //go up
            for (int i = 0; i < partialWireLength + 1; i++) {
                if (grid[(int) (position.getX() - i)][(int) position.getY()] == 1 && wireId == 2) {
                    grid[(int) (position.getX() - i)][(int) position.getY()] = 3;
                } else {
                    grid[(int) (position.getX() - i)][(int) position.getY()] = wireId;
                }
            }
            marker.setLocation((int) position.getX() - partialWireLength, position.getY());
        }

        if (directionString.substring(0, 1).equals("D")) {
            //go down
            for (int i = 0; i < partialWireLength + 1; i++) {
                if (grid[(int) (position.getX() + i)][(int) position.getY()] == 1 && wireId == 2) {
                    grid[(int) (position.getX() + i)][(int) position.getY()] = 3;
                } else {
                    grid[(int) (position.getX() + i)][(int) position.getY()] = wireId;
                }
            }
            marker.setLocation((int) position.getX() + partialWireLength, position.getY());
        }
    }

    private static void findIntersections() {
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 3) {
                    System.out.println("Overlap at: " + i + " " + j);
                    intersections.add(new Point(i, j));
                }
            }
        }
    }

    private static void calcDistance(String directionString, Point position, boolean isStart, boolean isFirstWire) {
        byte wireId;
        int partialWireLength = Integer.parseInt(directionString.substring(1));

        //Mark the starting point
        grid[grid.length/2][grid[0].length/2] = 69;


        if (isFirstWire) {
            wireId = 1;
        } else {
            wireId = 2;
        }

        if (isStart) {
            position.setLocation(grid.length / 2 , grid[0].length / 2 );
        }

     /*go through grid and follow wires until we hit the first intersection of each wire
     while counting the steps we needed to get there.
      */

        if (directionString.substring(0, 1).equals("R")) {
            //go right
            for (int i = 0; i <= partialWireLength ; i++) {
                if (grid[(int) position.getX()][(int) (position.getY() + i)] == wireId) {
                    if (wireId == 1) {
                        firstWirePathUntilIntersection++;
                    } else {
                        secondWirePathUntilIntersection++;
                    }
                } else if (grid[(int) position.getX()][(int) (position.getY() + i)] == 3) {

                    System.out.println("first intersection encountered, length is 1: " + firstWirePathUntilIntersection + " second: " + secondWirePathUntilIntersection);
                }
            }
            marker.setLocation(position.getX(), position.getY() + partialWireLength);
            System.out.println("went " + firstWirePathUntilIntersection + "||" + secondWirePathUntilIntersection + "right");
        }


        if (directionString.substring(0, 1).equals("L")) {
            //go west
            for (int i = 0; i <= partialWireLength; i++) {
                if (grid[(int) position.getX()][(int) (position.getY() - i)] == wireId) {

                    if (wireId == 1) {
                        firstWirePathUntilIntersection++;
                    } else {
                        secondWirePathUntilIntersection++;
                    }
                } else if (grid[(int) position.getX()][(int) (position.getY() - i)] == 3) {

                    System.out.println("first intersection encountered, length is 1: " + firstWirePathUntilIntersection + " 2: " + secondWirePathUntilIntersection);
                }
            }
            marker.setLocation((int) position.getX(), position.getY() - partialWireLength);
            System.out.println("went " +firstWirePathUntilIntersection + "||" +secondWirePathUntilIntersection + "left");
        }

        if (directionString.substring(0, 1).equals("U")) {
            //go up
            for (int i = 0; i <= partialWireLength; i++) {
                if (grid[(int) (position.getX() - i)][(int) position.getY()] == wireId) {
                    if (wireId == 1) {
                        firstWirePathUntilIntersection++;
                    } else {
                        secondWirePathUntilIntersection++;
                    }
                } else if (grid[(int) (position.getX() - i)][(int) position.getY()] == 3) {

                    System.out.println("first intersection encountered, length is 1: " + firstWirePathUntilIntersection + " 2: " + secondWirePathUntilIntersection);
                }
            }
            marker.setLocation((int) position.getX() - partialWireLength, position.getY());
            System.out.println("went " + firstWirePathUntilIntersection + "||" +secondWirePathUntilIntersection + "up");
        }

        if (directionString.substring(0, 1).equals("D")) {
            //go down
            for (int i = 0; i <= partialWireLength ; i++) {
                if (grid[(int) (position.getX() + i)][(int) position.getY()] == wireId) {
                    if (wireId == 1) {
                        firstWirePathUntilIntersection++;
                    } else {
                        secondWirePathUntilIntersection++;
                    }
                } else if (grid[(int) (position.getX() + i)][(int) position.getY()] == 3) {

                    System.out.println("first intersection encountered, length is 1: " + firstWirePathUntilIntersection + " 2: " + secondWirePathUntilIntersection);
                }
            }
            marker.setLocation((int) position.getX() + partialWireLength, position.getY());
            System.out.println("went " + firstWirePathUntilIntersection + "||" +secondWirePathUntilIntersection + "down");
        }

    }
}
