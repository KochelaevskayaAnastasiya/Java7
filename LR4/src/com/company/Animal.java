package com.company;

import java.io.OutputStream;
import java.io.Writer;

public interface Animal {
    String getName();

    double getWeight();

    int getScoresLen();

    int getOneScore(int k);

    int totalScore();

    void output(OutputStream var1) throws Exception;

    void write(Writer var1);

}
