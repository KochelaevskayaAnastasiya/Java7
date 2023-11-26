package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //интерфейс для лр4 и лр5
        //Animal_GUI animal_gui = new Animal_GUI();
        //animal_gui.setVisible(true);
/*
        Animal animal = new Cat(5, "Кузя", 4.5);
        WriteThread wh =  new WriteThread(animal);
        //wh.setPriority(Thread.MAX_PRIORITY);
        wh.start();
        ReadThread rh = new ReadThread(animal);
        //rh.setPriority(Thread.MIN_PRIORITY);
        rh.start();
        System.out.println(wh.getPriority());
        System.out.println(rh.getPriority());
*/
        try {


            Animal animal2 = new Dog(5, "Кузя", 4.5);
            AnimalSynchronizer animalSynchronizer = new AnimalSynchronizer(animal2);
            SyncWrite syncWrite = new SyncWrite(animalSynchronizer);
            SyncRead syncRead = new SyncRead(animalSynchronizer);

            Thread writeThread = new Thread(syncWrite);
            Thread readThread = new Thread(syncRead);

            writeThread.start();
            readThread.start();
            Animal animal3 = WorkFlow.Animal_sync(animal2);
            System.out.println(animal3.getOneScore(4));

            System.out.println(animal2);
            animal3 = WorkFlow.Animal_sync(animal2);
            System.out.println(animal3.getOneScore(4));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}