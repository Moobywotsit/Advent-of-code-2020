package com.advent.of.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Instruction> instructions = readFile("/Users/javaUser/Documents/Conor/Advent-of-code/Advent-of-code-2020/Day8/fileInput.txt");

        for(int i = 0; i<  instructions.size(); i++){
            int acc = 0;
            if(instructions.get(i).operation == Operations.JMP){
                instructions.get(i).operation = Operations.NOP;
                try{
                    acc = tryChangedInstruction(instructions);
                }catch(Exception e){

                }

                instructions.get(i).operation = Operations.JMP;
            }

            if(instructions.get(i).operation == Operations.NOP){
                instructions.get(i).operation = Operations.JMP;
                instructions.get(i).operation = Operations.NOP;

                try{
                    acc = tryChangedInstruction(instructions);
                }catch(Exception e){

                }

                instructions.get(i).operation = Operations.NOP;
            }

            System.out.println(acc);
        }
    }

    public static int tryChangedInstruction(List<Instruction> instructions) throws Exception {
        int accumulator = 0;
        boolean repeatInstruction = false;
        int index = 0;
        List<Instruction> readInstruction = new ArrayList<>();

        while(index != instructions.size() && !repeatInstruction){
            Instruction currInstruction = instructions.get(index);

            if(readInstruction.contains(currInstruction))
                throw new Exception();

            readInstruction.add(currInstruction);

            if(currInstruction.operation == Operations.ACC){
                accumulator += currInstruction.value;
                index++;
            }else if(currInstruction.operation == Operations.JMP){
                index += currInstruction.value;
            }else if(currInstruction.operation == Operations.NOP){
                index++;
            }else{
                throw new Exception();
            }
        }

        return accumulator;
    }

    public static List<Instruction> readFile(String filename){
        List<Instruction> instructions = new ArrayList<>();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                if (!line.isEmpty()) {
                    String[] splitLine = line.split(" ");
                    String operationText = splitLine[0];
                    Operations operation = Operations.valueOf(operationText.toUpperCase());

                    int address = Integer.valueOf(splitLine[1]);

                    instructions.add(new Instruction(operation, address));
                }

            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return instructions;
    }
}

//nop +0
//acc +1
//jmp +4
//acc +3
//jmp -3
//acc -99
//acc +1
//jmp -4
//acc +6