package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Day6 {

    protected List<String> readFile() {
        List<String> input = new ArrayList<>();
        try {
            File myObj = new File("src/main/resources/day6_custom_declaration_forms.txt");
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

    protected int calcFinalSum(List<String> inputData) {
        List<String> allAnswers = new ArrayList<>(inputData);
        Iterator<String> it = allAnswers.iterator();
        List<Character> uniqueHits = new ArrayList<>();
        int sum = 0;
        while (it.hasNext()) {
            char[] line = it.next().toCharArray();
            if (line.length <= 0) {
                sum += uniqueHits.size();
                uniqueHits.clear();
            } else {
                for (char c : line) {
                    if (!uniqueHits.contains(c)) uniqueHits.add(c);
                }
            }
        }
        if (!uniqueHits.isEmpty()) {
            sum += uniqueHits.size();
            uniqueHits.clear();
        }
        return sum;
    }

    protected int calcFinalSumOnlyEquals(List<String> inputData) {
        List<String> allAnswers = new ArrayList<>(inputData);
        Iterator<String> it = allAnswers.iterator();
        List<Character> matches = new ArrayList<>();
        int sum = 0;
        boolean isFirstRun = true;
        while (it.hasNext()) {
            String line = it.next();
            if (line.isEmpty()) {
                sum += matches.size();
                matches.clear();
                isFirstRun = true;
            } else {
                if (matches.isEmpty()) {
                    if (isFirstRun) {
                        for (char c : line.toCharArray()) {
                            matches.add(c);
                        }
                        isFirstRun = false;
                    }
                } else {
                    matches = compareLines(matches, line);
                }
            }
        }
        if (!matches.isEmpty()) {
            sum += matches.size();
            matches.clear();
        }
        return sum;
    }

    protected List<Character> compareLines(List<Character> inputMatches, String line) {
        List<Character> matches = new ArrayList<>(inputMatches);
        List<Character> newMatches = new ArrayList<>();
        for (char c : line.toCharArray()) {
            if (matches.contains(c)) {
                if (!newMatches.contains(c)) newMatches.add(c);
            }
        }
        return newMatches;
    }
}
