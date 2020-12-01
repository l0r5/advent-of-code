package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    public int getResult() {
        List<Integer> inputNumbers = readNumbers();
        List<Integer> pair = calcPair(inputNumbers);
        int finalResult = calcFinalResult(pair);
        System.out.println("Pair: " + pair.get(0) + ", " + pair.get(1));
        System.out.println("Day 1 Final Result: " + finalResult);
        return finalResult;
    }

    protected List<Integer> readNumbers() {
        List<Integer> inputNumbers = new ArrayList<>();
        try {
            File myObj = new File("/home/lars/dev/advent-of-code/src/main/resources/day1_expense_report.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                int data = myReader.nextInt();
                inputNumbers.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred during.");
            e.printStackTrace();
        }
        System.out.println("Data was read successfully.");
        return inputNumbers;
    }

    protected List<Integer> calcPair(List<Integer> allNumbers) {
        List<Integer> pair = new ArrayList<>();
        Iterator<Integer> i = allNumbers.iterator();
        while (i.hasNext()) {
            Integer number = i.next();
            i.remove();
            int searchNumber = 2020 - number;
            if (allNumbers.contains(searchNumber)) {
                pair.add(number);
                pair.add(searchNumber);
                System.out.println("Found a pair: " + number + ", " + searchNumber);
            }
        }
        return pair;
    }

    protected int calcFinalResult(List<Integer> pair) {
        if(pair.size() != 2) {
            System.out.println("Received more than one pair: " + pair.toString());
            return 0;
        }
        return pair.get(0) * pair.get(1);
    }
}