package com.christmas;

import sun.awt.X11.XSystemTrayPeer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Integer> entries = readEntriesFromFile("/home/conor.watson/Documents/AdventOfCode/Week1/ActivityOne/entries.txt");
        assert entries.size() > 0;

        entries.forEach(entry -> {
            entries.forEach(innerEntry -> {
                entries.forEach(innerInnerEntry -> {
                    if(entry + innerEntry + innerInnerEntry == 2020){
                        System.out.println(entry * innerEntry * innerInnerEntry);
                    }
                });
            });
        });

    }

    public static Set<Integer> readEntriesFromFile(String fileLocation){
        Set<Integer> entries = new HashSet<>();
        try {
            File myObj = new File(fileLocation);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                entries.add(Integer.valueOf(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return entries;
    }
}
