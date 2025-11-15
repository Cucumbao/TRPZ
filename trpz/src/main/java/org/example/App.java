package org.example;

import command.*;
import model.File;
import observer.LogObserver;
import observer.StatusBarObserver;
import repo.FileRepository;
import templateMethod.LowercaseFormatter;
import templateMethod.TextFormatterTemplate;
import templateMethod.UppercaseFormatter;

public class App {
    public static void main(String[] args) {
        FileRepository repository = new FileRepository("");
        CommandInvoker invoker = new CommandInvoker();
        repository.attach(new LogObserver());
        repository.attach(new StatusBarObserver());

        File fileJson = new File();
        fileJson.setId(1L);
        fileJson.setFileName("file_json1");
        fileJson.setFilePath("C:\\Users\\АНЮТА\\IdeaProjects\\trpz\\json");
        fileJson.setContent("\"text\": \"Hello JSON\"");

        File filetxt = new File();
        filetxt.setId(2L);
        filetxt.setFileName("file_txt1");
        filetxt.setFilePath("C:\\Users\\АНЮТА\\IdeaProjects\\trpz\\txt");
        filetxt.setContent("\"TEXT\": \"Hello TXT\"");

        Command save1 = new SaveFileCommand(repository, fileJson);
        Command save2 = new SaveFileCommand(repository, filetxt);
        invoker.executeCommand(save1);
        invoker.executeCommand(save2);

        TextFormatterTemplate formatter = new UppercaseFormatter(repository);
        TextFormatterTemplate formatter2 = new LowercaseFormatter(repository);
        String result = formatter.format(1L);
        String result2 = formatter2.format(2L);
        System.out.println(result);
        System.out.println(result2);
    }
}
