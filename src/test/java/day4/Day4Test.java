package day4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day4Test {

    Day4 day4;

    @BeforeEach
    void init() {
        day4 = new Day4();
    }

    @Test
    void testReadFile_contentAvailable() {
        List<String> data = day4.readFile();
        assertFalse(data.isEmpty());
    }

    @Test
    void testFormatPassList_formattedInOneLinePerSet() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm");
        expectedResult.add("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929");
        expectedResult.add("hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm");
        expectedResult.add("hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in");
        List<String> unformattedPassList = new ArrayList<>();
        unformattedPassList.add("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd");
        unformattedPassList.add("byr:1937 iyr:2017 cid:147 hgt:183cm");
        unformattedPassList.add("");
        unformattedPassList.add("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884");
        unformattedPassList.add("hcl:#cfa07d byr:1929");
        unformattedPassList.add("");
        unformattedPassList.add("hcl:#ae17e1 iyr:2013");
        unformattedPassList.add("eyr:2024");
        unformattedPassList.add("ecl:brn pid:760753108 byr:1931");
        unformattedPassList.add("hgt:179cm");
        unformattedPassList.add("");
        unformattedPassList.add("hcl:#cfa07d eyr:2025 pid:166559648");
        unformattedPassList.add("iyr:2011 ecl:brn hgt:59in");
        List<String> actualResult = day4.formatPassList(unformattedPassList);
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void testProducePassResultList_formattedInMaps() {
        Map<String, String> firstPass = new HashMap<>();
        firstPass.put("ecl","gry");
        firstPass.put("pid","860033327");
        firstPass.put("eyr","2020");
        firstPass.put("hcl","#fffffd");
        firstPass.put("byr","1937");
        firstPass.put("iyr","2017");
        firstPass.put("cid","147");
        firstPass.put("hgt","183cm");
        Map<String, String> secondPass = new HashMap<>();
        secondPass.put("iyr","2013");
        secondPass.put("ecl","amb");
        secondPass.put("cid","350");
        secondPass.put("eyr","2023");
        secondPass.put("pid","028048884");
        secondPass.put("hcl","#cfa07d");
        secondPass.put("byr","1929");
        List<Map<String, String>> expectedResult = new ArrayList<>();
        expectedResult.add(firstPass);
        expectedResult.add(secondPass);
        List<String> testPassList = new ArrayList<>();
        testPassList.add("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm");
        testPassList.add("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929");
        List<Map<String, String>> actualResult = day4.producePassResultList(testPassList);
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void testCreatePass_passAsMap() {
        Map<String, String> expectedPass = new HashMap<>();
        expectedPass.put("ecl","gry");
        expectedPass.put("pid","860033327");
        expectedPass.put("eyr","2020");
        expectedPass.put("hcl","#fffffd");
        expectedPass.put("byr","1937");
        expectedPass.put("iyr","2017");
        expectedPass.put("cid","147");
        expectedPass.put("hgt","183cm");
        String testLine = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm";
        Map<String, String> result = day4.createPass(testLine);
        assertEquals(expectedPass.toString(), result.toString());
    }

    @Test
    void testIsPassValid_isInvalid() {
        String testPass = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929";
        assertFalse(day4.isPassValid(day4.createPass(testPass)));
    }

    @Test
    void testIsPassValid_isValid() {
        String testPass = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm";
        assertTrue(day4.isPassValid(day4.createPass(testPass)));
    }

    @Test
    void testCountValidPasses_numberOfValidPasses() {
        List<String> unformattedPassList = new ArrayList<>();
        unformattedPassList.add("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd");
        unformattedPassList.add("byr:1937 iyr:2017 cid:147 hgt:183cm");
        unformattedPassList.add("");
        unformattedPassList.add("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884");
        unformattedPassList.add("hcl:#cfa07d byr:1929");
        unformattedPassList.add("");
        unformattedPassList.add("hcl:#ae17e1 iyr:2013");
        unformattedPassList.add("eyr:2024");
        unformattedPassList.add("ecl:brn pid:760753108 byr:1931");
        unformattedPassList.add("hgt:179cm");
        unformattedPassList.add("");
        unformattedPassList.add("hcl:#cfa07d eyr:2025 pid:166559648");
        unformattedPassList.add("iyr:2011 ecl:brn hgt:59in");
        int result = day4.countValidPasses(day4.producePassResultList(day4.formatPassList(unformattedPassList)));
        assertEquals(2, result);
    }

    @Test
    void testCountValidPasses_numberOfValidPassesRealData() {
        List<String> data = day4.readFile();
        int result = day4.countValidPasses(day4.producePassResultList(day4.formatPassList(data)));
        assertEquals(237, result);
    }

    @Test
    void testIsPassValidStrict_invalidPassport() {
        String testPass = "eyr:1972 cid:100 hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926";
        assertFalse(day4.isPassValidStrict(day4.createPass(testPass)));
        testPass = "iyr:2019 hcl:#602927 eyr:1967 hgt:170cm ecl:grn pid:012533040 byr:1946";
        assertFalse(day4.isPassValidStrict(day4.createPass(testPass)));

    }
}