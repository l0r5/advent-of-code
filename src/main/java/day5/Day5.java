package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day5 {

    protected List<String> readFile() {
        List<String> input = new ArrayList<>();
        try {
            File myObj = new File("src/main/resources/day5_boarding_passes.txt");
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

    protected long calcSeat(String boardingPass, int minRow, int maxRow, int minCol, int maxCol) {
        char[] passArr = boardingPass.toCharArray();
        int[] rangeRow = new int[]{minRow, maxRow};
        int[] rangeCol = new int[]{minCol, maxCol};
        int count = 0;
        for (char c : passArr) {
            switch (c) {
                case 'F':
                    int newMaxRow = rangeRow[1] - ((rangeRow[1] - rangeRow[0]) / 2);
                    rangeRow[1] = newMaxRow - 1;
                    break;
                case 'B':
                    // FBF ->0, 64 // 32, 64 // max - min / 2
                    // FBB ->0, 64 // 32, 64 // max - min / 2 + min
                    int newMinRow = ((rangeRow[1] - rangeRow[0]) / 2) + rangeRow[0];
                    rangeRow[0] = newMinRow + 1;
                    break;
                case 'L':
                    int newMaxCol = rangeCol[1] - ((rangeCol[1] - rangeCol[0]) / 2);
                    rangeCol[1] = newMaxCol - 1;
                    break;
                case 'R':
                    int newMinCol = ((rangeCol[1] - rangeCol[0]) / 2) + rangeCol[0];
                    rangeCol[0] = newMinCol + 1;
                    break;
            }
            count++;
        }
        return (rangeRow[0] * 8L) + rangeCol[0];
    }

    protected List<Long> calcAllSeatIds(List<String> input) {
        List<String> passList = new ArrayList<>(input);
        List<Long> result = new ArrayList<>();
        for (String pass : passList) {
            result.add(calcSeat(pass, 0, 127, 0, 7));
        }
        return result;
    }

    protected long getHighestId(List<Long> input) {
        List<Long> idList = new ArrayList<>(input);
        long highestId = -1;
        for (long id : idList) {
            if (id >= highestId) highestId = id;
        }
        return highestId;
    }

    protected long calcMySeatId(List<String> input) {
        List<String> passList = new ArrayList<>(input);
        List<Long> idList = new ArrayList<>();
        for (String pass : passList) {
            idList.add(calcSeat(pass, 1, 126, 0, 7));
        }
        for (long num : idList) {
            for (long testNum : idList) {
                long min = Math.min(num, testNum);
                long max = Math.max(num, testNum);
                if (max - min == 2) {
                    if (!idList.contains(min + 1))
                        return min + 1;
                }
            }
        }
        return -1;
    }
}