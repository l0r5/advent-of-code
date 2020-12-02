package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {



    protected long countValidPasswordsNew(){
        List<String> pwList = readFile();
        return (int) pwList.stream().filter(this::checkPasswordIsValidNew).count();
    }



    protected long countValidPasswordsOld(){
        List<String> pwList = readFile();
        return (int) pwList.stream().filter(this::checkPasswordIsValidOld).count();
    }

    protected List<String> readFile() {
        List<String> inputPasswords = new ArrayList<>();
        try {
            File myObj = new File("src/main/resources/day2_password_list.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputPasswords.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred during.");
            e.printStackTrace();
        }
        System.out.println("Data was read successfully.");
        return inputPasswords;
    }


    protected boolean checkPasswordIsValidOld(String testPassword) {
        String[] pwDivided = testPassword.split(":");
        //                13-17f
        String pw = pwDivided[1].trim();
        String criteria = pwDivided[0].trim();
        //                ffffffffffffrfffff
        String letter = criteria.substring(criteria.length() - 1);
        criteria = criteria.substring(0, criteria.length() - 1);
        String[] minMax = criteria.split("-");
        int min = Integer.parseInt(minMax[0].trim());
        int max = Integer.parseInt(minMax[1].trim());
        long hits = countLetterInPw(pw, letter);
        return hits >= min && hits <= max;
    }

    protected boolean checkPasswordIsValidNew(String testPassword) {


        // position 1 und 2 rausfinden --
        // string in char array umwandeln
        // allowedNumberOfOcc muss 1

        String[] pwDivided = testPassword.split(":");
        //                13-17f
        String pw = pwDivided[1].trim();
        String criteria = pwDivided[0].trim();
        //                ffffffffffffrfffff
        String letter = criteria.substring(criteria.length() - 1);
        criteria = criteria.substring(0, criteria.length() - 1);
        String[] minMax = criteria.split("-");
        int first = Integer.parseInt(minMax[0].trim());
        int second = Integer.parseInt(minMax[1].trim());
        //        7-8 f: wpgfvvzb
        boolean isFirstEqual = letter.charAt(0) == pw.charAt(first-1);
        boolean isSecondEqual = letter.charAt(0) == pw.charAt(second-1);
        int hitCount1 = isFirstEqual ? 1 : 0;
        int hitCount2 = isSecondEqual ? 1 : 0;

        if(hitCount1 + hitCount2 == 1) {
            return true;
        }
        return false;
    }

    protected long countLetterInPw(String password, String letter) {
        return password.chars().filter(c -> c == letter.charAt(0)).count();
    }
}