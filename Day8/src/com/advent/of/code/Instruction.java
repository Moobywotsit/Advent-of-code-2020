package com.advent.of.code;

public class Instruction {
    Operations operation;
    int value;

    Instruction(Operations operation, int value){
        this.value = value;
        this.operation = operation;
    }
}
