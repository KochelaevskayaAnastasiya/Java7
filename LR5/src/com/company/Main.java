package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //интерфейс для лр4 и лр5
        //Animal_GUI animal_gui = new Animal_GUI();
        //animal_gui.setVisible(true);

        Scanner in = new Scanner(System.in);//writeFormat
        System.out.println("------------------------------------------------------------");
        System.out.println("Меню программы:");
        System.out.println("1 - Задание 1");
        System.out.println("2 - Задание 2");
        System.out.println("3 - Задание 3");
        System.out.println("0 - Выход");
        System.out.println("------------------------------------------------------------");
        System.out.flush();

        try {
            int menu = 0;
            while (menu == 0) {
                String who = in.nextLine();
                int wh = 0;
                int ww = 0;
                while (ww == 0) {
                    try {
                        wh = Integer.parseInt(who);
                        if (wh > 3 || wh < 0) throw new Exception("Такого пункта меню нет!");
                        ww = 1;
                    } catch (Exception e2) {
                        System.out.println("Ошибка: " + e2);
                        System.out.println("Введите заново");
                        who = in.nextLine();
                    }
                }

                switch (wh) {
                    case 1:
                        try {
                            Animal animal = new Cat(5, "Кузя", 4.5);
                            WriteThread writeThread = new WriteThread(animal);
                            //wh.setPriority(Thread.MAX_PRIORITY);
                            writeThread.start();
                            ReadThread rh = new ReadThread(animal);
                            //rh.setPriority(Thread.MIN_PRIORITY);
                            rh.start();
                            //System.out.println(writeThread.getPriority());
                            //System.out.println(rh.getPriority());

                        } catch (Exception err) {
                            System.out.println("Ошибка!" + err.getMessage());

                        }
                        break;
                    case 2:
                        Animal animal = new Cat(5, "Кузя", 4.5);
                        AnimalSynchronizer synchronizedAnimal = new AnimalSynchronizer(animal);
                        SyncWrite writer = new SyncWrite(synchronizedAnimal);
                        SyncRead reader = new SyncRead(synchronizedAnimal);

                        Thread writerThread = new Thread(writer);
                        Thread readerThread = new Thread(reader);

                        writerThread.start();
                        readerThread.start();
                        break;
                    case 3:
                        Animal animal2 = new Dog(5, "Кузя", 4.5);
                        /*AnimalSynchronizer animalSynchronizer = new AnimalSynchronizer(animal2);
                        SyncWrite syncWrite = new SyncWrite(animalSynchronizer);
                        SyncRead syncRead = new SyncRead(animalSynchronizer);

                        Thread writeThread = new Thread(syncWrite);
                        Thread readThread = new Thread(syncRead);

                        writeThread.start();
                        readThread.start();
                        Animal animal3 = WorkFlow.Animal_sync(animal2);
                        System.out.println(animal3.getOneScore(4));
                        //System.out.println(animal2);
                        TimeUnit.SECONDS.sleep(1);
                        animal3 = WorkFlow.Animal_sync(animal2);
                        System.out.println(animal3.getOneScore(4));*/


                        Animal sync = WorkFlow.Animal_sync(animal2);

                        Thread setOneThread = new Thread(() -> {
                            sync.setOneScore(0,1);
                            System.out.println("\nsetOneThread");
                        });

                        Thread setTwoThread = new Thread(() -> {
                            sync.setOneScore(0,2);
                            System.out.println("\nsetTwoThread");
                        });

                        Thread readThread = new Thread(() -> {
                            System.out.println("\nRead: " + sync.getOneScore(0));
                        });

                        setOneThread.start();
                        readThread.start();
                        setTwoThread.start();
                        break;
                    case 0:
                        menu = 1;
                        break;
                    default:
                        break;

                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}