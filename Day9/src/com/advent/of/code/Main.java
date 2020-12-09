package com.advent.of.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double number = 25918798;
	    List<Double> numbers = loadFile("/Users/javaUser/Documents/Conor/Advent-of-code/Advent-of-code-2020/Day9/fileInput.txt");

        for(int i = 0; i < numbers.size(); i++){
            double accumulatedNumber = numbers.get(i);
            List<Double> numbersUsed = new ArrayList<>();
            numbersUsed.add(accumulatedNumber);

            int tempIndex = i;
            while(accumulatedNumber < number){
                tempIndex++;
                double currentNumber = numbers.get(tempIndex);
                numbersUsed.add(currentNumber);
                accumulatedNumber += currentNumber;

                if(accumulatedNumber == number && numbersUsed.size() > 1){
                    double largestNumber = 0;
                    double  smallestNumber = numbersUsed.get(0);

                    for(double curr : numbersUsed){
                        System.out.println(curr);
                        if(largestNumber < curr)
                            largestNumber = curr;

                        if(smallestNumber > curr)
                            smallestNumber = curr;
                    }

                    System.out.println(smallestNumber + largestNumber);
                }
            }
        }
    }

    private static List<Double> loadFile(String filename) {
        List<Double> numbers = new ArrayList<>();

        try {
            File myFile = new File(filename);
            Scanner fileReader = new Scanner(myFile);
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                numbers.add(Double.valueOf(row));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return numbers;
    }
}
