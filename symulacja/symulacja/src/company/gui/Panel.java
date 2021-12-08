package company.gui;

import company.Person;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private Person[] person;

    public void setPerson(Person[] person) {
        this.person = person;
    }

    public Panel() {
        super();
//        this.add(new JButton("test"));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < person.length; i++) {
            if (person[i].handleSub().equals("vulnerable"))
                g.setColor(Color.GREEN);
            if (person[i].handleSub().equals("resist"))
                g.setColor(Color.BLUE);
            if (person[i].handleSub().equals("hasSymptoms"))
                g.setColor(Color.RED);
            if (person[i].handleSub().equals("doesntHave"))
                g.setColor(Color.ORANGE);
            g.fillRect(person[i].wektor.getComponents()[0], person[i].wektor.getComponents()[1], 4, 4);
//            g.drawLine(person[i].wektor.getComponents()[0]+2, person[i].wektor.getComponents()[1]+ 2, person[i].wektor.getComponents()[0]+ person[i].wektor.getVectors()[0]+ 4, person[i].wektor.getComponents()[1]+person[i].wektor.getVectors()[1]+ 4);
        }

    }

    public Person[] getPerson() {
        return person;
    }
}

