package day5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Day5Test {

    Day5 day5;

    @BeforeEach
    void init() {
        day5 = new Day5();
    }

    @Test
    void testReadFile_contentAvailable() {
        List<String> data = day5.readFile();
        assertFalse(data.isEmpty());
    }

    @Test
    void testCalcSeatId_seatRowCol() {
        String testPass = "BFFFBBFRRR";
        assertEquals(567, day5.calcSeat(testPass,0 , 127, 0,7));
    }

    @Test
    void testCalcAllSeatIds_ids() {
        List<String> input = new ArrayList<>();
        input.add("BFFFBBFRRR");
        input.add("FFFBBBFRRR");
        input.add("BBFFBBFRLL");
        assertEquals(567, day5.calcAllSeatIds(input).get(0));
        assertEquals(119, day5.calcAllSeatIds(input).get(1));
        assertEquals(820, day5.calcAllSeatIds(input).get(2));
    }

    @Test
    void testGetHighestId_ids() {
        List<String> input = new ArrayList<>();
        input.add("BFFFBBFRRR");
        input.add("FFFBBBFRRR");
        input.add("BBFFBBFRLL");
        assertEquals(820, day5.getHighestId(day5.calcAllSeatIds(input)));
    }

    @Test
    void testGetHighestIdResult_ids() {
        List<String> input = day5.readFile();
        assertEquals(991, day5.getHighestId(day5.calcAllSeatIds(input)));
    }
    @Test
    void testCalcMySeatId_myId() {
        List<String> input = day5.readFile();
        assertEquals(534, day5.calcMySeatId(input));
    }


}