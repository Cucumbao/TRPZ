package observer;

import model.File;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(File file, String message);
}
