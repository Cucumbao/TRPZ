package observer;

public class LogObserver implements Observer {
    @Override
    public void update(model.File file, String message) {
        System.out.println("[LOG] File '" + file.getFileName() + "' event: " + message);
    }
}
