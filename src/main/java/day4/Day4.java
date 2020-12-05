package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day4 {

    protected List<String> readFile() {
        List<String> input = new ArrayList<>();
        try {
            File myObj = new File("src/main/resources/day4_passport_batches.txt");
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

    protected List<String> formatPassList(List<String> rawInputPassList) {
        List<String> rawPassList = new ArrayList<>(rawInputPassList);
        List<String> formattedPassList = new ArrayList<>();
        Iterator<String> iterator = rawPassList.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.isEmpty() && iterator.hasNext()) {
                line = iterator.next();
                index++;
            }
            if (formattedPassList.size() <= index) {
                formattedPassList.add(line);
            } else {
                String oldLine = formattedPassList.get(index);
                String newLine = oldLine + " " + line;
                formattedPassList.remove(oldLine);
                formattedPassList.add(newLine);
            }
            iterator.remove();
        }
        return formattedPassList;
    }

    protected List<Map<String, String>> producePassResultList(List<String> inputPassList) {
        List<String> passList = new ArrayList<>(inputPassList);
        List<Map<String, String>> resultList = new ArrayList<>();
        for (String line : passList) {
            Map<String, String> pass = createPass(line);
            resultList.add(pass);
        }
        return resultList;
    }

    protected Map<String, String> createPass(String line) {
        Map<String, String> resultPass = new HashMap<>();
        String[] passElements = line.split(" ");
        for (String elem : passElements) {
            String[] pair = elem.split(":");
            if (pair.length < 2) {
                resultPass.put(pair[0], "");
            } else {
                resultPass.put(pair[0].trim(), pair[1].trim());
            }
        }
        return resultPass;
    }

    protected boolean isPassValid(Map<String, String> inputTestPass) {
        if (inputTestPass.size() < 7) return false;
        if (inputTestPass.size() == 7) {
            return inputTestPass.get("cid") == null;
        }
        for (Map.Entry<String, String> entry : inputTestPass.entrySet()) {
            if (!entry.getKey().equals("cid") && (entry.getKey().isEmpty() || entry.getValue().isEmpty()))
                return false;
        }
        return true;
    }

    protected boolean isPassValidStrict(Map<String, String> inputTestPass) {
        if (inputTestPass.size() < 7) return false;
        if (inputTestPass.size() == 7) {
            if (!(inputTestPass.get("cid") == null)) return false;
        }
        for (Map.Entry<String, String> entry : inputTestPass.entrySet()) {
            if (!entry.getKey().equals("cid") && (entry.getKey().isEmpty() || entry.getValue().isEmpty())) {
                return false;
            }
            switch (entry.getKey()) {
                case "byr":
                    if (entry.getValue().length() != 4 || Integer.parseInt(entry.getValue()) < 1920 || Integer.parseInt(entry.getValue()) > 2002)
                        return false;
                    break;
                case "iyr":
                    if (entry.getValue().length() != 4 || Integer.parseInt(entry.getValue()) < 2010 || Integer.parseInt(entry.getValue()) > 2020)
                        return false;
                    break;
                case "eyr":
                    if (entry.getValue().length() != 4 || Integer.parseInt(entry.getValue()) < 2020 || Integer.parseInt(entry.getValue()) > 2030)
                        return false;
                    break;
                case "hgt":
                    if (!(entry.getValue().contains("cm") || entry.getValue().contains("in")))
                        return false;
                    if (entry.getValue().contains("cm")) {
                        int num = Integer.parseInt(entry.getValue().replace("cm", ""));
                        if (num < 150 || num > 193) return false;
                    }
                    if (entry.getValue().contains("in")) {
                        int num = Integer.parseInt(entry.getValue().replace("in", ""));
                        if(num < 59 || num > 76) return false;
                    }
                    break;
                case "hcl":
                    if (!entry.getValue().startsWith("#")) return false;
                    if (entry.getValue().length() != 7) return false;
                    boolean valid;
                    for (char c : entry.getValue().toCharArray()) {
                        valid = ((c == '#')) ||
                                ((c >= 'a') && (c <= 'f')) ||
                                ((c >= '0') && (c <= '9'));
                        if (!valid) {
                            return false;
                        }
                    }
                    break;
                case "ecl":
                    List<String> allowedValues = new ArrayList<>();
                    allowedValues.add("amb");
                    allowedValues.add("blu");
                    allowedValues.add("brn");
                    allowedValues.add("gry");
                    allowedValues.add("grn");
                    allowedValues.add("hzl");
                    allowedValues.add("oth");
                    int hit = 0;
                    for (String value : allowedValues) {
                        if (entry.getValue().equals(value)) hit++;
                    }
                    if (hit == 0) return false;
                    break;
                case "pid":
                    if (entry.getValue().length() != 9)
                        return false;
            }
        }
        return true;
    }

    protected int countValidPasses(List<Map<String, String>> passResultList) {
        int count = 0;
        for (Map<String, String> pass : passResultList) {
            if (isPassValid(pass)) count++;
        }
        return count;
    }

    protected int countValidPassesStrict(List<Map<String, String>> passResultList) {
        int count = 0;
        for (Map<String, String> pass : passResultList) {
            if (isPassValidStrict(pass)) count++;
        }
        return count;
    }
}