package company.mementos;

import company.Person;

import java.util.Arrays;

public class Memento {
    private Person[] person;

    public Memento(Person[] personSave){
        this.person = personSave;
        for(int k = 0; k< 10; k++) {
            System.out.println(person[k].handle() +" "+ person[k].handleSub()+ " " + Arrays.toString(person[k].wektor.getComponents()));
        }
    }

    public Person[] getSavedPeople(){
        for(int k = 0; k< 10; k++) {
            System.out.println(person[k].handle() +" "+ person[k].handleSub()+ " " + Arrays.toString(person[k].wektor.getComponents()));
        }
        return person;
    }
}
