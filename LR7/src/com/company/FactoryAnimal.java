package com.company;

public interface FactoryAnimal {
    Animal createInstance();
    Animal createInstance(String name, double weight, int[] score);
}