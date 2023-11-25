package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkFlow {

    //записи в байтовый поток
    static void output(Animal o, OutputStream out) {
        try {
            o.output(out);
        } catch (Exception e) {

        }
    }

    //чтения из байтового потока
    static Animal input(InputStream in) {
        try {
            byte[] wh = new byte[1];
            in.read(wh);

            byte[] byte_name_l = new byte[4];
            in.read(byte_name_l);
            int name_l = ByteBuffer.wrap(byte_name_l).getInt();
            byte[] byte_name = new byte[name_l];
            in.read(byte_name);
            String name = new String(byte_name);

            byte[] byte_weigth_l = new byte[4];
            in.read(byte_weigth_l);
            int w_l = ByteBuffer.wrap(byte_weigth_l).getInt();
            //System.out.println(w_l);
            byte[] byte_weigth = new byte[w_l];
            in.read(byte_weigth);
            double weigth = Double.valueOf(new String(byte_weigth));

            byte[] byte_sc_l = new byte[4];
            in.read(byte_sc_l);
            int sc_l = ByteBuffer.wrap(byte_sc_l).getInt();
            int[] an = new int[sc_l];

            for (int i = 0; i < sc_l; i++) {
                byte[] byte_l = new byte[4];
                in.read(byte_l);
                an[i] = ByteBuffer.wrap(byte_l).getInt();
            }
            if (wh[0] == 0) {
                return new Cat(an, name, weigth);
            } else {
                return new Dog(an, name, weigth);
            }

        } catch (Exception e) {
            return null;

        }
    }

    //записи в символьный поток
    static void write(Animal o, Writer out) {
        try {
                o.write(out);

        } catch (Exception e) {

        }
    }

    //чтения из символьного потока
    static Animal read(Reader in) {
        try {
            String str = "";
            int k = in.read();
            if (k!=-1) {
                while (k != -1 && k != '\n') {
                    str += (char) k;
                    k = in.read();
                }
                String[] parts = str.split(" ");
                int u = 0;
                int wh = Integer.parseInt(parts[u]);
                String name = parts[u + 1];
                double wei = Double.valueOf(parts[u + 2]);
                int rr = Integer.parseInt(parts[u + 3]);
                int[] mas = new int[rr];
                for (int i = 0; i < rr; i++) {
                    mas[i] = Integer.parseInt(parts[u + i + 4]);
                }
                if (wh == 0) {
                    return new Cat(mas, name, wei);
                } else {
                    return new Dog(mas, name, wei);
                }
            }
            else throw new Exception("");
        } catch (Exception e) {
            throw new RuntimeException("");
            //return null;
        }
    }

    //вывод сериализованных объектов
    static void serialize(Animal o, ObjectOutputStream out) {
        try {
            out.writeObject(o);
        } catch (Exception e) {

        }
    }

    //ввод десериализованного объекта
    static Animal deserialize(ObjectInputStream in) {

        try {
            return (Animal) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException("");

        }
    }

    //вывод с объектов
    static void writeFormat(Animal o, PrintWriter out) {
        try {
            String str = "";
            //int[] sc = new int[o.getScoresLen()];
            for (int i = 0; i < o.getScoresLen(); i++)
                str += o.getOneScore(i)+" ";
            if(o instanceof Cat)
            {
                out.printf("Имя кошки: %s; Вес: %s; Очки: %s\n", o.getName(), o.getWeight(), str);
            }
            else
            {
                out.printf("Имя собаки: %s; Вес: %s; Очки: %s\n", o.getName(), o.getWeight(), str);
            }
        }
        catch (Exception e)
        {
            throw new ExceptionInput(e.getMessage());
        }
    }

    //ввод объекта (параметром метода является объект типа Scanner)
    static Animal readFormat(Scanner in) {
        try {
            String str = in.nextLine();
            String[] ani = str.split(" ");
            int who = Integer.parseInt(ani[0]);
            if(who==0 || who==1) {
                String name = ani[1];
                double weigth = Double.parseDouble(ani[2]);

                int[] score = new int[ani.length - 3];
                for (int i = 0; i < score.length; i++) {
                    score[i] = Integer.parseInt(ani[i + 3]);
                }
                if (who == 0) return new Cat(score, name, weigth);
                else return new Dog(score, name, weigth);
            }
            else throw new ExceptionInput("Ошибка!");
        }
        catch (ExceptionInput e)
        {
            throw new ExceptionInput("Ошибка про вводе!");
        }
        /*System.out.println("Ввод животного");
        System.out.println("Введите 0, если хотите добавить кошку и 1, если собаку");
        System.out.println("Введите -1, если хотите выйти без добавления");
        String who = in.nextLine();
        int wh = 0;
        int ww = 0;
        while (ww==0) {
            try {
                wh = Integer.parseInt(who);
                if(wh==-1) return null;
                if(wh==0||wh==1)
                {ww=1;}
                else throw new ExceptionInput("Неверное значение!");
            } catch (ExceptionInput e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Введите заново");
                who = in.nextLine();
            }
        }
        System.out.println("Введите имя животного:");
        String name = in.nextLine();

        System.out.println("Введите вес животного");
        String wei = in.nextLine();
        double weigth = 0;
        int kk =0;
        while (kk==0) {
            try {
                weigth = Double.parseDouble(wei);
                if(weigth<=0) throw new ExceptionInput("Вес не может быть меньше или равен нулю!");
                kk=1;
            } catch (ExceptionInput e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Введите вес заново");
                wei = in.nextLine();
            }
        }

        System.out.println("Введите оценки животного через пробел");
        String scores = in.nextLine();
        int[] score = null;
        int ss =0;
        while (ss==0) {
            try {
                String[] sc = scores.split(" ");
                score = new int[sc.length];
                for (int i = 0; i < score.length; i++) {
                    score[i] = Integer.parseInt(sc[i]);
                }
                ss=1;
            } catch (ExceptionInput e) {
                System.out.println("Ошибка: ");
                System.out.println("Введите оценки заново");
                scores = in.nextLine();
            }
        }
        Animal animal;
        if (wh==0) animal = new Cat(score, name, weigth);
        else animal=new Dog(score, name, weigth);
        return animal;*/
    }

}
