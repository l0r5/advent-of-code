package day1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    Day1 day1;

    @BeforeEach
    void init() {
        day1 = new Day1();
    }

    @Test
    void testReadFile_containsNumbers() {
        List<Integer> data = day1.readNumbers();
        assertFalse(data.isEmpty());
    }

    @Test
    void testCalcPair_deliversPair() {
        List<Integer> data = day1.readNumbers();
        List<Integer> pair = day1.calcPair(data);
        assertFalse(pair.isEmpty());
        assertEquals(2, pair.size());
        assertEquals(2020, pair.get(0) + pair.get(1));
    }

    @Test
    void testCalcFinalResult_moreThanOnePair() {
        List<Integer> data = day1.readNumbers();
        List<Integer> pair = day1.calcPair(data);
        pair.add(3);
        assertFalse(pair.isEmpty());
        assertEquals(3, pair.size());
        assertEquals(0, day1.calcFinalResult(pair));
    }

    @Test
    void testCalcFinalResult_exactlyOnePair() {
        List<Integer> pair  = new ArrayList<>();
        pair.add(1000);
        pair.add(1020);
        assertFalse(pair.isEmpty());
        assertEquals(2, pair.size());
        assertEquals(1020000, day1.calcFinalResult(pair));
    }

    @Test
    void testGetResultDay1_successful() {
        int result = day1.getResult();
        assert(result != 0);
        assertEquals(858496, day1.getResult());
    }
}