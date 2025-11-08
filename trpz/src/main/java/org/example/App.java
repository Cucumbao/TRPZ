package org.example;

import model.File;
import repo.FileRepository;

public class App {
    public static void main(String[] args) {
        FileRepository repo = new FileRepository("");
        File file = new File();
        file.setId(1000L);
        file.setFileName("приклад3");
        file.setFilePath("C:\\Users\\АНЮТА\\IdeaProjects\\trpz\\json");
        file.setContent("Київ це столиця України1\nпривіт я контент файлу\nбла бла бла\n4 4 4\nшось шось шось");
        file.setUser(100L);
        file.setLastUpdate(java.time.LocalDate.now().toString());
        repo.save(file);
    }
}
