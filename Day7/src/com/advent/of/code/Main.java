package com.advent.of.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Map<String, List<Bag>> bags = readFile("/Users/javaUser/Documents/Conor/Advent-of-code/Advent-of-code-2020/Day7/fileInput.txt");

//        for(Map.Entry<String, List<String>> entry : bags.entrySet()){
//                if(containsAShinyGoldBag(entry.getValue(), bags)){
//                    numberOfValidBags++;
//                }
//        }
//        System.out.println(numberOfValidBags);

        List<Bag> shinyGoldBagInnerBags = bags.get("shiny gold");

        int numberOfValidBags = countNumberOfBags(shinyGoldBagInnerBags, bags);
        System.out.println(numberOfValidBags);
    }

    public static int countNumberOfBags(List<Bag> innerBags, Map<String, List<Bag>> bags){
        int numberOfInnerBags = 1;

        for(Bag innerBag : innerBags){
            numberOfInnerBags += innerBag.numberInBag * countNumberOfBags(bags.get(innerBag.colour), bags);
        }

        return numberOfInnerBags;
    }

    public static boolean containsAShinyGoldBag(List<String> colours, Map<String, List<String>> bags){
        for(String bagColour : colours){
            if(bagColour.equals("shiny gold")){
                return true;
            }else{
                if(containsAShinyGoldBag(bags.get(bagColour), bags))
                    return true;
            }
        }
        //contains no colours.
        return false;
    }

    public static Map<String, List<Bag>> readFile(String filename) {
        Map<String, List<Bag>> bags = new HashMap<>();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                if (!line.isEmpty()) {

                    //Colour is always the first 2 words
                    String[] splitLine = line.split(" ");
                    String colour = splitLine[0] + " " + splitLine[1];

                    //check it can have other bags in. if not leave list empty
                    List<Bag> contentOfBagColours = new ArrayList<>();
                    if (!line.contains("no other bags")) {
                        //all instructions have colour then contain then the bags they have. + 8 because that is the length
                        //of the string and indexOf returns the first character int eh substr
                        int startOfContents = line.indexOf("contain ") + 8;

                        String[] splitContents = line.substring(startOfContents).split(",");

                        //one bag that is inside the outer bag
                        for (String content : splitContents) {
                            String[] splitContent = content.strip().split(" ");
                            int numOfCurrBag = Integer.valueOf(splitContent[0]);

                            String nameOfCurrentBag = "";
                            for (int i = 1; i < 3; i++) {
                                nameOfCurrentBag += i != 2 ? splitContent[i] + " " : splitContent[i];
                            }
                            contentOfBagColours.add(new Bag(nameOfCurrentBag, numOfCurrBag));
                        }
                    }else{

                    }

                    bags.put(colour, contentOfBagColours);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return bags;
    }
}
