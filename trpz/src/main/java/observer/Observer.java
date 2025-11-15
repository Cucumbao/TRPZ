package observer;

import model.File;

public interface Observer {
    void update(File file, String message);
}
