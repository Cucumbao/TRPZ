package strategy;
import model.File;

public class FileSaver {
    private SaveStrategy strategy;

    public void setStrategy(SaveStrategy strategy) {
        this.strategy = strategy;
    }

    public void save(File file) {
        if (strategy == null) {
            System.out.println("⚠️ Стратегія не встановлена!");
            return;
        }
        strategy.save(file);
    }
}
