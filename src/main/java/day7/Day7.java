package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class Day7 {

    protected List<String> readFile() {
        List<String> input = new ArrayList<>();
        try {
            File myObj = new File("src/main/resources/day7_luggage_bags_with_colors.txt");
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


    protected Map<String, Map<String, String>> getBagTypes(List<String> inputBags) {
        List<String> bagList = new ArrayList<>(inputBags);
        Map<String, Map<String, String>> result = new HashMap<>();
        for (String line : bagList) {
            List<String> words = Arrays.asList(line.split(" "));
            String bagType = words.get(0) + " " + words.get(1);
            Map<String, String> contents = new HashMap<>();
            for (int i = 4; i < words.size(); i += 4) {
                if (!words.get(i).equals("no")) {
                    String key = words.get(i + 1) + " " + words.get(i + 2);
                    String value = words.get(i);
                    contents.put(key, value);
                }
            }
            result.put(bagType, contents);
        }
        return result;
    }

    protected Map<String, Map<String, String>> calculateBiggerBags(Map<String, Map<String, String>> inputBagTypes, String color) {
        Map<String, Map<String, String>> bagTypes = new HashMap<>(inputBagTypes);
        List<String> colorsToFind = new ArrayList<>();
        Map<String, Map<String, String>> result = new HashMap<>();
        colorsToFind.add(color);
        bagTypes.remove(color);
        while (!bagTypes.isEmpty()) {
            Map<String, Map<String, String>> directContainers = getDirectContainers(bagTypes, colorsToFind);
            if (directContainers.isEmpty()) break;
            colorsToFind = updateColors(directContainers);
            directContainers.forEach((k, v) -> {
                bagTypes.remove(k);
                result.put(k, v);
            });
        }
        return result;
    }

    protected long getNumberOfBagsBigger(Map<String, Map<String, String>> inputBags) {
        return inputBags.size();
    }

    private List<String> updateColors(Map<String, Map<String, String>> directContainers) {
        List<String> resultColors = new ArrayList<>();
        directContainers.forEach((k, v) -> {
            resultColors.add(k);
        });
        return resultColors;
    }

    protected Map<String, Map<String, String>> getDirectContainers(Map<String, Map<String, String>> inputBagTypes, List<String> colorsToFind) {
        Map<String, Map<String, String>> bagTypes = new HashMap<>(inputBagTypes);
        Map<String, Map<String, String>> directContainers = new HashMap<>();
        colorsToFind.forEach((color) -> {
            bagTypes.forEach((bagType, contents) -> {
                contents.forEach((contentColor, amount) -> {
                    if ((contentColor.equals(color) && !directContainers.containsKey(bagType)))
                        directContainers.put(bagType, contents);
                });
            });
        });
        return directContainers;
    }

    protected long countBagsInside(Map<String, Map<String, String>> inputBagTypes, String colorToFind) {
        Map<String, Map<String, String>> bagTypesStrings = new HashMap<>(inputBagTypes);
        Map<String, Map<String, Long>> bagTypes = parseToLong(bagTypesStrings);
        Map<String, Long> actualValues = new HashMap<>();
        long sum = 0;
        Map<String, Long> colorsToCheck = new HashMap<>();
        colorsToCheck.put(colorToFind, 1L);
        while (colorsToCheck.entrySet().stream().findFirst().isPresent()) {
            String key = colorsToCheck.entrySet().stream().findFirst().get().getKey();
            Map<String, Long> innerBags = getInnerBags(bagTypes, key);
            AtomicLong pairSum = new AtomicLong();
            innerBags.forEach((color, number) -> {
                long actualNumber = number * colorsToCheck.get(key);
                pairSum.addAndGet(number);
                colorsToCheck.put(color, actualNumber);
                if(actualValues.get(color) != null) {
                    actualValues.replace(color, actualValues.get(color) + actualNumber);
                } else {
                    actualValues.put(color, actualNumber);
                }
            });
            long multiplier = colorsToCheck.get(key) == 0 ? 1 : colorsToCheck.get(key);
            sum += pairSum.get() * multiplier;
            colorsToCheck.remove(key);
        }
        long alternSum = actualValues.values().stream().reduce(0L, Long::sum);
        return sum;
    }

    private Map<String, Map<String, Long>> parseToLong(Map<String, Map<String, String>> bagTypesStrings) {
        Map<String, Map<String, Long>> result = new HashMap<>();
        bagTypesStrings.forEach((bagColor, innerBags) -> {
            Map<String, Long> entry = new HashMap<>();
            innerBags.forEach((color, stringNumber) -> {
                entry.put(color, Long.parseLong(stringNumber));
            });
            result.put(bagColor, entry);
        });
        return result;
    }

    private Map<String, Long> getInnerBags(Map<String, Map<String, Long>> bagTypes, String color) {
        Map<String, Long> result = new HashMap<>();
        bagTypes.get(color).forEach(result::put);
        return result;
    }

}
