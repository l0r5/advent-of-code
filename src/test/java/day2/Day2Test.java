package day2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day2Test {

    Day2 day2;

    @BeforeEach
    void init() {
        day2 = new Day2();
    }

    @Test
    void testReadFile_containsPasswords() {
        List<String> data = day2.readFile();
        assertFalse(data.isEmpty());
    }

    @Test
    void testCheckPasswordValidOld_isValid() {
        String testPassword = "5-7 g: mghggkgg";
        assertTrue(day2.checkPasswordIsValidOld(testPassword));
    }

    @Test
    void testCheckPasswordValidOld_isInvalid() {
        String testPassword = "5-7 g: ggkgg";
        assertFalse(day2.checkPasswordIsValidOld(testPassword));
    }

    @Test
    void testCountLetterInPw_getNumber() {
        String pw = "mghggkgg";
        String letter = "g";
        assertEquals(5, day2.countLetterInPw(pw, letter));
    }

    @Test
    void testCountValidPasswordsOld_getNumber() {
        assertEquals(469, day2.countValidPasswordsOld());
    }

    @Test
    void testCheckPasswordValidNew_isValid() {
        String testPassword = "5-7 g: mghggkkg";
        assertTrue(day2.checkPasswordIsValidNew(testPassword));
    }

    @Test
    void testCheckPasswordValidNew_isInvalid() {
        String testPassword = "5-7 g: mghggkgg";
        assertFalse(day2.checkPasswordIsValidNew(testPassword));
    }

    @Test
    void testCountValidPasswordsNew_getNumber() {
        assertEquals(267, day2.countValidPasswordsNew());
    }
}