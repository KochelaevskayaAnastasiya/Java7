package com.company;

public class DogFactory implements FactoryAnimal{

    public Animal createInstance() {
        return new Dog();
    }

    public Animal createInstance(String name,  double weight, int[] score) {
        return new Dog(score, name,weight);
    }
}
