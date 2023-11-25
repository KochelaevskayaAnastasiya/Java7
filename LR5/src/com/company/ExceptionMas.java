package com.company;

public class ExceptionMas extends Exception {

    private int number;

    public int getNumber() {
        return number;
    }

    public ExceptionMas() {

        super("Номер находиться вне диапазона!");
        number = 0;
    }
}