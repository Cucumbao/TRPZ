package observer;

public class StatusBarObserver implements Observer {
    @Override
    public void update(model.File file, String message) {
        System.out.println("[StatusBar] Action: " + message +
                " | Symbols in content: " + file.getContent().length());
    }
}
