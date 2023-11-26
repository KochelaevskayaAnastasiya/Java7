package com.company;

import java.util.Random;

public class WriteThread extends Thread {
    Animal animal;

    public WriteThread(Animal animal) {
        this.animal =animal;
    }

    public void run() {
        for (int i=0; i<animal.getScoresLen();i++)
        {
            Random rnd = new Random();
            animal.setOneScore(i, rnd.nextInt(100));
            System.out.println("Write: " + animal.getOneScore(i) + " to position " + i);
        }
    }
}
