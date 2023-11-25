package com.company;

public class Dog implements Animal{
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
            throw new RuntimeException(exc.getMessenge());
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

    public int getOneScore(int k) {
        try {
            if (k>=score.length) throw new ExceptionMas();
            return score[k];
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
