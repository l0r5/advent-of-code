package day6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Day6Test {

    Day6 day6;

    @BeforeEach
    void init() {
        day6 = new Day6();
    }

    @Test
    void testReadFile_contentAvailable() {
        List<String> data = day6.readFile();
        assertFalse(data.isEmpty());
    }

    @Test
    void testCalcFinalSum_sumIsCorrect() {
        List<String> testInput = new ArrayList<>();
        testInput.add("abc");
        testInput.add("");
        testInput.add("a");
        testInput.add("b");
        testInput.add("c");
        testInput.add("");
        testInput.add("ab");
        testInput.add("ac");
        testInput.add("");
        testInput.add("a");
        testInput.add("a");
        testInput.add("a");
        testInput.add("a");
        testInput.add("");
        testInput.add("b");
        assertEquals(11, day6.calcFinalSum(testInput));
    }

    @Test
    void testCalcFinalSum_realInput_sumIsCorrect() {
        List<String> data = day6.readFile();
        assertEquals(6273, day6.calcFinalSum(data));
    }

    @Test
    void testCompareLines_listOfEquals() {
        List<Character> matches = new ArrayList<>();
        matches.add('a');
        matches.add('b');
        String line = "ac";
        List<Character> expectedResult = new ArrayList<>();
        expectedResult.add('a');

        assertEquals(expectedResult, day6.compareLines(matches, line));
    }

    @Test
    void testCalcFinalSumOnlyEquals_sumIsCorrect() {
        List<String> testInput = new ArrayList<>();
        testInput.add("abc");
        testInput.add("");
        testInput.add("a");
        testInput.add("b");
        testInput.add("c");
        testInput.add("");
        testInput.add("ab");
        testInput.add("ac");
        testInput.add("");
        testInput.add("a");
        testInput.add("a");
        testInput.add("a");
        testInput.add("a");
        testInput.add("");
        testInput.add("b");
        testInput.add("");
        testInput.add("abc");
        testInput.add("c");
        testInput.add("bc");
        testInput.add("acb");
        testInput.add("");
        testInput.add("ba");
        testInput.add("ab");
        testInput.add("aabb");

        assertEquals(9, day6.calcFinalSumOnlyEquals(testInput));
    }

    @Test
    void testCalcFinalSumOnlyEquals_realInput_sumIsCorrect() {
        List<String> data = day6.readFile();
        assertEquals(3254, day6.calcFinalSumOnlyEquals(data));
    }

}