package org.example;

import model.File;
import strategy.FileSaver;
import strategy.SaveAsJson;
import strategy.SaveAsTxt;
import strategy.SaveAsXml;;

public class App {
    public static void main(String[] args) {
        File file = new File();
        file.setId(1L);
        file.setFileName("example");
        file.setContent("Це тестовий контент файлу");
        file.setUser(100L);
        file.setLastUpdate(java.time.LocalDate.now().toString());
        FileSaver saver = new FileSaver();

        saver.setStrategy(new SaveAsTxt());
        saver.save(file);

        saver.setStrategy(new SaveAsJson());
        saver.save(file);

        saver.setStrategy(new SaveAsXml());
        saver.save(file);
    }
}
