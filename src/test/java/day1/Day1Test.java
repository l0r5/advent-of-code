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
    void testCalcTriple_deliversTriple() {
        List<Integer> data = day1.readNumbers();
        List<Integer> triple = day1.calcTriple(data);
        assertFalse(triple.isEmpty());
        assertEquals(3, triple.size());
        assertEquals(2020, triple.get(0) + triple.get(1) + triple.get(2));
    }

    @Test
    void testCalcFinalResult_moreThanOnePairOrTuple() {
        List<Integer> data = day1.readNumbers();
        List<Integer> tuple = day1.calcPair(data);
        tuple.add(3);
        tuple.add(3);
        assertFalse(tuple.isEmpty());
        assertEquals(4, tuple.size());
        assertEquals(-1, day1.calcFinalResult(tuple));
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
    void testCalcFinalResult_exactlyOneTriple() {
        List<Integer> triple  = new ArrayList<>();
        triple.add(1000);
        triple.add(1000);
        triple.add(20);
        assertFalse(triple.isEmpty());
        assertEquals(3, triple.size());
        assertEquals(20000000, day1.calcFinalResult(triple));
    }

    @Test
    void testGetResultDay1_successful() {
        String result = day1.getResult();
        assertNotNull(result);
        assertNotEquals("", result);
        assertEquals("858496,98721392", result);
    }
}