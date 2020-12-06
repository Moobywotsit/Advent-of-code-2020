package com.advent.of.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> planeRows = loadFile("/Users/javaUser/Documents/Conor/Advent-of-code/Advent-of-code-2020/Day5/inputData.txt");

        PlaneCalculator planeCalculator = new PlaneCalculator();

        List<Long> seats = new ArrayList<>();
        for (String seatNotation : planeRows) {
            long currSeatID = planeCalculator.getSeatID(seatNotation);
            seats.add(currSeatID);
        }

        Collections.sort(seats);

        for(int i = 1; i < seats.size() -1; i++){
            long seatUnderTest = seats.get(i);
            long left = seats.get(i-1);
            long right = seats.get(i+1);

            if(seatUnderTest - left != 1)
                System.out.println(seatUnderTest-1);
            if(seatUnderTest - right != -1)
                System.out.println(seatUnderTest+1);

        }
    }

    private static List<String> loadFile(String filename) {
        List<String> rows = new ArrayList<>();

        try {
            File myFile = new File(filename);
            Scanner fileReader = new Scanner(myFile);
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                rows.add(row);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return rows;
    }
}
