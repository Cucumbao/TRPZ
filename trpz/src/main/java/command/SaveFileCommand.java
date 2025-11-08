package command;
import model.File;
import repo.FileRepository;

public class SaveFileCommand implements Command {
    private FileRepository receiver;
    private File file;

    public SaveFileCommand(FileRepository receiver, File file) {
        this.receiver = receiver;
        this.file = file;
    }

    @Override
    public void execute() {
        receiver.save(file);
        System.out.println("File saved: " + file.getFileName());
    }

    @Override
    public void undo() {
        receiver.delete(file.getId());
        System.out.println("Undo save: " + file.getFileName());
    }
}
