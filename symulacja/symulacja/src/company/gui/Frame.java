package company.gui;

import company.mementos.CareTaker;
import company.mementos.Originator;
import company.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private Person[] person;
    public Panel panel = new Panel();
    public Originator originator = new Originator();
    public CareTaker careTaker = new CareTaker();

    public void saveImage(){
        System.out.println(person);
        try {
            originator.set(person);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        careTaker.addMemento(originator.storeInMemento());
    }

    public void reloadImage(){
        person = originator.restoreFromMemento(careTaker.getMemento(0));
        System.out.println(person);
        panel.setPerson(person);
    }

    public Frame(int n, int m, Person[] person){
        this.setTitle("Symulacja");
        this.setBounds(0,0, n+18,m+45);
        this.setResizable(false);
        this.person = person;
        panel.setPerson(person);
        JButton buttonSave = (JButton) panel.add(new JButton("Save"));

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveImage();
            }
        });
        JButton buttonLoad = (JButton) panel.add(new JButton("Load"));

        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadImage();
            }
        });
        getContentPane().add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

