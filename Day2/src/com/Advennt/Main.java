package com.Advennt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<String> entries = loadEntries("C:/Users/small/Documents/Conor/AdventOfCode/Advent-of-code-2020/Week2/entries.txt");

        //17-19 p: pwpzpfbrcpppjppbmppp
        //need to check if it has 17-19 p's return true or false
        int numberOfValidPasswords = 0;
        for(String entry : entries){
            if(isNewValidPassword(entry)){
                numberOfValidPasswords ++;
            }
        }
        System.out.println(numberOfValidPasswords);
    }

    private static boolean isNewValidPassword(String password){
        String[] splitPassword = splitPasswordIntoComponents(password);

        int firstValidLocation = Integer.parseInt(splitPassword[0].split("-")[0]);
        int secondValidLoaction = Integer.parseInt(splitPassword[0].split("-")[1]);

        char characterToCheckFor = splitPassword[1].charAt(0);

        char[] passwordCharacters = splitPassword[2].toCharArray();

        return passwordCharacters[firstValidLocation -1] == characterToCheckFor ^ passwordCharacters[secondValidLoaction -1] == characterToCheckFor;
    }

    private static boolean isOldValidPassword(String password){
        String[] splitPassword = splitPasswordIntoComponents(password);

        int minBound = Integer.parseInt(splitPassword[0].split("-")[0]);
        int maxBound = Integer.parseInt(splitPassword[0].split("-")[1]);

        System.out.println(minBound);
        System.out.println(maxBound);

        char characterToCheckFor = splitPassword[1].charAt(0);

        System.out.println(characterToCheckFor);

        char[] passwordCharacters = splitPassword[2].toCharArray();

        int numberOfCharacter = 0;
        for(char currChar : passwordCharacters){
            if(currChar == characterToCheckFor){
                numberOfCharacter ++;
            }
        }

        return numberOfCharacter >= minBound && numberOfCharacter <= maxBound;
    }

    private static String[] splitPasswordIntoComponents(String password){
        return password.split(" ");
    }

    private static List<String> loadEntries(String filename) {
        List<String> entries = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                entries.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return entries;
    }
}
