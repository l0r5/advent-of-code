package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day1 {
    public String getResult() {
        List<Integer> inputNumbers = readNumbers();
        List<Integer> pair = calcPair(inputNumbers);
        List<Integer> triple = calcTriple(inputNumbers);
        int finalResultPair = calcFinalResult(pair);
        int finalResultTriple = calcFinalResult(triple);
        System.out.println("Pair: " + pair.get(0) + ", " + pair.get(1));
        System.out.println("Triple: " + triple.get(0) + ", " + triple.get(1) + ", " + triple.get(2));
        System.out.println("Day 1 Final Result - Pairs:" + finalResultPair + ", " + finalResultTriple);
        return finalResultPair + "," + finalResultTriple;
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

    protected List<Integer> calcPair(List<Integer> allNumbersInput) {
        List<Integer> allNumbers = new ArrayList<>(allNumbersInput);
        List<Integer> pair = new ArrayList<>();
        Iterator<Integer> i = allNumbers.iterator();
        while (i.hasNext()) {
            Integer number = i.next();
            i.remove();
            int difference = 2020 - number;
            if (allNumbers.contains(difference)) {
                pair.add(number);
                pair.add(difference);
                System.out.println("Found a pair: " + number + ", " + difference);
            }
        }
        return pair;
    }

    protected List<Integer> calcTriple(List<Integer> allNumbersInput) {
        List<Integer> allNumbers = new ArrayList<>(allNumbersInput);
        List<Integer> triple = new ArrayList<>();
        Iterator<Integer> i = allNumbers.iterator();
        while (i.hasNext()) {
            Integer number = i.next();
            i.remove();
            int difference = 2020 - number;
            allNumbers.forEach(num -> {
                int diff = difference - num;
                if (diff <= 0) return;
                if (allNumbers.contains(diff)) {
                    if(triple.contains(number) || triple.contains(num) || triple.contains(diff)) return;
                    triple.add(number);
                    triple.add(num);
                    triple.add(diff);
                    System.out.println("Found a triple: " + number + ", " + num + ", " + diff);
                }
            });
        }
        return triple;
    }

    protected int calcFinalResult(List<Integer> tuple) {
        if (tuple.size() < 2 || tuple.size() > 3) {
            System.out.println("Didn't receive a pair or tuple: " + tuple.toString());
            return -1;
        }
        return tuple.stream().reduce(1, (a, b) -> a * b);
    }
}