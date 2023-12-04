package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //интерфейс для лр4 и лр5
        //Animal_GUI animal_gui = new Animal_GUI();
        //animal_gui.setVisible(true);

        //Меню лр5
        /*Scanner in = new Scanner(System.in);//writeFormat
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
                        Animal sync = WorkFlow.Animal_sync(animal2);

                        Thread setOneThread = new Thread(() -> {
                            sync.setOneScore(0,1);
                            System.out.println("\nWrite: 1 to position 0");
                        });

                        Thread setTwoThread = new Thread(() -> {
                            sync.setOneScore(0,2);
                            System.out.println("\nWrite: 2 to position 0");
                        });

                        Thread readThread = new Thread(() -> {
                            System.out.println("\nRead: " + sync.getOneScore(0)+" to position 0");
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
        }*/

        Scanner in = new Scanner(System.in);//writeFormat
        System.out.println("------------------------------------------------------------");
        System.out.println("Меню программы:");
        System.out.println("1 - Итератор");
        System.out.println("2 - Декоратор");
        System.out.println("3 - Байтовый");
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
                            CatFactory animal_factory = new CatFactory();
                            WorkFlow.setAnimal(animal_factory);
                            Animal animal_instance = WorkFlow.createInstance();
                            System.out.println("Итератор");
                            System.out.println("Цикл for");
                            for (Integer i : animal_instance) {
                                System.out.println(i);
                            }
                            Iterator<Integer> iter_while = animal_instance.iterator();
                            System.out.println("While");
                            while(iter_while.hasNext())
                            {
                                System.out.println(iter_while.next());
                            }

                        } catch (Exception err) {
                            System.out.println("Ошибка!" + err.getMessage());
                        }
                        break;
                    case 2:
                        try {


                            CatFactory animal_factory = new CatFactory();
                            WorkFlow.setAnimal(animal_factory);
                            Animal animal_instance = WorkFlow.createInstance();
                            System.out.println("Декоратор");
                            Animal unmodifiableAnimal = WorkFlow.unmodifiableAnimal(animal_instance);
                            System.out.println("Получено:" + unmodifiableAnimal.getName());
                            System.out.println("Изменено:");
                            unmodifiableAnimal.setOneScore(0, 10);
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        ArrayList<Animal> animals = new ArrayList<>();
                        Animal d = new Cat();
                        animals.add(d);
                        String name_file_bytes = "D:/Anastasia/University/7sem/jav/LR5/outputBytes.txt";
                        System.out.println("Байтовый");
                        FileOutputStream outputStream = new FileOutputStream(name_file_bytes);
                        for (int i=0;i<animals.size();i++)
                        {
                            WorkFlow.output(animals.get(i), outputStream);
                        }
                        outputStream.close();
                        FileInputStream inputStream = new FileInputStream(name_file_bytes);
                        List<Animal> animals_in = new ArrayList<>();
                        while (inputStream.available() > 0) {
                            animals_in.add(WorkFlow.input(inputStream));
                        }
                        System.out.println("Массив считанный из байтового файла");
                        for(int i = 0; i < animals_in.size(); ++i) {
                            System.out.println(animals_in.get(i));
                        }
                        inputStream.close();
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