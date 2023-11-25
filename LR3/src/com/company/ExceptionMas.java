package com.company;

public class ExceptionMas extends Exception {

    private int number;
    private String messenge;

    public int getNumber() {
        return number;
    }
    public String getMessenge() {
        return messenge;
    }

    public ExceptionMas() {

        messenge = "Номер находиться вне диапазона!";
        number = 0;
    }
}