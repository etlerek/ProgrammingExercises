package company.mementos;

import java.util.ArrayList;

public class CareTaker {

    ArrayList<Memento> savedPeople = new ArrayList<Memento>();

    public void addMemento(Memento m){
        savedPeople.clear();
        savedPeople.add(m);
        System.out.println("add memento: " + savedPeople.size());
    }

    public Memento getMemento(int index) {
        return savedPeople.get(index);
    }

}
