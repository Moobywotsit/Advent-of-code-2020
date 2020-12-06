package com.advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<List<Boolean>> track = loadPuzzleInput("/Users/maxjones/Documents/Conor/Advent-of-code/Advent-of-code-2020/Week3/puzzleInput.txt");

        int route1 = getNumberOfTreesOnRoute(1, 1, track);
        int route2 = getNumberOfTreesOnRoute(1, 3, track);
        int route3 = getNumberOfTreesOnRoute(1, 5, track);
        int route4 = getNumberOfTreesOnRoute(1, 7, track);
        int route5 = getNumberOfTreesOnRoute(2, 1, track);


        System.out.println(((long)route1 * route2 * route3 * route4 * route5));
    }

    private static int getNumberOfTreesOnRoute(int down, int right, List<List<Boolean>> track){
        int numTrees = 0;
        int currX = 0;

        for(int rowNum = down; rowNum < track.size(); rowNum += down){
            List<Boolean> row = track.get(rowNum);

            currX += right;
            if(currX >= row.size()){
                currX = currX - row.size();
            }

            if(row.get(currX)){
                numTrees ++;
            }
        }

        return numTrees;
    }

    private static List<List<Boolean>> loadPuzzleInput(String filename) {
        List<List<Boolean>> entries = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                entries.add(checkLineForTrees(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return entries;
    }

    private static List<Boolean> checkLineForTrees(String trees){
        List<Boolean> entries = new ArrayList<>(trees.length());
        for(char entry : trees.toCharArray()){
            entries.add(entry == '#');
        }

        return entries;
    }
}
