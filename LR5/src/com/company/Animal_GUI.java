package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Animal_GUI extends JFrame {
    private JLabel label1 = new JLabel("Введите имя:");
    private JTextField textField_name = new JTextField(10);
    private JLabel label2 = new JLabel("Введите вес:");
    private JTextField textField_weight = new JTextField(10);
    private JLabel label3 = new JLabel("Введите оценки:");
    private JTextField textField_score = new JTextField(10);
    private JButton button_cat = new JButton("Добавить кошку");
    private JButton button_dog = new JButton("Добавить собаку");

    /*private JButton button_score = new JButton("Найти одинаковые очки");
    private JButton button_separation = new JButton("Разделить");
    private JButton button_consol = new JButton("Перейти на консоль");*/

    //ЛР5
    private JButton button_task_1 = new JButton("Задание 1");
    private JButton button_task_2 = new JButton("Задание 2");
    private JButton button_task_3 = new JButton("Задание 3");

    public TextArea res2= new TextArea();

    public JLabel result = new JLabel();

    private JLabel all = new JLabel();

    private ArrayList<Animal> animals = new ArrayList<>();

    private ArrayList<Animal> cats = new ArrayList<>();
    private ArrayList<Animal> dogs = new ArrayList<>();

    Scanner in = new Scanner(System.in);//writeFormat
    PrintWriter out2 = new PrintWriter(System.out);

    public Animal_GUI() {
        super("Лабораторная работа №3");
        this.setSize(1000, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();

        SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        container.add(label1);
        container.add(textField_name);
        container.add(label2);
        container.add(textField_weight);
        container.add(label3);
        container.add(textField_score);
        container.add(button_cat);
        container.add(button_dog);
        container.add(all);

        /*container.add(button_score);
        container.add(button_separation);
        container.add(button_consol);*/

        container.add(button_task_1);
        container.add(button_task_2);
        container.add(button_task_3);
        container.add(result);
        container.add(res2);

        layout.putConstraint(SpringLayout.WEST, label1, 30, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, label1, 30, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.NORTH, textField_name, 30, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.WEST, textField_name, 30, SpringLayout.EAST, label1);

        layout.putConstraint(SpringLayout.WEST, label2, 30, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, label2, 30, SpringLayout.NORTH, label1);

        layout.putConstraint(SpringLayout.NORTH, textField_weight, 30, SpringLayout.NORTH, textField_name);
        layout.putConstraint(SpringLayout.WEST, textField_weight, 32, SpringLayout.EAST, label2);

        layout.putConstraint(SpringLayout.WEST, label3, 30, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, label3, 30, SpringLayout.NORTH, label2);

        layout.putConstraint(SpringLayout.NORTH, textField_score, 30, SpringLayout.NORTH, textField_weight);
        layout.putConstraint(SpringLayout.WEST, textField_score, 12, SpringLayout.EAST, label3);

        layout.putConstraint(SpringLayout.WEST, button_cat, 30, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, button_cat, 30, SpringLayout.NORTH, label3);

        layout.putConstraint(SpringLayout.WEST, button_dog, 150, SpringLayout.WEST, button_cat);
        layout.putConstraint(SpringLayout.NORTH, button_dog, 30, SpringLayout.NORTH, textField_score);

        layout.putConstraint(SpringLayout.WEST, all, 30, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, all, 30, SpringLayout.NORTH, button_dog);

        /*layout.putConstraint(SpringLayout.WEST, button_score, 200, SpringLayout.WEST, textField_name);
        layout.putConstraint(SpringLayout.NORTH, button_score, 30, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, button_separation, 250, SpringLayout.WEST, button_score);
        layout.putConstraint(SpringLayout.NORTH, button_separation, 30, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, button_consol, 150, SpringLayout.WEST, button_separation);
        layout.putConstraint(SpringLayout.NORTH, button_consol, 30, SpringLayout.NORTH, container);*/


        layout.putConstraint(SpringLayout.WEST, result, 250, SpringLayout.WEST, textField_weight);
        layout.putConstraint(SpringLayout.NORTH, result, 30, SpringLayout.NORTH, button_task_2);

        //-------------------------------------------------------------------
        layout.putConstraint(SpringLayout.WEST, button_task_1, 150, SpringLayout.WEST, textField_name);
        layout.putConstraint(SpringLayout.NORTH, button_task_1, 30, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, button_task_2, 150, SpringLayout.WEST, button_task_1);
        layout.putConstraint(SpringLayout.NORTH, button_task_2, 30, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, button_task_3, 150, SpringLayout.WEST, button_task_2);
        layout.putConstraint(SpringLayout.NORTH, button_task_3, 30, SpringLayout.NORTH, container);

        String str = "<html>";

        for (int i = 0; i < animals.size(); i++) {
            str += animals.get(i) + "<br/>";
        }
        str += "</html>";
        all.setText(str);
        result.setText("Результат:");

        ActionListener listener_cat = new CreateCatListner();
        button_cat.addActionListener(listener_cat);

        ActionListener listener_dog = new CreateDogListner();
        button_dog.addActionListener(listener_dog);

        /*ActionListener listener_score = new CreateScoreListner();
        button_score.addActionListener(listener_score);

        ActionListener listener_separation = new CreateSeparationListner();
        button_separation.addActionListener(listener_separation);

        ActionListener listener_console = new ConsolListner();
        button_consol.addActionListener(listener_console);*/

        //------------------
        ActionListener listener_task_1 = new CreateTask1();
        button_task_1.addActionListener(listener_task_1);

        ActionListener listener_task_2 = new CreateTask2();
        button_task_2.addActionListener(listener_task_2);

        ActionListener listener_task_3 = new CreateTask3();
        button_task_3.addActionListener(listener_task_3);

    }
    public class CreateTask1 implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            try {

            }
            catch (Exception exc)
            {
            }
        }
    }

    public class CreateTask2 implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            try {
                Animal animal = new Cat(5, "Кузя", 4.5);
                WriteThread wh =  new WriteThread(animal);
                //wh.setPriority(Thread.MAX_PRIORITY);
                wh.start();
                ReadThread rh = new ReadThread(animal);
                //rh.setPriority(Thread.MIN_PRIORITY);
                rh.start();

            }
            catch (Exception exc)
            {
            }
        }
    }

    public class CreateTask3 implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            try {

            }
            catch (Exception exc)
            {
            }
        }
    }

    public class CreateCatListner implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            try {
                String name = textField_name.getText();
                double weight = Double.parseDouble(textField_weight.getText());
                String scores = textField_score.getText();
                String[] sc = scores.split(" ");
                int[] score = new int[sc.length];
                for (int i = 0; i < score.length; i++) {
                    score[i] = Integer.parseInt(sc[i]);
                }

                animals.add(new Cat(score, name, weight));

                String str = "<html>";

                for (int i = 0; i < animals.size(); i++) {
                    str += i + 1 + ") " + animals.get(i) + "<br/>";
                }
                str += "</html>";
                all.setText(str);
                textField_name.setText(null);
                textField_weight.setText(null);
                textField_score.setText(null);
            }
            catch (Exception exc)
            {
                all.setText(exc.getMessage());
                textField_weight.setText(null);
                if (animals.size()>0 && animals.get(animals.size()-1).getName()==null) animals.remove(animals.size()-1);
            }
        }
    }

    public class CreateDogListner implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try {
                String name = textField_name.getText();
                if (name.equals("")) throw new Exception("Укажите имя!");
                String w = textField_weight.getText();
                if (w.equals("")) throw new Exception("Укажите вес!");
                double weight = Double.parseDouble(w);
                String scores = textField_score.getText();
                if (scores.equals("")) throw new Exception("Укажите оценки!");
                String[] sc = scores.split(" ");
                int[] score = new int[sc.length];
                for (int i = 0; i < score.length; i++) {
                    score[i] = Integer.parseInt(sc[i]);
                }

                animals.add(new Dog(score, name, weight));

                String str = "<html>";

                for (int i = 0; i < animals.size(); i++) {
                    str += i + 1 + ") " + animals.get(i) + "<br/>";
                }
                str += "</html>";
                all.setText(str);
                textField_name.setText(null);
                textField_weight.setText(null);
                textField_score.setText(null);
            }
            catch (Exception exc)
            {
                all.setText(exc.getMessage());
                textField_weight.setText(null);
                if (animals.size()>0 && animals.get(animals.size()-1).getName()==null) animals.remove(animals.size()-1);
            }
        }
    }

    public class CreateScoreListner implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            try {
            int maxSc = 0;
            int j;

            for (int i =0; i<animals.size();i++)
            {
                j = animals.get(i).totalScore();
                if(j>maxSc) maxSc = j;
            }

            ArrayList<ArrayList<Animal>> any = new ArrayList<>(maxSc+1);
            for (int i =0; i<maxSc+1;i++) {
                any.add(new ArrayList<>());
            }
            int k;
            for (int i =0; i<animals.size();i++)
            {
                k = animals.get(i).totalScore();
                any.get(k).add(animals.get(i));
            }
            String res = "<html>Результат:<br/>";

            for (int i =0; i<maxSc+1;i++) {
                if (any.get(i).size() != 0) {
                    res += "С оценкой " + i + ": <br/>";
                    for (int j2=0; j2<any.get(i).size();j2++)
                    {
                        res += any.get(i).get(j2) + "<br/>";
                    }
                }
            }
            res += "</html>";
            result.setText(res);
            }
            catch (Exception exc)
            {
                result.setText(exc.getMessage());
            }

        }

    }
    public class CreateSeparationListner implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<Animal> cats = new ArrayList<>();
            ArrayList<Animal> dogs = new ArrayList<>();
            for (int i =0; i<animals.size();i++) {
                if(animals.get(i) instanceof Cat)
                {
                    cats.add(animals.get(i));
                }
                else
                {
                    dogs.add(animals.get(i));
                }
            }
            String res = "<html>Результат:<br/> Кошки:<br/>";
            for (int i =0; i<cats.size();i++) {
                res += cats.get(i) + "<br/>";
            }
            res+="Собаки:<br/>";
            for (int i =0; i<dogs.size();i++) {
                res += dogs.get(i) + "<br/>";
            }
            res += "</html>";
            result.setText(res);
        }

    }

    public class ConsolListner implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            try {
                String name_file_bytes = "D:/Anastasia/University/7sem/jav/LR3/outputBytes.txt";
                String name_file_string = "D:/Anastasia/University/7sem/jav/LR3/outputString.txt";
                String name_file_ser = "D:/Anastasia/University/7sem/jav/LR3/output_ser.txt";

                int menu = 0;
                while (menu==0) {
                    System.out.println("------------------------------------------------------------");
                    System.out.flush();
                    System.out.println("Список животных:");
                    for (int i=0; i<animals.size();i++)
                    {
                        WorkFlow.writeFormat(animals.get(i), out2);
                        //System.out.println(animals.get(i));
                    }
                    out2.flush();
                    //System.out.flush();
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Меню программы:");
                    System.out.println("1 - Ввести животное");
                    System.out.println("2 - Байтовый");
                    System.out.println("3 - Текстовый");
                    System.out.println("4 - Байтовый Сериализация");
                    System.out.println("0 - Выход");
                    System.out.println("------------------------------------------------------------");
                    System.out.flush();
                    String who = in.nextLine();
                    int wh = 0;
                    int ww =0;
                    while (ww==0) {
                        try {
                            wh = Integer.parseInt(who);
                            if (wh>4 || wh<0) throw new Exception("Такого пункта меню нет!");
                            ww=1;
                        } catch (Exception e2) {
                            System.out.println("Ошибка: " + e2);
                            System.out.println("Введите заново");
                            who = in.nextLine();
                        }
                    }

                    switch (wh) {
                        case 1:
                            try{
                                System.out.println("Введите следующие значения с троку через пробел:");
                                System.out.println("0, если кошка и 1, если собака, имя, вес и оценки");

                                animals.add(WorkFlow.readFormat(in));}
                            catch (Exception err)
                            {
                                System.out.println("Ошибка!"+ err.getMessage());

                            }
                            break;
                        case 2:
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
                        case 3:
                            System.out.println("Текстовый");
                            FileWriter writer = new FileWriter(name_file_string, false);
                            for (int i=0;i<animals.size();i++) {
                                WorkFlow.write(animals.get(i), writer);
                            }
                            writer.flush();
                            FileReader reader = new FileReader(name_file_string);

                            List<Animal> animals_in2 = new ArrayList<>();

                            try {
                                while (true) {
                                    animals_in2.add(WorkFlow.read(reader));
                                    //kk = reader.read();
                                }
                            }
                            catch(Exception ex) {

                            }
                            System.out.println("Массив считанный из текстового файла");
                            for(int i = 0; i < animals_in2.size(); ++i) {
                                System.out.println(animals_in2.get(i));
                            }
                            reader.close();
                            break;
                        case 4:
                            System.out.println("Байтовый Сериализация");
                            FileOutputStream outputStream_ser = new FileOutputStream(name_file_ser);
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream_ser);

                            for (int i=0;i<animals.size();i++)
                            {
                                WorkFlow.serialize(animals.get(i), objectOutputStream);
                            }
                            objectOutputStream.close();
                            FileInputStream fileInputStream = new FileInputStream(name_file_ser);
                            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                            List<Animal> list = new ArrayList<Animal>();
                            int chek=0;
                            try
                            {
                                while (true)
                                {list.add(WorkFlow.deserialize(objectInputStream));}

                            }
                            catch(Exception ex){
                                chek = 1;
                            }
                            if(chek==1)
                            {
                                System.out.println("Массив считанный из файла");
                                for(int i = 0; i < list.size(); ++i) {
                                    System.out.println(list.get(i));
                                }
                            }

                            break;
                        case 0:
                            menu=1;
                            String str = "<html>";

                            for (int i = 0; i < animals.size(); i++) {
                                str += i + 1 + ") " + animals.get(i) + "<br/>";
                            }
                            str += "</html>";
                            all.setText(str);
                            break;
                        default:
                            break;

                    }

                }
                //in.close();
                //out2.close();
            }
            catch (Exception var17) {
                System.out.println(var17);
            }
        }
    }
}
