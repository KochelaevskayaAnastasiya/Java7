package com.company;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public class UnmodifiableAnimal implements Animal {

    private Animal animal;

    public UnmodifiableAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getName() {
        return animal.getName();
    }

    public double getWeight() {
        return animal.getWeight();
    }

    public int getScoresLen() {return animal.getScoresLen();}

    public int getOneScore(int k){return animal.getOneScore(k);}

    public void setOneScore(int k, int sco){throw new UnsupportedOperationException("Недопустимо изменение объекта"); }

    public int totalScore(){return animal.totalScore();}

    public void output(OutputStream var1) throws Exception{animal.output(var1);}

    public void write(Writer var1){animal.write(var1);}

    public Iterator<Integer> iterator(){return animal.iterator();}
}
