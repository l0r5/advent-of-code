package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Day3 {

    protected List<String> readFile() {
        List<String> input = new ArrayList<>();
        try {
            File myObj = new File("src/main/resources/day3_slope_map.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred during.");
            e.printStackTrace();
        }
        System.out.println("Data was read successfully.");
        return input;
    }

    protected int calcTrees(List<String> inputMap) {
        List<String> map = new ArrayList<>(inputMap);
        Iterator<String> i = map.iterator();
        int steps = 0;
        int trees = 0;
        while (i.hasNext()) {
            String line = i.next();
            i.remove();
            char searchChar;
            if (steps == 0 && i.hasNext()) {
                line = i.next();
                i.remove();
            }
            steps += 3;
            while (steps >= line.length()) {
                line += line;
            }
            searchChar = line.charAt(steps);
            if (searchChar == '#') trees++;
        }
        return trees;
    }

    protected int calcTreesDynamically(List<String> inputMap, int right, int down) {
        List<String> map = new ArrayList<>(inputMap);
        Iterator<String> i = map.iterator();
        int steps = 0;
        int trees = 0;
        while (i.hasNext()) {
            String line = "";
            for (int n = 0; n < down; n++) {
                if (i.hasNext()) {
                    line = i.next();
                    i.remove();
                }
            }
            char searchChar;
            if (steps == 0 && i.hasNext()) {
                line = i.next();
                i.remove();
            }
            steps += right;
            while (steps >= line.length()) {
                line += line;
            }
            searchChar = line.charAt(steps);
            if (searchChar == '#') trees++;
        }
        return trees;
    }

    protected long calcResult() {
        List<String> data = readFile();
        int result1 = calcTreesDynamically(data, 1, 1);
        int result2 = calcTreesDynamically(data, 3, 1);
        int result3 = calcTreesDynamically(data, 5, 1);
        int result4 = calcTreesDynamically(data, 7, 1);
        int result5 = calcTreesDynamically(data, 1, 2);
        return (long) result1 * result2 * result3 * result4 * result5;
    }

}