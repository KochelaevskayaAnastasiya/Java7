package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Animal_GUI extends JFrame {
    private JLabel label1 = new JLabel("Введите имя:");
    private JTextField textField_name = new JTextField(10);
    private JLabel label2 = new JLabel("Введите вес:");
    private JTextField textField_weight = new JTextField(10);
    private JLabel label3 = new JLabel("Введите оценки:");
    private JTextField textField_score = new JTextField(10);
    private JButton button_cat = new JButton("Добавить кошку");
    private JButton button_dog = new JButton("Добавить собаку");

    private JButton button_score = new JButton("Найти одинаковые очки");
    private JButton button_separation = new JButton("Разделить");

    private JLabel result = new JLabel();

    private JLabel all = new JLabel();

    private ArrayList<Animal> animals = new ArrayList<>();

    private ArrayList<Animal> cats = new ArrayList<>();
    private ArrayList<Animal> dogs = new ArrayList<>();

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

        container.add(button_score);
        container.add(button_separation);
        container.add(result);

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

        layout.putConstraint(SpringLayout.WEST, button_score, 200, SpringLayout.WEST, textField_name);
        layout.putConstraint(SpringLayout.NORTH, button_score, 30, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, button_separation, 250, SpringLayout.WEST, button_score);
        layout.putConstraint(SpringLayout.NORTH, button_separation, 30, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, result, 250, SpringLayout.WEST, textField_weight);
        layout.putConstraint(SpringLayout.NORTH, result, 30, SpringLayout.NORTH, button_separation);

        //int[] mas = new int[]{1, 2};
        //animals.add(new Cat(mas, "1", 1));
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


        ActionListener listener_score = new CreateScoreListner();
        button_score.addActionListener(listener_score);

        ActionListener listener_separation = new CreateSeparationListner();
        button_separation.addActionListener(listener_separation);

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

}
