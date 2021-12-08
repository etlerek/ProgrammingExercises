package company.mementos;

import company.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class Originator {

    private Person[] people;

    public void set(Person[] people) throws CloneNotSupportedException {
        this.people = clonePeople(people);
    }

    public Memento storeInMemento(){
        return new Memento(this.people);
    }

    public Person[] restoreFromMemento(Memento memento){

        people = clonePeople(memento.getSavedPeople());

        return people;
    }

    public Person[] clonePeople(Person[] peopleIn){
        ArrayList <Person> peopleList = new ArrayList<>();
        for (Person person: peopleIn)
            peopleList.add(new Person(person));
        Person[] peopleOut = new Person[peopleList.size()];
        return peopleList.toArray(peopleOut);
    }
}
