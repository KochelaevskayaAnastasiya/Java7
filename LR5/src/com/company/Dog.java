package com.company;

import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.nio.ByteBuffer;

public class Dog implements Animal, Serializable {
    private int[] score;
    private String name;
    private double weight;


    public Dog(int[] score, String name, double weight) {
        try {
            if (weight<=0) throw new ExceptionInput("Вес не может быть отрицательным или равным нулю");
            this.score = score;
            this.name = name;
            this.weight = weight;
        }
        catch (ExceptionInput exc)
        {
            throw new ExceptionInput(exc.getMessage());
        }
    }
    public Dog(int score_l, String name, double weight) {
        try {
            if (weight<=0) throw new ExceptionInput("Вес не может быть отрицательным или равным нулю");
            this.score = new int[score_l];
            for (int i=0;i<score_l;i++)
            {
                this.score[i]=0;
            }
            this.name = name;
            this.weight = weight;
        }
        catch (ExceptionInput exc)
        {
            throw new ExceptionInput(exc.getMessage());
        }
    }


    public Dog() {
        this.score = new int[0];
        this.name = "";
        this.weight = 0;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
    public int getScoresLen() {return score.length;}

    public int getOneScore(int k) {
        try {
            if (k>=score.length) throw new ExceptionMas();
            return score[k];
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void setOneScore(int k, int sco) {
        try {
            score[k] = sco;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public int totalScore() {
        int total_score = 0;
        for (int i = 0; i < score.length; i++) {
            total_score += score[i];
        }
        return total_score;
    }
    public void output(OutputStream out) throws Exception {
        out.write(1);
        byte[] byte_name = this.name.getBytes();
        String w = String.valueOf(this.weight);
        byte[] byte_weigth = w.getBytes();
        byte[] byte_name_l = ByteBuffer.allocate(4).putInt(byte_name.length).array();
        out.write(byte_name_l);
        out.write(byte_name);
        byte[] byte_w_l = ByteBuffer.allocate(4).putInt(byte_weigth.length).array();
        out.write(byte_w_l);
        out.write(byte_weigth);
        byte[] byte_sc_l = ByteBuffer.allocate(4).putInt(this.score.length).array();
        out.write(byte_sc_l);

        for(int i = 0; i < this.score.length; ++i) {
            out.write(ByteBuffer.allocate(4).putInt(this.score[i]).array());
        }

    }

    public void write(Writer out) {
        try {
            String str = "1 " + this.name + " " + this.weight + " " + this.score.length;

            for(int i = 0; i < this.score.length; ++i) {
                str = str + " " + this.score[i];
            }

            out.write(str + "\n");
        } catch (Exception var4) {

        }

    }

    @Override
    public String toString() {
        String str = "Имя собаки: "+name+"; Вес: "+ weight+"; Очки: ";
        for (int i = 0; i < score.length; i++)
            str += score[i] + " ";
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;
        if(!this.name.equals(dog.name)) return false;
        if(this.weight != dog.weight) return false;
        for (int i = 0; i < dog.score.length; i++)
        {
            if(this.score[i] != dog.score[i]) {return false;}
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result += 31 * weight;
        for (int i = 0; i < score.length; i++)
        {
            result +=31 * score[i];
        }
        return result;
    }


}
