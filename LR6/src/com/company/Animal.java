package com.company;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public interface Animal extends Iterable<Integer>{
    String getName();

    double getWeight();

    int getScoresLen();

    int getOneScore(int k);

    void setOneScore(int k, int sco);

    int totalScore();

    void output(OutputStream var1) throws Exception;

    void write(Writer var1);

    Iterator<Integer> iterator();

}
