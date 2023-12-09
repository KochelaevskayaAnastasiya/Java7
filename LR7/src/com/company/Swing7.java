package com.company;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;



public class Swing7 extends JFrame {

    private List<Animal> animals;

    private JButton button_bd_random = new JButton("Заполнить случайно");
    private JButton button_bd_file = new JButton("Загрузить из файла");
    private JPanel panel_common;

    JPanel panel_animal = new JPanel(new GridBagLayout());
    GridBagConstraints constr_animal = new GridBagConstraints();

    int y_count;

    String lookAndFeel = null;

    public Swing7() {
        super("Лабораторная работа №7");
        this.setSize(1000, 600);

        animals = new ArrayList<>();
        Font font = new Font("Verdana", Font.PLAIN, 16);
        button_bd_random.setFont(font);
        button_bd_file.setFont(font);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel_common = new JPanel(new GridBagLayout());
        GridBagConstraints constr_common = new GridBagConstraints();

        //радиобатон
        JRadioButton buttonSystem = new JRadioButton("System");
        JRadioButton buttonMotif = new JRadioButton("Motif");
        JRadioButton buttonMetal = new JRadioButton("Metal");


        Font font2 = new Font("Verdana", Font.PLAIN, 16);
        buttonSystem.setFont(font2);
        buttonMotif.setFont(font2);
        buttonMetal.setFont(font2);

        ButtonGroup group = new ButtonGroup();
        group.add(buttonSystem);
        group.add(buttonMotif);
        group.add(buttonMetal);

        JPanel panel_radio = new JPanel(new GridBagLayout());

        GridBagConstraints constr_radio = new GridBagConstraints();
        constr_radio.gridx = 0;
        constr_radio.gridy = 0;
        panel_radio.add(buttonSystem, constr_radio);
        constr_radio.gridx = 1;
        panel_radio.add(buttonMotif, constr_radio);
        constr_radio.gridx = 2;
        panel_radio.add(buttonMetal, constr_radio);

        buttonSystem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {lookAndFeel = UIManager.getSystemLookAndFeelClassName();try{UIManager.setLookAndFeel(lookAndFeel);SwingUtilities.updateComponentTreeUI(Swing7.this); }catch (Exception e){}}
        });
        buttonMotif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";try{UIManager.setLookAndFeel(lookAndFeel);SwingUtilities.updateComponentTreeUI(Swing7.this);}catch (Exception e){}}
        });
        buttonMetal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";try{UIManager.setLookAndFeel(lookAndFeel);SwingUtilities.updateComponentTreeUI(Swing7.this);}catch (Exception e){}}
        });



        constr_common.gridx = 0;
        constr_common.gridy = 0;
        panel_common.add(panel_radio, constr_common);


        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = 0;

        panel.add(button_bd_random, constr);
        constr.gridx = 1;
        panel.add(button_bd_file, constr);

        UpdateBD();

        constr_common.gridx = 0;
        constr_common.gridy = 1;
        panel_common.add(panel, constr_common);
        constr_common.gridy = 2;
        panel_common.add(panel_animal, constr_common);

        JScrollPane scrPane = new JScrollPane(panel_common);
        add(scrPane);

        this.setVisible(true);

        ActionListener listener_button_bd_random = new CreateBDRandomListner();
        button_bd_random.addActionListener(listener_button_bd_random);
        ActionListener listener_button_bd_file = new CreateBDFileListner();
        button_bd_file.addActionListener(listener_button_bd_file);

        panel_animal.addMouseListener(new MouseAdapter() {
            private Color background;

            @Override
            public void mousePressed(MouseEvent e) {
                JPanel comp = (JPanel) e.getSource();
                int he = comp.getComponent(0).getSize().height;
                int kk = e.getY()/he;
                JLabel c = (JLabel)comp.getComponent(kk);
                JOptionPane.showMessageDialog(Swing7.this,"<html><h2>Выбран объект №"+kk+"</h2><i>"+c.getText()+"</i>");
            }
        });
    }

    public class CreateBDRandomListner implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            try {
                animals = CreateBD.randomBD();
                UpdateBD();

            }
            catch (Exception exc)
            {
                System.out.println(exc.getMessage());
            }
        }
    }

    public class CreateBDFileListner implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            try {
                animals = CreateBD.fileBD();
                UpdateBD();
            }
            catch (Exception exc)
            {
                System.out.println(exc.getMessage());
            }
        }
    }

    public void UpdateBD(){
        panel_animal.removeAll();
        int i=2;
        for (Animal ani:animals){
            JLabel as = new JLabel(ani.toString());
            Font font = new Font("Verdana", Font.PLAIN, 16);
            as.setFont(font);
            as.setBorder(BorderFactory.createEtchedBorder());
            constr_animal.gridx = 2;
            constr_animal.gridy = i;
            panel_animal.add(as, constr_animal);
            i++;
        }
        this.invalidate();
        this.validate();
        this.repaint();
    }

}