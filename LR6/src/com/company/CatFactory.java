package com.company;

public class CatFactory implements FactoryAnimal{

    public Animal createInstance() {
        return new Cat();
    }
    public Animal createInstance(String name,  double weight, int[] score) {
        return new Cat(score, name,weight);
    }
}
