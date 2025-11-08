package command;

import model.File;
import repo.FileRepository;

public class Client {
    public static void main(String[] args) {
        FileRepository repository = new FileRepository("");
        CommandInvoker invoker = new CommandInvoker();

        File fileJson = new File();
        fileJson.setId(1L);
        fileJson.setFileName("file_json1");
        fileJson.setFilePath("C:\\Users\\АНЮТА\\IdeaProjects\\trpz\\json");
        fileJson.setContent("\"text\": \"Hello JSON\"");

        File fileTxt = new File();
        fileTxt.setId(2L);
        fileTxt.setFileName("file_txt");
        fileTxt.setFilePath("C:\\Users\\АНЮТА\\IdeaProjects\\trpz\\txt");
        fileTxt.setContent("\"text\": \"afqkdfjhdf\"");


        Command save1 = new SaveFileCommand(repository, fileJson);
        Command save2 = new SaveFileCommand(repository, fileTxt);
        Command delete = new DeleteFileCommand(repository, 1L);
        InsertCharCommand insertChar = new InsertCharCommand(repository, 2L, '>', 5);

        invoker.executeCommand(save1);
        invoker.executeCommand(save2);
        invoker.executeCommand(insertChar);
        invoker.executeCommand(delete);
        invoker.undoLastCommand();
    }
}