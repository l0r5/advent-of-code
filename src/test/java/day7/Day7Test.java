package day7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Day7Test {

    Day7 day7;

    @BeforeEach
    void init() {
        day7 = new Day7();
    }

    @Test
    void testReadFile_contentAvailable() {
        List<String> data = day7.readFile();
        assertFalse(data.isEmpty());
    }

    @Test
    void testGetBagTypes_returnBagTypesAsMap() {
        List<String> testInput = new ArrayList<>();
        testInput.add("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        testInput.add("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        testInput.add("bright white bags contain 1 light red bag.");

        Map<String, String> redContaining = new HashMap<>();
        redContaining.put("bright white", "1");
        redContaining.put("muted yellow", "2");
        Map<String, String>orangeContaining = new HashMap<>();
        orangeContaining.put("bright white", "3");
        orangeContaining.put("muted yellow", "4");
        Map<String, String> whiteContaining = new HashMap<>();
        whiteContaining.put("light red", "1");

        Map<String, Map<String, String>> expectedResult = new HashMap<>();
        expectedResult.put("light red", redContaining);
        expectedResult.put("dark orange", orangeContaining);
        expectedResult.put("bright white", whiteContaining);

        assertEquals(expectedResult, day7.getBagTypes(testInput));
    }

    @Test
    void testCalculateBiggerBags_returnBagsThatCanContainColor() {
        List<String> testInput = new ArrayList<>();
        testInput.add("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        testInput.add("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        testInput.add("bright white bags contain 1 light red bag.");

        Map<String, String> redContaining = new HashMap<>();
        redContaining.put("bright white", "1");
        redContaining.put("muted yellow", "2");
        Map<String, String>orangeContaining = new HashMap<>();
        orangeContaining.put("bright white", "3");
        orangeContaining.put("muted yellow", "4");

        Map<String, Map<String, String>> expectedResult = new HashMap<>();
        expectedResult.put("light red", redContaining);
        expectedResult.put("dark orange", orangeContaining);

        assertEquals(expectedResult, day7.calculateBiggerBags(day7.getBagTypes(testInput),  "bright white"));
    }

    @Test
    void testGetDirectContainers_returnExample() {
        List<String> testInput = new ArrayList<>();
        testInput.add("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        testInput.add("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        testInput.add("bright white bags contain 1 shiny gold bag.");
        testInput.add("muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.");
        testInput.add("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.");
        testInput.add("dark olive bags contain 3 faded blue bags, 4 dotted black bags.");
        testInput.add("vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.");
        testInput.add("faded blue bags contain no other bags.");
        testInput.add("dotted black bags contain no other bags.");

        List<String> colorsToFind = Arrays.asList("shiny gold");

        assertEquals("{muted yellow={shiny gold=2, faded blue=9}, bright white={shiny gold=1}}", day7.getDirectContainers(day7.getBagTypes(testInput),  colorsToFind).toString());
    }

    @Test
    void testCalculateBiggerBags_returnExample() {
        List<String> testInput = new ArrayList<>();
        testInput.add("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        testInput.add("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        testInput.add("bright white bags contain 1 shiny gold bag.");
        testInput.add("muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.");
        testInput.add("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.");
        testInput.add("dark olive bags contain 3 faded blue bags, 4 dotted black bags.");
        testInput.add("vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.");
        testInput.add("faded blue bags contain no other bags.");
        testInput.add("dotted black bags contain no other bags.");

        assertEquals("{muted yellow={shiny gold=2, faded blue=9}, light red={muted yellow=2, bright white=1}, bright white={shiny gold=1}, dark orange={muted yellow=4, bright white=3}}", day7.calculateBiggerBags(day7.getBagTypes(testInput),  "shiny gold").toString());
    }

    @Test
    void testCalculateBiggerBags_withRealDataReturnResult() {
        List<String> data = day7.readFile();

        long result = day7.getNumberOfBagsBigger(day7.calculateBiggerBags(day7.getBagTypes(data),"shiny gold"));
        assertEquals(326, result);
    }

    @Test
    void testCountBagsInside_withTestData1() {
        List<String> testInput = new ArrayList<>();
        testInput.add("shiny gold bags contain 2 dark red bags.");
        testInput.add("dark red bags contain 2 dark orange bags.");
        testInput.add("dark orange bags contain 2 dark yellow bags.");
        testInput.add("dark yellow bags contain 2 dark green bags.");
        testInput.add("dark green bags contain 2 dark blue bags.");
        testInput.add("dark blue bags contain 2 dark violet bags.");
        testInput.add("dark violet bags contain no other bags.");

        long result = day7.countBagsInside(day7.getBagTypes(testInput), "shiny gold");
        assertEquals(126, result);
    }

    @Test
    void testCountBagsInside_withTestData2() {
        List<String> testInput = new ArrayList<>();
        testInput.add("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        testInput.add("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        testInput.add("bright white bags contain 1 shiny gold bag.");
        testInput.add("muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.");
        testInput.add("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.");
        testInput.add("dark olive bags contain 3 faded blue bags, 4 dotted black bags.");
        testInput.add("vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.");
        testInput.add("faded blue bags contain no other bags.");
        testInput.add("dotted black bags contain no other bags.");


        long result = day7.countBagsInside(day7.getBagTypes(testInput), "shiny gold");
        assertEquals(32, result);
    }

    @Test
    void testCountBagsInside_withRealDate() {
        List<String> data = day7.readFile();
        long result = day7.countBagsInside(day7.getBagTypes(data), "shiny gold");
        assertEquals(3345, result);
    }

}