package company.events;

import company.fireDeparments.IObserver;

public interface ISubject {

    void addObserver(IObserver obj);
    void removeObserver(IObserver obj);
    void notifyObserver(IObserver obj);
    void notifyObservers();

}
