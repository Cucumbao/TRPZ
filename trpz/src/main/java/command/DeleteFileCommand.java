package command;

import model.File;
import repo.FileRepository;

public class DeleteFileCommand implements Command {
    private FileRepository receiver;
    private Long fileId;
    private File deletedFile;

    public DeleteFileCommand(FileRepository receiver, Long fileId) {
        this.receiver = receiver;
        this.fileId = fileId;
    }

    @Override
    public void execute() {
        deletedFile = receiver.delete(fileId);
    }

    @Override
    public void undo() {
        if (deletedFile != null) {
            receiver.save(deletedFile);
            System.out.println("Undo delete: " + deletedFile.getFileName());
        }
    }
}
