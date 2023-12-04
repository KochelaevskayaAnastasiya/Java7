package com.company;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public class SynchronizedAnimal implements Animal{
    private Animal animal;

    public SynchronizedAnimal(Animal animal) {
        this.animal = animal;
    }

    public synchronized String getName() {
        return animal.getName();
    }

    public synchronized double getWeight() {
        return animal.getWeight();
    }

    public synchronized int getScoresLen() {return animal.getScoresLen();}

    public synchronized int getOneScore(int k){return animal.getOneScore(k);}

    public synchronized void setOneScore(int k, int sco){animal.setOneScore(k, sco);}

    public synchronized int totalScore(){return animal.totalScore();}

    public synchronized void output(OutputStream var1) throws Exception{animal.output(var1);}

    public synchronized void write(Writer var1){animal.write(var1);}

    public synchronized Iterator<Integer> iterator(){return animal.iterator();}

}
