package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateBD {

    public static List<Animal> randomBD() {
        List<Animal> animals = new ArrayList<>();

        CatFactory catFactory = new CatFactory();
        DogFactory dogFactory = new DogFactory();

        WorkFlow.setAnimal(catFactory);
        for(int i=0;i<32;i++){animals.add(WorkFlow.createInstance());}
        WorkFlow.setAnimal(dogFactory);
        for(int i=0;i<32;i++){animals.add(WorkFlow.createInstance());}
        return animals;
    }

    public static List<Animal> fileBD() {
        List<Animal> animals = new ArrayList<>();

        try {
            String name_file_bytes = "D:/Anastasia/University/7sem/jav/LR5/outputBytes.txt";

            FileInputStream inputStream = new FileInputStream(name_file_bytes);
            while (inputStream.available() > 0) {
                animals.add(WorkFlow.input(inputStream));
            }
            inputStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return animals;
    }
}