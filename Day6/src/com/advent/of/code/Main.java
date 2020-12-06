package com.advent.of.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<List<Character>> file = loadFile("/Users/javaUser/Documents/Conor/Advent-of-code/Advent-of-code-2020/Day6/fileInput.txt");

        int sum = 0;
        for(List<Character> group : file){
            sum += group.size();
        }

        System.out.println(sum);
    }

    private static List<List<Character>> loadFile(String filename) {
        List<List<Character>> entries = new ArrayList<>();

        List<List<Character>> personsEntries = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                if(!line.isEmpty()){
                    char[] personsAnswersArray = line.toCharArray();
                    List<Character> personsAnswersList = new ArrayList<>();
                    for(int i = 0; i < personsAnswersArray.length; i++){
                        personsAnswersList.add(personsAnswersArray[i]);
                    }
                    personsEntries.add(personsAnswersList);
                }

                //assure last line is added
                if(!myReader.hasNextLine() || line.isEmpty()){
                    List<Character> matchingCharacters = new ArrayList<>();

                    for(List<Character> personsAnswersList : personsEntries){
                        for(Character personAnswer : personsAnswersList){
                            boolean isValid = true;
                            for(List<Character> otherPersonsAnswersList : personsEntries){
                                if(isValid)
                                    isValid = otherPersonsAnswersList.contains(personAnswer);
                            }
                            if(isValid)
                                if(!matchingCharacters.contains(personAnswer))
                                    matchingCharacters.add(personAnswer);
                        }
                    }
                    entries.add(matchingCharacters);
                    personsEntries = new ArrayList<>();
                }
            }
            myReader.close();


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return entries;
    }
}
