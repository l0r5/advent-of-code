package day3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Day3Test {

    Day3 day3;

    @BeforeEach
    void init() {
        day3 = new Day3();
    }

    @Test
    void testReadFile_contentAvailable() {
        List<String> data = day3.readFile();
        assertFalse(data.isEmpty());
    }

    @Test
    void testCalcTrees_numberOfTreesZero() {
        List<String> data = new ArrayList<>();
        data.add("..##.........##.........##.........##.........##.........##.......");
        data.add("#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..");
        assertEquals(0,  day3.calcTrees(data));
    }

    @Test
    void testCalcTrees_numberOfTreesNotZero() {
        List<String> data = new ArrayList<>();
        data.add("..##.........##.........##.........##.........##.........##.......");
        data.add("#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..");
        data.add(".#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.");
        data.add("..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#");
        data.add(".#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.");
        data.add("..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....");
        data.add(".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#");
        data.add(".#........#.#........#.#........#.#........#.#........#.#........#");
        data.add("#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...");
        data.add("#...##....##...##....##...##....##...##....##...##....##...##....#");
        data.add(".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#");
        assertEquals(7,  day3.calcTrees(data));
    }

    @Test
    void testCalcTrees_numberOfTreesRealValues() {
        List<String> data = day3.readFile();
        assertEquals(268,  day3.calcTrees(data));
    }

    @Test
    void testCalcTrees_numberOfTreesDynamicallyRealValues() {
        List<String> data = day3.readFile();
        assertEquals(68,  day3.calcTreesDynamically(data, 1, 1));
        assertEquals(268,  day3.calcTreesDynamically(data, 3, 1));
        assertEquals(73,  day3.calcTreesDynamically(data, 5, 1));
        assertEquals(75,  day3.calcTreesDynamically(data, 7, 1));
        assertEquals(31,  day3.calcTreesDynamically(data, 1, 2));
    }

    @Test
    void testCalcResult_returnsResult() {
        long result = day3.calcResult();
        assertEquals(3093068400L,  result);
    }
}