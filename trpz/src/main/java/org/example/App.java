package org.example;

import command.*;
import flyweight.Client;
import flyweight.FlyweightFactory;
import flyweight.JavaSyntaxHighlighter;
import model.File;
import observer.LogObserver;
import observer.StatusBarObserver;
import repo.FileRepository;
import templateMethod.LowercaseFormatter;
import templateMethod.TextFormatterTemplate;
import templateMethod.UppercaseFormatter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
        filetxt.setFileName("file_txt1111111");
        filetxt.setFilePath("C:\\Users\\АНЮТА\\IdeaProjects\\trpz\\txt");
        filetxt.setContent("\"TEXT\": \"Hello TXT\"");

        Command save1 = new SaveFileCommand(repository, fileJson);
        Command save2 = new SaveFileCommand(repository, filetxt);
        invoker.executeCommand(save1);
        invoker.executeCommand(save2);
        invoker.undoLastCommand();

 /*       TextFormatterTemplate formatter = new UppercaseFormatter(repository);
        TextFormatterTemplate formatter2 = new LowercaseFormatter(repository);
        String result = formatter.format(1L);
        String result2 = formatter2.format(2L);
        System.out.println(result);
        System.out.println(result2);*/
/*        FlyweightFactory factory = new FlyweightFactory();
        JavaSyntaxHighlighter highlighter = new JavaSyntaxHighlighter(factory);

        String code = "if (x == 10) {\n    System.out.println(\"Hello\");\n } \nfor (int i = 0; i < 5; i++) {\n}";

        List<Client> clients = highlighter.highlight(code);

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Highlighted Java</title></head><body><pre>");

        for (Client c : clients) {
            html.append(c.display());
        }

        html.append("</pre></body></html>");

        try (FileWriter writer = new FileWriter("highlighted_code.html")) {
            writer.write(html.toString());
            System.out.println("HTML file saved: highlighted_code.html");
        } catch (IOException e) {
            e.printStackTrace();*/
 //       }
    }
}
