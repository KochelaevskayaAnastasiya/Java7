package com.company;

import java.util.Random;

public class ReadThread extends Thread {
    private Animal animal;

    public ReadThread(Animal animal) {
        this.animal =animal;
    }

    public void run() {
        for (int i=0; i<animal.getScoresLen();i++)
        {
            System.out.println("Read: " + animal.getOneScore(i) + " to position " + i);
        }
    }
}
