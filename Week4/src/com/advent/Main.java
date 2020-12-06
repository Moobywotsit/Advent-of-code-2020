package com.advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) {
        List<List<String>> passports = loadFile("/Users/maxjones/Documents/Conor/Advent-of-code/Advent-of-code-2020/Week4/file1.txt");

        List<String> requiredValues = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

        int numberOfValidPassports = 0;

        for(List<String> passport : passports){
            List<String> foundAndValidKeys = new ArrayList<>();

            for(String keyValue : passport){
                String[] keyValueSplit = keyValue.split(":");
                String key = keyValueSplit[0];
                String value = keyValueSplit[1];
                if(isValid(key, value)){
                    foundAndValidKeys.add(key);
                }else{
                    System.out.println(key + " " + value);
                }
            }

            if(foundAndValidKeys.containsAll(requiredValues))
                numberOfValidPassports++;
        }

        System.out.println(numberOfValidPassports);
    }

    private static boolean isValid(String key, String value){
        if(key.equals("byr"))
        {
            //four digits; at least 1920 and at most 2002.
            try{
                int age = Integer.valueOf(value);
                return age >= 1920 && age <= 2002;
            }catch (NumberFormatException nfe){
                System.out.println(nfe.getMessage());
                return false;
            }
        }
        else if(key.equals("iyr"))
        {
            //our digits; at least 2010 and at most 2020.
            try{
                int year = Integer.valueOf(value);
                return year >= 2010 && year <= 2020;
            }catch (NumberFormatException nfe){
                System.out.println(nfe.getMessage());
                return false;
            }
        }
        else if(key.equals("eyr"))
        {
            //four digits; at least 2020 and at most 2030.
            try{
                int year = Integer.valueOf(value);
                return year >= 2020 && year <= 2030;
            }catch (NumberFormatException nfe){
                System.out.println(nfe.getMessage());
                return false;
            }
        }
        else if(key.equals("hgt"))
        {
            //a number followed by either cm or in:
            //If cm, the number must be at least 150 and at most 193.
            //If in, the number must be at least 59 and at most 76.
            try{
                if(value.length() < 4){
                    return false;
                }
                String unit = value.substring(value.length() - 2);
                int heightValue = Integer.valueOf(value.substring(0, value.length() -2));
                if(unit.equals("cm")){
                    return heightValue >= 150 && heightValue <= 193;
                }else if(unit.equals("in")){
                    return heightValue >= 59 && heightValue <= 76;
                }else{
                    return false;
                }
            }catch (NumberFormatException nfe){
                System.out.println(nfe.getMessage());
                return false;
            }
        }
        else if(key.equals("hcl"))
        {
            //a # followed by exactly six characters 0-9 or a-f.
            return value.matches("^[#][a-f0-9]{6}$");
        }
        else if(key.equals("ecl"))
        {
            //exactly one of: amb blu brn gry grn hzl oth.
            return value.equals("amb") || value.equals("blu") || value.equals("brn") || value.equals("gry")
                    || value.equals("grn") || value.equals("hzl") || value.equals("oth");
        }
        else if(key.equals("pid"))
        {
            //a nine-digit number, including leading zeroes
            return value.length() == 9;
        }
        else{
            return false;
        }
    }

    private static List<List<String>> loadFile(String filename) {
        List<List<String>> entries = new ArrayList<>();

        List<String> passportEntries = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                if(!line.isEmpty()){
                    String[] keyValues = line.split(" ");
                    for(String keyValue : keyValues){
                        passportEntries.add(keyValue);
                    }
                }

                //assure last line is added
                if(!myReader.hasNextLine() || line.isEmpty()){
                    entries.add(passportEntries);
                    passportEntries = new ArrayList<>();
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
